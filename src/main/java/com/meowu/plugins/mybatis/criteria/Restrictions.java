package com.meowu.plugins.mybatis.criteria;

import com.meowu.commons.security.page.PageRequest;
import com.meowu.commons.utils.AssertUtils;
import com.meowu.commons.utils.ReflectionUtils;
import com.meowu.commons.utils.SpellUtils;
import com.meowu.plugins.mybatis.mysql.utils.FieldUtils;

import java.util.Arrays;
import java.util.List;

public class Restrictions{

    public static Criterion eq(String field, Object value){
        return new Criterion(getField(field)).eq(value);
    }

    public static <T, R> Criterion eq(ReflectionUtils.SerializableFunction<T, R> function, Object value){
        return new Criterion(getField(function)).eq(value);
    }

    public static Criterion ne(String field, Object value){
        return new Criterion(getField(field)).ne(value);
    }

    public static <T, R> Criterion ne(ReflectionUtils.SerializableFunction<T, R> function, Object value){
        return new Criterion(getField(function)).ne(value);
    }

    public static Criterion lt(String field, Object value){
        return new Criterion(getField(field)).lt(value);
    }

    public static <T, R> Criterion lt(ReflectionUtils.SerializableFunction<T, R> function, Object value){
        return new Criterion(getField(function)).lt(value);
    }

    public static Criterion le(String field, Object value){
        return new Criterion(getField(field)).le(value);
    }

    public static <T, R> Criterion le(ReflectionUtils.SerializableFunction<T, R> function, Object value){
        return new Criterion(getField(function)).le(value);
    }

    public static Criterion gt(String field, Object value){
        return new Criterion(getField(field)).gt(value);
    }

    public static <T, R> Criterion gt(ReflectionUtils.SerializableFunction<T, R> function, Object value){
        return new Criterion(getField(function)).gt(value);
    }

    public static Criterion ge(String field, Object value){
        return new Criterion(getField(field)).ge(value);
    }

    public static <T, R> Criterion ge(ReflectionUtils.SerializableFunction<T, R> function, Object value){
        return new Criterion(getField(function)).ge(value);
    }

    public static Criterion like(String field, Object value){
        return new Criterion(getField(field)).like("%" + value + "%");
    }

    public static <T, R> Criterion like(ReflectionUtils.SerializableFunction<T, R> function, Object value){
        return new Criterion(getField(function)).like(value);
    }

    public static Criterion notLike(String field, Object value){
        return new Criterion(getField(field)).notLike("%" + value + "%");
    }

    public static <T, R> Criterion notLike(ReflectionUtils.SerializableFunction<T, R> function, Object value){
        return new Criterion(getField(function)).notLike(value);
    }

    public static Criterion between(String field, Object first, Object second){
        return new Criterion(getField(field)).between(first, second);
    }

    public static <T, R> Criterion between(ReflectionUtils.SerializableFunction<T, R> function, Object first, Object second){
        return new Criterion(getField(function)).between(first, second);
    }

    public static Criterion notBetween(String field, Object first, Object second){
        return new Criterion(getField(field)).notBetween(first, second);
    }

    public static <T, R> Criterion notBetween(ReflectionUtils.SerializableFunction<T, R> function, Object first, Object second){
        return new Criterion(getField(function)).notBetween(first, second);
    }

    public static Criterion in(String field, List<Object> value){
        return new Criterion(getField(field)).in(value);
    }

    public static <T, R> Criterion in(ReflectionUtils.SerializableFunction<T, R> function, List<Object> value){
        return new Criterion(getField(function)).in(value);
    }

    public static Criterion notIn(String field, List<Object> value){
        return new Criterion(getField(field)).notIn(value);
    }

    public static <T, R> Criterion notIn(ReflectionUtils.SerializableFunction<T, R> function, List<Object> value){
        return new Criterion(getField(function)).notIn(value);
    }

    public static Criterion regexp(String field, Object value){
        return new Criterion(getField(field)).regexp(value);
    }

    public static <T, R> Criterion regexp(ReflectionUtils.SerializableFunction<T, R> function, Object value){
        return new Criterion(getField(function)).regexp(value);
    }

    public static Criterion isNull(String field){
        return new Criterion(getField(field)).isNull();
    }

    public static <T, R> Criterion isNull(ReflectionUtils.SerializableFunction<T, R> function){
        return new Criterion(getField(function)).isNull();
    }

    public static Criterion notNull(String field){
        return new Criterion(getField(field)).notNull();
    }

    public static <T, R> Criterion notNull(ReflectionUtils.SerializableFunction<T, R> function){
        return new Criterion(getField(function)).notNull();
    }

    public static Criterion and(Criterion... criteria){
        return new Criterion().and(criteria);
    }

    public static Criterion or(Criterion... criteria){
        return new Criterion().or(criteria);
    }

    public static Criterion limit(PageRequest pageRequest){
        return new Criterion().limit(pageRequest.getOffset(), pageRequest.getPageSize());
    }

    public static Criterion groupBy(String... fields){
        return new Criterion(getFields(fields)).groupBy();
    }

    public static <T, R> Criterion groupBy(ReflectionUtils.SerializableFunction<T, R>... functions){
        return new Criterion(getFields(functions)).groupBy();
    }

    public static Criterion asc(String... fields){
        return new Criterion(getFields(fields)).asc();
    }

    public static <T, R> Criterion asc(ReflectionUtils.SerializableFunction<T, R>... functions){
        return new Criterion(getFields(functions)).asc();
    }

    public static Criterion desc(String... fields){
        return new Criterion(getFields(fields)).desc();
    }

    public static <T, R> Criterion desc(ReflectionUtils.SerializableFunction<T, R>... functions){
        return new Criterion(getFields(functions)).desc();
    }

    private static String getField(String field){
        AssertUtils.hasText(field, "computed field must not be null");

        return SpellUtils.underline(field);
    }

    private static <T, R> String getField(ReflectionUtils.SerializableFunction<T, R> function){
        AssertUtils.notNull(function, "function must not be null");

        return FieldUtils.getColumnName(function);
    }

    private static String[] getFields(String... fields){
        AssertUtils.isNotEmpty(fields, "computed fields must not be empty");

        return Arrays.stream(fields)
                     .map(field -> getField(field))
                     .toArray(String[]::new);
    }

    private static <T, R> String[] getFields(ReflectionUtils.SerializableFunction<T, R>... functions){
        AssertUtils.isNotEmpty(functions, "functions must not be empty");

        return FieldUtils.getColumnNames(functions);
    }
}
