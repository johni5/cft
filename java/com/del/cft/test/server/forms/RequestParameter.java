package com.del.cft.test.server.forms;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * User: johni5
 * Date: 05.09.14
 * Time: 9:39
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.FIELD)
public @interface RequestParameter {

    String name();

    boolean optional() default false;

    int max() default -1;

    int min() default -1;

}