package com.github.confiqure.util;

/**
 *
 * Helper class to format Strings.
 * 
 * @author Dylan Wheeler
 */
public class Format {
    
    /**
     *
     * Adds commas into a whole number recursively.
     * 
     * @param str number to insert commas into (number must be whole and numeric)
     * @return the number formatted with commas
     */
    public static String addCommas(final String str) {
        if (!str.matches("^[0-9]+$") || str.length() < 4) return str;
        return addCommas(str.substring(0, str.length() - 3)) + "," + str.substring(str.length() - 3, str.length());
    }
    
    /**
     *
     * Formats time into hh:mm:ss format.
     * 
     * @param ms time to convert
     * @return converted time
     */
    public static String time(final long ms) {
        String str = "";
        final long s = ms / 1000;
        final long m = s / 60;
        final int secs = (int) s % 60;
        final int mins = (int) m % 60;
        final int hours = (int) (m / 60);
        str += hours + ":";
        if (mins <= 9) str += "0";
        str += mins + ":";
        if (secs <= 9) str += "0";
        return str + secs;
    }
    
    /**
     *
     * Converts a time countdown into something more readable.
     * 
     * @param ms time left
     * @return formatted time
     */
    public static String countdown(final long ms) {
        if (ms <= 0) {
            return "Completed";
        }
        if (ms < 60000) {
            String time = Math.ceil(ms / 1000D) + "";
            if (time.endsWith(".0")) {
                time = time.substring(0, time.length() - 2);
            }
            if (time.equals("1")) {
                return "1 sec";
            }
            return time + " secs";
        } else if (ms < 3600000) {
            String time = round(ms / 60000D) + "";
            if (time.endsWith(".0")) {
                time = time.substring(0, time.length() - 2);
            }
            if (time.equals("1")) {
                return "1 min";
            }
            return time + " mins";
        } else if (ms < 3600000000L) {
            String time = round(ms / 3600000D) + "";
            if (time.endsWith(".0")) {
                time = time.substring(0, time.length() - 2);
            }
            if (time.equals("1")) {
                return "1 hour";
            }
            return time + " hours";
        } else {
            String time = addCommas(Math.round(ms / 3600000D) + "");
            return time + " hours";
        }
    }

    /**
     *
     * Rounds a double to the tenths place.
     * 
     * @param d unrounded number
     * @return rounded number
     */
    public static double round(final double d) {
        return round(d, 1);
    }

    /**
     *
     * Rounds a double.
     * 
     * @param d unrounded number
     * @param factor number of decimal places to round to (0 for whole number)
     * @return rounded number
     */
    public static double round(final double d, final int factor) {
        if (factor < 0) return d;
        return ((int) Math.round(d * Math.pow(10, factor))) / Math.pow(10, factor);
    }
    
}
