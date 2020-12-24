package com.example.annotations.myannotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AnnotationTest {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // 新建Person
        Person person = new Person();
        // 获取Person的Class实例
        Class<Person> c = Person.class;
        // 获取 somebody() 方法的Method实例
        Method mSomebody = c.getMethod("somebody", String.class, int.class);
        // 执行该方法
        mSomebody.invoke(person, "lily", 18);
        iteratorAnnotations(mSomebody);

        // 获取 somebody() 方法的Method实例
        Method mEmpty = c.getMethod("empty");
        // 执行该方法
        mEmpty.invoke(person);
        iteratorAnnotations(mEmpty);
    }

    public static void iteratorAnnotations(Method method) {

        // 判断 somebody() 方法是否包含MyAnnotation注解
        if(method.isAnnotationPresent(MyAnnotation.class)){
            // 获取该方法的MyAnnotation注解实例
            MyAnnotation myAnnotation = method.getAnnotation(MyAnnotation.class);
            // 获取 myAnnotation的值，并打印出来
            String[] values = myAnnotation.value();
            for (String str:values)
                System.out.printf(str+", ");
            System.out.println();
        }

        // 获取方法上的所有注解，并打印出来
        Annotation[] annotations = method.getAnnotations();
        for(Annotation annotation : annotations){
            System.out.println(annotation);
        }
    }
}
