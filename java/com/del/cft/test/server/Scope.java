package com.del.cft.test.server;

import com.del.cft.test.utils.Reflections;
import com.del.cft.test.utils.Unchecked;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.locks.ReentrantLock;

/**
 * User: johni5
 * Date: 05.09.14
 * Time: 9:24
 */
public enum Scope {

    REQUEST(new AttrHandler() {
        @Override
        public <T> T getAttribute(String name, HttpServletRequest request) {
            return Unchecked.cast(request.getAttribute(name));
        }

        @Override
        public <T> void setAttribute(String name, HttpServletRequest request, T obj) {
            request.setAttribute(name, obj);
        }

        @Override
        public void lock(HttpServletRequest request) {
            //
        }

        @Override
        public void unlock(HttpServletRequest request) {
            //
        }
    }),
    SESSION(new AttrHandler() {
        ReentrantLock globalLock = new ReentrantLock();
        String LOCK_NAME = "SESSION_LOCK";

        @Override
        public <T> T getAttribute(String name, HttpServletRequest request) {
            return Unchecked.cast(request.getSession().getAttribute(name));
        }

        @Override
        public <T> void setAttribute(String name, HttpServletRequest request, T obj) {
            request.getSession().setAttribute(name, obj);
        }

        @Override
        public void lock(HttpServletRequest request) {
            ReentrantLock lock = (ReentrantLock) request.getSession().getAttribute(LOCK_NAME);
            if (lock == null) {
                globalLock.lock();
                try {
                    lock = (ReentrantLock) request.getSession().getAttribute(LOCK_NAME);
                    if (lock == null) {
                        lock = new ReentrantLock();
                        request.getSession().setAttribute(LOCK_NAME, lock);
                    }
                } finally {
                    globalLock.unlock();
                }
            }
            lock.lock();
        }

        @Override
        public void unlock(HttpServletRequest request) {
            ReentrantLock lock = (ReentrantLock) request.getSession().getAttribute(LOCK_NAME);
            if (lock != null && lock.isLocked()) {
                lock.unlock();
            }
        }
    }),
    CONTEXT(new AttrHandler() {
        ReentrantLock lock = new ReentrantLock();

        @Override
        public <T> T getAttribute(String name, HttpServletRequest request) {
            return Unchecked.cast(request.getSession().getServletContext().getAttribute(name));
        }

        @Override
        public <T> void setAttribute(String name, HttpServletRequest request, T obj) {
            request.getSession().getServletContext().setAttribute(name, obj);
        }

        @Override
        public void lock(HttpServletRequest request) {
            lock.lock();
        }

        @Override
        public void unlock(HttpServletRequest request) {
            if (lock.isLocked()) {
                lock.unlock();
            }
        }
    });

    Scope(AttrHandler finder) {
        this.attrHandler = finder;
    }

    private AttrHandler attrHandler;

    public <T> T get(String name, Class<T> beanClass, HttpServletRequest request) {
        T obj = attrHandler.getAttribute(name, request);
        if (obj == null) {
            attrHandler.lock(request);
            try {
                obj = attrHandler.getAttribute(name, request);
                if (obj == null) {
                    obj = Reflections.tryCreate(beanClass);
                    attrHandler.setAttribute(name, request, obj);
                }
            } finally {
                attrHandler.unlock(request);
            }
        }
        return obj;
    }

    interface AttrHandler {
        <T> T getAttribute(String name, HttpServletRequest request);

        <T> void setAttribute(String name, HttpServletRequest request, T obj);

        void lock(HttpServletRequest request);

        void unlock(HttpServletRequest request);
    }

}
