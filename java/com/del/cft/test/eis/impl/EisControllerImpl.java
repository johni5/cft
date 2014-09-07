package com.del.cft.test.eis.impl;

import com.del.cft.test.CommonException;
import com.del.cft.test.eis.IEisController;
import com.del.cft.test.eis.Person;
import com.del.cft.test.utils.HibernateUtil;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

/**
 * User: johni5
 * Date: 04.09.14
 * Time: 18:22
 */
public class EisControllerImpl implements IEisController {

    @Override
    public void createPerson(final Person person) throws CommonException {
        handle(new RequestHandler() {
            @Override
            public void handle(Session session) {
                session.beginTransaction();
                session.save(person);
                session.getTransaction().commit();
            }
        });
    }

    @Override
    public void updatePerson(final Person person) throws CommonException {
        handle(new RequestHandler() {
            @Override
            public void handle(Session session) {
                session.beginTransaction();
                session.update(person);
                session.getTransaction().commit();
            }
        });
    }

    @Override
    public Person getPerson(final Long id) throws CommonException {
        final Person person = new Person();
        handle(new RequestHandler() {
            @Override
            public void handle(Session session) throws CommonException {
                try {
                    Person p = (Person) session.load(Person.class, id);
                    if (p != null) {
                        person.copy(p);
                    }
                } catch (ObjectNotFoundException e) {
                    throw new CommonException("Object not found", e);
                }
            }
        });
        return person;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Person> getAllPersons() throws CommonException {
        final List<Person> result = new ArrayList<Person>();
        handle(new RequestHandler() {
            @Override
            public void handle(Session session) {
                List<Person> list = session.createCriteria(Person.class).list();
                if (list != null) {
                    result.addAll(list);
                }
            }
        });
        return result;
    }

    @Override
    public void deletePerson(final Person person) throws CommonException {
        handle(new RequestHandler() {
            @Override
            public void handle(Session session) {
                session.beginTransaction();
                session.delete(person);
                session.getTransaction().commit();
            }
        });
    }

    private void handle(RequestHandler requestHandler) throws CommonException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            requestHandler.handle(session);
        } catch (Exception e) {
            throw new CommonException("Handle request error", e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    interface RequestHandler {

        void handle(Session session) throws CommonException;

    }
}
