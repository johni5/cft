package com.del.cft.test.server.beans;

import com.del.cft.test.CommonException;
import com.del.cft.test.eis.EisFactory;
import com.del.cft.test.eis.Person;
import com.del.cft.test.utils.ClientLog;
import com.del.cft.test.utils.Strings;

/**
 * User: johni5
 * Date: 05.09.14
 * Time: 15:46
 */
public class EditPersonBean {

    private Person person;

    public void setId(String id) {
        if (!Strings.isTrimmedEmpty(id)) {
            try {
                person = EisFactory.getInstance().getController().getPerson(Long.parseLong(id));
            } catch (CommonException e) {
                ClientLog.error(e.getMessage(), e);
            }
        }
    }

    public Person getPerson() {
        return person;
    }
}
