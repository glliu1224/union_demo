package com.example.demo.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DColumn {

    //字段名
    String name() default "";

    //字段类型
    String columnType();

    //字段长度
    int length() default 0;

    //字段能否为空
    boolean canBeEmpty() default true;

    //注释
    String comment() default "";
}
