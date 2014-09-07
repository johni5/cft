package com.del.cft.test.server.beans;

import com.del.cft.test.CommonException;
import com.del.cft.test.eis.EisFactory;
import com.del.cft.test.eis.Person;
import com.del.cft.test.utils.ClientLog;

import java.util.List;

/**
 * User: johni5
 * Date: 05.09.14
 * Time: 15:46
 */
public class PersonalBean {

    private List<Person> list;

    public PersonalBean() {
        init();
    }

    private void init() {
        try {
            list = EisFactory.getInstance().getController().getAllPersons();
        } catch (CommonException e) {
            ClientLog.error(e.getMessage(), e);
        }
    }

    public List<Person> getList() {
        return list;
    }
}
