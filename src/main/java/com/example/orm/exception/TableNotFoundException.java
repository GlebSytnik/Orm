package com.example.orm.exception;

/**
 * @author WangTingZheng
 * @date 2020/7/8 16:29
 * @features
 */
public class TableNotFoundException extends Exception{
    Class databse;

    public TableNotFoundException(String message, Class database) {
        super(message);
        this.databse = database;
    }

    public Class getDatabase() {
        return databse;
    }
}
