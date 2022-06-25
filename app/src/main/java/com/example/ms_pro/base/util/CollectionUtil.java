package com.example.ms_pro.base.util;

import java.util.Collection;

/**
 * Created by anhth on 1/18/18.
 */
public class CollectionUtil {
    public static boolean isEmpty(final Collection<?> c) {
        return c == null || c.isEmpty();
    }
}
