package com.del.cft.test;

import com.del.cft.test.eis.EisFactory;
import com.del.cft.test.eis.IEisController;
import com.del.cft.test.eis.Person;

/**
 * User: johni5
 * Date: 04.09.14
 * Time: 18:44
 */
public class MainTest {

    public static void main(String[] args) {
        new MainTest().start();
    }

    public void start() {
        try {
            test1();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    public void test1() throws CommonException {
        Person p1 = new Person("Evgeny", "Leo", "Dodolin");
        IEisController controller = EisFactory.getInstance().getController();
        controller.createPerson(p1);
        System.out.println(controller.getAllPersons());
    }

}
