package com.example.annotations.table;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)//注解作用于，作用于类上面
@Retention(RetentionPolicy.RUNTIME)//注解生效在运行时
public @interface DBTable {
    String name() default "";
}
