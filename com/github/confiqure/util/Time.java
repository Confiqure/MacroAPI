package com.github.confiqure.util;

import java.util.concurrent.Callable;

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
    
    /**
     *
     * Sleeps until a specific event occurs. This event is customizable through a Callable object.
     * 
     * @param waitFor the event to wait for
     * @param duration duration of time in milliseconds to sleep per iteration
     * @param iterations number of iterations to sleep before aborting
     * @return true if the event occurred or false if the maximum number of sleep cycles was reached, or if there was an error while sleeping
     * @see java.util.concurrent.Callable
     */
    public static boolean wait(final Callable<Boolean> waitFor, final long duration, final int iterations) {
        for (int i = 0; i < iterations; i++) {
            try {
                if (waitFor.call()) {
                    return true;
                }
                Thread.sleep(duration);
            } catch (final Exception ex) {
                return false;
            }
        }
        return false;
    }
    
}
