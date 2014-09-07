package com.del.cft.test.eis;

import com.del.cft.test.CommonException;

import java.util.List;

/**
 * User: johni5
 * Date: 04.09.14
 * Time: 18:16
 */
public interface IEisController {

    void createPerson(Person person) throws CommonException;

    void updatePerson(Person person) throws CommonException;

    Person getPerson(Long id) throws CommonException;

    List<Person> getAllPersons() throws CommonException;

    void deletePerson(Person person) throws CommonException;


}
