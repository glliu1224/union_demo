package com.example.annotations.myannotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/**
*@Description
*@Author glliu
*@Date 2020/12/11
*/
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {

    String[] value() default "unknown";
}
