package com.example.ms_pro.base.util;

import android.text.InputFilter;
import android.text.Spanned;

public class MinMaxFilter implements InputFilter {

    private int mIntMin, mIntMax;
    private int maxLength;

    public MinMaxFilter(int minValue, int maxValue) {
        this.mIntMin = minValue;
        this.mIntMax = maxValue;
        if (mIntMax > mIntMin) {
            maxLength = String.valueOf(maxValue).length();
        } else {
            maxLength = String.valueOf(minValue).length();
        }
    }

    public MinMaxFilter(String minValue, String maxValue) {
        this.mIntMin = Integer.parseInt(minValue);
        this.mIntMax = Integer.parseInt(maxValue);
        if (mIntMax > mIntMin) {
            maxLength = maxValue.trim().length();
        } else {
            maxLength = minValue.trim().length();
        }
    }

    private boolean isInRange(int a, int b, int c) {
        return b > a ? c >= a && c <= b : c >= b && c <= a;
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        try {
            if (source.toString().length() >= maxLength) {
//                int input = Integer.parseInt(dest.toString() + source.toString());
//                if (isInRange(mIntMin, mIntMax, input))
                return null;
            }
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }
        return "";
    }
}
