package kr.co.mvp.dagger2.sample.utils;

public final class StringUtils {
    private StringUtils() {
    }

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }


    public static String setComma(String value) {
        if (value == null || value.length() == 0) {
            return "0";
        }
        return String.format("%,d", Integer.parseInt(value));

    }

    public static String transferNull(String value) {
        if (isNullOrEmpty(value)) {
            return "";
        } else {
            return value;
        }
    }

    public static String getConvertSize(long size) {
        String converString = "";

        long minimumMegaSize = 1048576L;
        if (size >= minimumMegaSize) {
            float mbSize = size / minimumMegaSize;
            converString = String.format("%.2f", mbSize) + "MB";
        } else {
            float kbSize = size / 1024;
            converString = String.format("%.2f", kbSize) + "KB";
        }


        return converString;
    }



}
