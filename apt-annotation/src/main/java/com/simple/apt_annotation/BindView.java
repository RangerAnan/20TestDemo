package com.simple.apt_annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * author fcl
 * date  2019/12/7
 * description
 */

@Retention(RetentionPolicy.CLASS)   //编译时注解
@Target(ElementType.FIELD)
public @interface BindView {
    int value();
}
