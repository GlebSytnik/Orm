package com.example.orm.reflection;

import com.example.orm.annotation.OrmDatabase;
import com.example.orm.exception.DatabaseNotFoundException;
import com.example.orm.exception.TableClassNotFoundException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class DatabaseReflection {
    Class database;

    public DatabaseReflection(Class database) {
        this.database = database;
    }

    public DatabaseConnectionEntity toDatabaseConnectionEntity() throws DatabaseNotFoundException {
        if (database.isAnnotationPresent(OrmDatabase.class)){
            OrmDatabase ormDatabase = (OrmDatabase) database.getAnnotation(OrmDatabase.class);
            return new DatabaseConnectionEntity(ormDatabase.type(), ormDatabase.extendType(), ormDatabase.host(),ormDatabase.username(),ormDatabase.password(),ormDatabase.openDatabase(),ormDatabase.serverTimezone());
        }
        else {
            throw new DatabaseNotFoundException("Database annotation not found.", database);
        }
    }

    /**
     * 通过数据库类来获得数据库实体
     * 主要是填充数据库的表数据
     * 数据中包含表的类列表和表的信息列表
     * @return
     * @throws TableNotFoundException
     */
    public DatabaseEntity toDatabaseEntity() throws TableNotFoundException {
        DatabaseEntity  databaseEntity = new DatabaseEntity(database);
        for(Field field: database.getDeclaredFields()){
            field.setAccessible(true);
            if (field.isAnnotationPresent(OrmTable.class)){
                try {
                    databaseEntity.addTableEntity(new TableReflection(field.getType()).toTableEntity());
                    databaseEntity.addTableClass(field.getType());
                } catch (TableItemNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        if (databaseEntity.getSize() == 0){
            throw new TableNotFoundException("Table not found in this database class", database);
        }
        return databaseEntity;
    }

    public List<Class> getTableClass() throws TableClassNotFoundException {
        List<Class> clazz = new ArrayList<>();
        for (Field field:database.getDeclaredFields()){
            field.setAccessible(true);
            if(field.isAnnotationPresent(OrmTable.class)){
                clazz.add(field.getType());
            }
        }
        if (clazz.size() == 0){
            throw new TableClassNotFoundException("Table class not found in this database class", database);
        }
        return clazz;
    }

    public static void main(String[] args) {

    }
}