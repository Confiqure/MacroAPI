package com.github.confiqure.util;

/**
 *
 * Methods relating to time.
 * 
 * @author Dylan Wheeler
 */
public class Time {
    
    /**
     *
     * Sleeps for specified amount of time. Method utilizes dynamic sleep.
     * 
     * @param millis milliseconds to sleep
     * @return true if no exceptions were thrown
     */
    public static boolean sleep(final long millis) {
        try {
            if (millis > 1000) {
                final long start = getMillis();
                while (getMillis() < start + millis) {
                    Thread.sleep(100);
                }
            } else {
                Thread.sleep(millis);
            }
        } catch (final InterruptedException ex) {
            return false;
        }
        return true;
    }
    
    /**
     *
     * Rather than using System#getTimeMillis() to keep track of time which can be altered, this method utilizes System#nanoTime() to return a number of milliseconds.
     * 
     * @return an arbitrary amount of milliseconds based on current time
     */
    public static long getMillis() {
        return System.nanoTime() / 1000000;
    }
    
}
