package com.example.orm.annotation;

import com.example.orm.enums.DatabaseTypeEnum;
import com.example.orm.Simple;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface OrmDatabase {
    DatabaseTypeEnum type() default DatabaseTypeEnum.POSTGRES;
    Class extendType() default Simple.class;
    String host() default "localhost:5432";
    String username() default "djooky";
    String password() default "djooky";
    String openDatabase() default "sys";
    String serverTimezone() default "UTC";
}