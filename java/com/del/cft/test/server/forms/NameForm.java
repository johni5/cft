package com.del.cft.test.server.forms;

import com.del.cft.test.eis.Person;

import java.security.Permission;

/**
 * User: johni5
 * Date: 05.09.14
 * Time: 15:04
 */
public class NameForm implements IForm {

    public final static String _P_ID = "PID";
    public final static String _P_NAME_F_NAME = "FIRST_NAME";
    public final static String _P_NAME_PAT = "PATRONYMIC";
    public final static String _P_NAME_L_NAME = "LAST_NAME";

    @RequestParameter(name = _P_ID, optional = true)
    private String id;

    @RequestParameter(name = _P_NAME_F_NAME, min = 1, max = 255)
    private String firstName;

    @RequestParameter(name = _P_NAME_PAT, optional = true)
    private String patronymic;

    @RequestParameter(name = _P_NAME_L_NAME, min = 1, max = 255)
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void loadTo(Person p) {
        p.setFirstName(firstName);
        p.setPatronymic(patronymic);
        p.setLastName(lastName);
    }

    public void clear() {
        id = null;
        firstName = null;
        patronymic = null;
        lastName = null;
    }
}
