package com.del.cft.test.server.managers;

import com.del.cft.test.server.Page;
import com.del.cft.test.server.RequestContext;
import com.del.cft.test.server.Scope;
import com.del.cft.test.server.actions.CreatePerson;
import com.del.cft.test.server.actions.IAction;
import com.del.cft.test.server.actions.RemovePerson;
import com.del.cft.test.server.actions.UpdatePerson;
import com.del.cft.test.utils.Reflections;
import com.del.cft.test.utils.Strings;

import java.util.HashMap;
import java.util.Map;

/**
 * User: johni5
 * Date: 05.09.14
 * Time: 9:40
 */
public class ActionRegister {

    private Map<ActionKey, ActionEntry> actions = new HashMap<>();

    public ActionRegister() {
        register(CreatePerson._NAME, Page.PERSONAL, CreatePerson.class);
        register(UpdatePerson._NAME, Page.EDIT_PERSON, UpdatePerson.class);
        register(RemovePerson._NAME, Page.PERSONAL, RemovePerson.class);
    }

    public static ActionRegister getInstance() {
        return RequestContext.getCurrent().getVar(ActionRegister.class, Scope.CONTEXT);
    }

    private void register(String name, Page page, Class<? extends IAction> aClass) {
        actions.put(key(name, page), entry(aClass));
    }

    private ActionKey key(String name, Page page) {
        return new ActionKey(name, page);
    }

    private ActionEntry entry(Class<? extends IAction> aClass) {
        return new ActionEntry(aClass);
    }

    public IAction detect(String action, Page page) {
        if (!Strings.isTrimmedEmpty(action) && page != null) {
            ActionKey key = key(action, page);
            if (actions.containsKey(key)) {
                ActionEntry entry = actions.get(key);
                return Reflections.tryCreate(entry.aClass);
            }
        }
        return null;
    }

    class ActionEntry {

        Class<? extends IAction> aClass;

        ActionEntry(Class<? extends IAction> aClass) {
            this.aClass = aClass;
        }
    }

    class ActionKey {

        String name;
        Page page;

        protected ActionKey(String name, Page page) {
            this.name = name;
            this.page = page;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            ActionKey actionKey = (ActionKey) o;

            if (name != null ? !name.equals(actionKey.name) : actionKey.name != null) return false;
            if (page != actionKey.page) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + (page != null ? page.hashCode() : 0);
            return result;
        }
    }
}
