package com.sinkiang.ddd.sample.common.id.firework;

import java.util.HashMap;
import java.util.Map;

/**
 * @author dengxj
 * @date 2022/7/20 14:39
 */
class FireWorkSubString {
    private static String strArray = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final Map<Character, Integer> CHARACTER_INTEGER_MAP = new HashMap<>(16);
    private final char[] value;

    public static void setStrArray(String str) {
        strArray = str;
    }

    public static int len() {
        return strArray.length();
    }

    private FireWorkSubString(char[] value) {
        this.value = value;
    }

    public static FireWorkSubString of(String value) {
        return new FireWorkSubString(value.toCharArray());
    }

    public static FireWorkSubString of(long value, int len) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < len; ++i) {
            if (value != 0L) {
                int charIndex = (int) (value % (long) strArray.length());
                sb.append(strArray.charAt(charIndex));
                value /= strArray.length();
            } else {
                sb.append(strArray.charAt(0));
            }
        }

        return new FireWorkSubString(sb.reverse().toString().toCharArray());
    }

    public void addAtFixLen(long add) {
        if (add != 0L) {
            long sum = add;

            for (int i = 0; i < this.value.length; ++i) {
                int index = this.value.length - i - 1;
                Integer integer = CHARACTER_INTEGER_MAP.get(this.value[index]);
                sum += (long) integer;
                int charIndex = (int) (sum % (long) strArray.length());
                if (charIndex < 0) {
                    charIndex += strArray.length();
                    sum -= strArray.length();
                }

                this.value[index] = strArray.charAt(charIndex);
                sum /= strArray.length();
                if (sum == 0L) {
                    break;
                }
            }

        }
    }

    @Override
    public String toString() {
        return new String(this.value);
    }

    public long toLong() {
        long ret = 0L;

        for (char c : this.value) {
            Integer integer = CHARACTER_INTEGER_MAP.get(c);
            ret = ret * (long) strArray.length() + (long) integer;
        }

        return ret;
    }

    static {
        for (int i = 0; i < strArray.length(); ++i) {
            CHARACTER_INTEGER_MAP.put(strArray.charAt(i), i);
        }

    }
}
