package com.example.annotations.table;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class TableCreator {

    public static String createTableSql(String className) throws ClassNotFoundException {
        Class<?> cl = Class.forName(className);
        DBTable dbTable = cl.getAnnotation(DBTable.class);
        //如果没有表注解，直接返回
        if (dbTable == null) {
            System.out.println("No DBTable annotations in class" + className);
            return null;
        }
        String tableName = dbTable.name();
        if (tableName.length() < 1) {
            tableName = cl.getName().toUpperCase();
            List<String> columnDefs = new ArrayList<>();
            for (Field field : cl.getDeclaredFields()) {
                String columnName = null;
                //获取字段上的注解
                Annotation[] anns = field.getDeclaredAnnotations();
                if (null != anns) {
                    if (anns.length < 1) {
                        continue;
                    }
                    if (anns[0] instanceof SQLInteger) {
                        SQLInteger sInt = (SQLInteger) anns[0];
                        if (sInt.value().length() < 1) {
                            columnName = field.getName().toUpperCase();
                        } else {
                            columnName = sInt.value();
                        }
                        columnDefs.add(columnName + "INT" + getConstraints(sInt.constraint()));
                    }
                    //判断String类型
                    if (anns[0] instanceof SQLString) {
                        SQLString sString = (SQLString) anns[0];
                        if (sString.name().length() < 1) {
                            columnName = field.getName().toUpperCase();
                        } else {
                            columnName = sString.name();
                        }
                        columnDefs.add(columnName + " VARCHAR(" +
                                sString.value() + ")" +
                                getConstraints(sString.constraint()));
                    }
                }

                StringBuilder createCommand = new StringBuilder(
                        "CREATE TABLE " + tableName + "(");
                for (String columnDef : columnDefs) {
                    createCommand.append("\n    " + columnDef + ",");
                }

                String tableCreate = createCommand.substring(
                        0, createCommand.length() - 1) + ");";
                return tableCreate;
            }
        }
        return null;

    }

    private static String getConstraints(Constraints con){
        String constraints = "";
        if(!con.allowNull())
            constraints += " NOT NULL";
        if(con.primaryKey())
            constraints += " PRIMARY KEY";
        if(con.unique())
            constraints += " UNIQUE";
        return constraints;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        String[] arg={"com.example.annotations.table.Member"};
        for(String className : arg) {
            System.out.println("Table Creation SQL for " +
                    className + " is :\n" + createTableSql(className));
        }
    }

}
