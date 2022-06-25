package com.example.ms_pro.base.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by FRAMGIA\nguyen.duc.manh on 13/06/2017.
 */

public class ListOfJson<T> implements ParameterizedType {
    private Class<?> wrapped;

    public ListOfJson(Class<T> wrapper) {
        this.wrapped = wrapper;
    }

    @Override
    public Type[] getActualTypeArguments() {
        return new Type[]{wrapped};
    }

    @Override
    public Type getRawType() {
        return List.class;
    }

    @Override
    public Type getOwnerType() {
        return null;
    }
}