package com.del.cft.test.server.actions;

import com.del.cft.test.CommonException;
import com.del.cft.test.eis.EisFactory;
import com.del.cft.test.eis.IEisController;
import com.del.cft.test.eis.Person;
import com.del.cft.test.server.Page;
import com.del.cft.test.server.forms.NameForm;
import com.del.cft.test.utils.Strings;

/**
 * User: johni5
 * Date: 05.09.14
 * Time: 15:09
 */
public class UpdatePerson extends AbstractAction {

    public static final String _NAME = "UpdatePerson";

    @Override
    public void handle() throws CommonException {
        NameForm nameForm = new NameForm();
        if (validateAndLoad(nameForm)) {
            if (!Strings.isTrimmedEmpty(nameForm.getId())) {
                IEisController controller = EisFactory.getInstance().getController();
                try {
                    Person p = controller.getPerson(Long.parseLong(nameForm.getId()));
                    if (p != null && p.getId() != null) {
                        nameForm.loadTo(p);
                        controller.updatePerson(p);
                        addMessage("Person changed successfully");
                    } else {
                        addError("Person not found!");
                        setPage(Page.PERSONAL);
                    }
                } catch (CommonException e) {
                    addError("Person not found!");
                    setPage(Page.PERSONAL);
                }
            } else {
                addError("Person not found!");
                setPage(Page.EDIT_PERSON);
            }
        } else {
            setPage(Page.EDIT_PERSON);
        }
    }

}
