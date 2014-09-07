package com.del.cft.test.server.beans;

import com.del.cft.test.CommonException;
import com.del.cft.test.eis.EisFactory;
import com.del.cft.test.eis.Person;
import com.del.cft.test.server.RequestContext;
import com.del.cft.test.server.Scope;
import com.del.cft.test.server.forms.NameForm;
import com.del.cft.test.utils.ClientLog;
import com.del.cft.test.utils.Strings;

/**
 * User: johni5
 * Date: 05.09.14
 * Time: 15:46
 */
public class CreatePersonBean {

    private NameForm form;

    public CreatePersonBean() {
        this.form = RequestContext.getCurrent().getVar(NameForm.class, Scope.SESSION);
    }

    public NameForm getForm() {
        return form;
    }
}
