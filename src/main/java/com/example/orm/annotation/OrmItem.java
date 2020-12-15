package com.example.orm.annotation;

import com.example.orm.enums.ItemTypeEnums;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface OrmItem {
    ItemTypeEnums type();
    long length();
    boolean isPrimaryKey() default false;
}