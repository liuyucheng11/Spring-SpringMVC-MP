package com.studentAdmin.api.apiAnnotation;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author: liu.yucheng
 * @Date: 2019-7-6  10:43
 * @version: 1.0
 */
@Target(TYPE)
@Inherited
@Retention(RUNTIME)
public @interface ServiceCom {

    String id();
}
