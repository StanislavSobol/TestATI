package com.sobolgmail.i.stanislav.testati.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (c) 2016 StreamLoan. All rights reserved.
 * Created on 27/12/2016
 */

public class StringUtils {

    /**
     * Environment independent analog of TextUtils.isEmpty(String s)
     *
     * @param src incoming string
     * @return true src equals NULL or ""
     */
    public static boolean isEmpty(final String src) {
        return src == null || src.equals("");
    }

    /**
     * Slices the string into equal parts
     *
     * @param src       incoming string
     * @param sliceSize size of each part
     * @return the list of sliced strings
     */
    public static List<String> sliceString(final String src, final int sliceSize) {
        final List<String> result = new ArrayList<>();
        if (!isEmpty(src)) {
            if (sliceSize == 0) {
                result.add(src);
            } else {
                final int size = src.length() / sliceSize + 1;
                for (int i = 0; i < size; i++) {
                    String ss;
                    try {
                        ss = src.substring(i * sliceSize, (i + 1) * sliceSize);
                    } catch (java.lang.StringIndexOutOfBoundsException e) {
                        ss = src.substring(i * sliceSize);
                    }
                    result.add(ss);
                }
            }
        }
        return result;
    }

}
