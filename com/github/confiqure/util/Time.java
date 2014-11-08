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
     * Rather than using System#getTimeMillis() to keep track of time which can be altered, this method utilizes System#nanoTime() to return a number of milliseconds.
     * 
     * @return an arbitrary amount of milliseconds based on current time
     */
    public static long millis() {
        return System.nanoTime() / 1000000;
    }
    
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
                final long start = millis();
                while (millis() < start + millis) {
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
    
}
