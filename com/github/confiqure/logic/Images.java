package com.github.confiqure.logic;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

/**
 *
 * Class for methods to compute logic with images.
 * 
 * @author Dylan Wheeler
 */
public class Images {
    
    private final Robot r;
    
    /**
     *
     * Creates new instance of Images.
     * 
     * @throws AWTException if the Robot could not be instanced
     * @see java.awt.Robot;
     */
    public Images() throws AWTException {
        r = new Robot();
    }
    
    /**
     *
     * Creates a screen capture of the entire display.
     * 
     * @return a screen capture of the entire screen
     */
    public BufferedImage screenshot() {
        final Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        return r.createScreenCapture(new Rectangle(0, 0, d.width, d.height));
    }
    
    /**
     *
     * Creates a screen capture of a specific part of the display.
     * 
     * @param x x-coordinate to start the capture
     * @param y y-coordinate to start the capture
     * @param width width of the capture
     * @param height height of the capture
     * @return a screen capture of the specified rectangle of the screen
     */
    public BufferedImage screenshot(final int x, final int y, final int width, final int height) {
        return r.createScreenCapture(new Rectangle(x, y, width, height));
    }
    
    /**
     *
     * Checks if a larger BufferedImage contains a smaller BufferedImage.
     * 
     * @see java.awt.image.BufferedImage
     * @param haystack image to search
     * @param needle image to search for
     * @return true if the smaller image is contained within the larger image
     */
    public static boolean contains(final BufferedImage haystack, final BufferedImage needle) {
        return getContainsPoint(haystack, needle) != null;
    }
    
    /**
     *
     * Returns Point where the smaller BufferedImage is contained within the larger BufferedImage.
     * 
     * @see java.awt.image.BufferedImage
     * @param haystack image to search
     * @param needle image to search for
     * @return point where the smaller BufferedImage is represented in the larger BufferedImage or null if needle is not a subset of haystack
     */
    public static Point getContainsPoint(final BufferedImage haystack, final BufferedImage needle) {
        //loop through each of haystack's pixels
        for (int offX = 0; offX < haystack.getWidth() - needle.getWidth(); offX++) {
            for (int offY = 0; offY <= haystack.getHeight() - needle.getHeight(); offY++) {
                //loop through each of needle's pixels
                next: for (int imgX = 0; imgX < needle.getWidth(); imgX++) {
                    for (int imgY = 0; imgY < needle.getHeight(); imgY++) {
                        //if there is a discrepancy, break out and move on to next set of pixels
                        if (haystack.getRGB(imgX + offX, imgY + offY) != needle.getRGB(imgX, imgY)) {
                            break next;
                        }
                    }
                    //if we've reached the end, return true
                    if (imgX + 1 == needle.getWidth()) {
                        return new Point(offX, offY);
                    }
                }
            }
        }
        return null;
    }
    
    /**
     *
     * Determines if two BufferedImage objects are the same.
     * 
     * @see java.awt.image.BufferedImage
     * @param a first image to compare
     * @param b second image to compare
     * @return true if both sizes are equal and if all the corresponding pixels are the same colors
     */
    public static boolean equals(final BufferedImage a, final BufferedImage b) {
        if (a.getWidth() != b.getWidth() || a.getHeight() != b.getHeight()) {
            return false;
        }
        for (int x = 0; x < a.getWidth(); x++) {
            for (int y = 0; y < a.getHeight(); y++) {
                if (a.getRGB(x, y) != b.getRGB(x, y)) {
                    return false;
                }
            }
        }
        return true;
    }
    
}
