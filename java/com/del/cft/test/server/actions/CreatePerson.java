package com.del.cft.test.server.actions;

import com.del.cft.test.CommonException;
import com.del.cft.test.eis.EisFactory;
import com.del.cft.test.eis.Person;
import com.del.cft.test.server.Page;
import com.del.cft.test.server.RequestContext;
import com.del.cft.test.server.Scope;
import com.del.cft.test.server.forms.NameForm;

/**
 * User: johni5
 * Date: 05.09.14
 * Time: 15:09
 */
public class CreatePerson extends AbstractAction {

    public static final String _NAME = "CreatePerson";

    @Override
    public void handle() throws CommonException {
        NameForm nameForm = RequestContext.getCurrent().getVar(NameForm.class, Scope.SESSION);
        if (validateAndLoad(nameForm)) {
            Person p = new Person();
            nameForm.loadTo(p);
            EisFactory.getInstance().getController().createPerson(p);
            addMessage("Person created successfully");
            nameForm.clear();
        } else {
            setPage(Page.NEW_PERSON);
        }
    }

}
