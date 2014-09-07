package com.del.cft.test.server.actions;

import com.del.cft.test.CommonException;

/**
 * User: johni5
 * Date: 05.09.14
 * Time: 9:36
 */
public interface IAction {

    void handle() throws CommonException;

}
