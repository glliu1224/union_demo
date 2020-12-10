package com.example.annotations;

import java.lang.annotation.*;

/**
 * 自定义注解
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
//@Inherited//所标记的annotation具有继承性
public @interface Inheritable {
}
