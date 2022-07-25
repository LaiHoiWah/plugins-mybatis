package com.meowu.plugins.mybatis.mysql.utils;

import com.meowu.commons.utils.ReflectionUtils;
import com.meowu.commons.utils.SpellUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;

public class FieldUtils{

    public static <T, R> String getColumnName(ReflectionUtils.SerializableFunction<T, R> function){
        Field field = ReflectionUtils.getField(function);

        com.meowu.plugins.mybatis.mysql.stereotype.annotation.Field annotation = ReflectionUtils.getAnnotation(field, com.meowu.plugins.mybatis.mysql.stereotype.annotation.Field.class);

        if(annotation == null || StringUtils.isBlank(annotation.value())){
            return SpellUtils.underline(field.getName());
        }else{
            return annotation.value();
        }
    }

    public static <T, R> String[] getColumnNames(ReflectionUtils.SerializableFunction<T, R>... functions){
        String[] fields = new String[functions.length];

        for(int i = 0; i < functions.length; i++){
            fields[i] = getColumnName(functions[i]);
        }

        return fields;
    }
}
