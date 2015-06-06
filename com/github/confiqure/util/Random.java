package com.github.confiqure.util;

/**
 *
 * Custom methods relating to randomness.
 * 
 * @author Dylan Wheeler
 */
public class Random {
    
    private final java.util.Random r;
    
    /**
     *
     * Creates new instance of Random.
     * 
     */
    public Random() {
        r = new java.util.Random();
    }
    
    /**
     *
     * Generates a random number between the ranges specified, inclusive.
     * 
     * @param min the minimum number to be generated
     * @param max the maximum number to be generated
     * @return a random number between the ranges specified, inclusive
     */
    public int random(final int min, final int max) {
        return min + (int) (r.nextDouble() * (max - min));
    }
    
}
