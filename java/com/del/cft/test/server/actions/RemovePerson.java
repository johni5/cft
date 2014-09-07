package com.del.cft.test.server.actions;

import com.del.cft.test.CommonException;
import com.del.cft.test.eis.EisFactory;
import com.del.cft.test.eis.IEisController;
import com.del.cft.test.eis.Person;
import com.del.cft.test.server.Page;
import com.del.cft.test.server.forms.NameForm;

/**
 * User: johni5
 * Date: 05.09.14
 * Time: 15:09
 */
public class RemovePerson extends AbstractAction {

    public static final String _NAME = "RemovePerson";

    @Override
    public void handle() throws CommonException {
        if (validateParams(NameForm._P_ID)) {
            Long id = Long.parseLong(getParameter(NameForm._P_ID));
            IEisController controller = EisFactory.getInstance().getController();
            try {
                Person p = controller.getPerson(id);
                if (p != null && p.getId() != null) {
                    controller.deletePerson(p);
                    addMessage("Person was deleted!");
                } else {
                    addError("Person not found!");
                    setPage(Page.PERSONAL);
                }
            } catch (CommonException e) {
                addError("Person not found!");
                setPage(Page.PERSONAL);
            }
        } else {
            setPage(Page.PERSONAL);
        }
    }

}
