package com.example.demo.test;

import com.example.demo.annotations.DColumn;
import com.example.demo.annotations.DTable;
import com.example.demo.enums.ColumnTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
*@Description 根据实体类生成DDL
*@Author glliu
*@Date 2020/12/11
*/
@Slf4j
public class GeneratorEntity {

    /**
     * 生成DDL方法
     */
    public static String generator(String className) throws ClassNotFoundException {
        //获取实体类的class文件
        Class<?> clazz = Class.forName(className);
        //获取实体类上面的注解
        DTable annotation = clazz.getAnnotation(DTable.class);
        if (ObjectUtils.isEmpty(annotation)) {
            log.error("未发现@DTable注解");
            throw new RuntimeException("未发现DTable注解");
        }
        //获取注解的value值
        String value = annotation.value();
        if (StringUtils.isBlank(value)) {
            log.error("未发现@DTable注解");
            throw new RuntimeException("注解:" + clazz.getName() + "值不能为空");
        }
        List<String> columns = new LinkedList<>();
        //获取该实体类所有的字段
        Field[] declaredFields = clazz.getDeclaredFields();
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE '").append(value).append("'(").append("\n");
        first:
        for (Field declaredField : declaredFields) {
            String columnName = "";
            //获取字段上的所有注解
            Annotation[] declaredAnnotations = declaredField.getDeclaredAnnotations();
            if (ObjectUtils.isEmpty(declaredAnnotations) || declaredAnnotations.length < 1) {
                continue;
            }
            second:
            for (Annotation declaredAnnotation : declaredAnnotations) {
                if (declaredAnnotation instanceof DColumn) {
                    //获取字段上的注解
                    DColumn dColumn = (DColumn) declaredAnnotation;
                    if (StringUtils.isBlank(dColumn.columnType()) || dColumn.length() == 0) {
                        throw new RuntimeException("注解字段赋值有问题，请修改");
                    }
                    //拼接注解中name属性
                    String name = dColumn.name();
                    if (StringUtils.isBlank(name)) {
                        sb.append("'").append(declaredField.getName().replaceAll("[A-Z]", "_$0").toLowerCase()).append("'");
                    } else {
                        sb.append("'").append(name.replaceAll("[A-Z]", "_$0").toLowerCase()).append("'");
                    }
                    //拼接columnType
                    String columnType = dColumn.columnType();
                    if (StringUtils.isBlank(columnType)) {
                        throw new RuntimeException("字段类型不能为空");
                    }
                    sb.append(columnType);
                    //拼接length
                    int length = dColumn.length();
                    if (length == 0 && declaredField.getType().equals(Date.class)) {
                        throw new RuntimeException("字段长度不可为空");
                    }
                    sb.append("(").append(String.valueOf(length)).append(") ");
                    //拼接canBeEnpty
                    boolean canBeEmpty = dColumn.canBeEmpty();
                    if (canBeEmpty) {
                        sb.append("DEFAULT NULL ");
                    } else {
                        sb.append("NOT NULL ");
                    }
                    //拼接comment
                    String comment = dColumn.comment();
                    if (StringUtils.isNotBlank(comment)) {
                        sb.append("'").append(comment).append("'");
                    }
                }
                //拼接逗号
                if (!declaredFields[declaredFields.length - 1].equals(declaredField)) {
                    sb.append(",");
                }
                sb.append("\n");
            }
        }
        sb.append(")");
        return sb.toString();
    }

    public static void main(String[] args) throws ClassNotFoundException {
        String generator = generator("com.example.demo.entity.Member");
        log.info("获取到的DDL：{}", generator);
    }
}
