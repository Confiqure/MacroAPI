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
    
    private final Dimension d;
    private final Robot r;
    
    /**
     *
     * Creates new instance of Images.
     * 
     * @throws AWTException if the Robot could not be instanced
     * @see java.awt.Robot
     */
    public Images() throws AWTException {
        d = Toolkit.getDefaultToolkit().getScreenSize();
        r = new Robot();
    }
    
    /**
     *
     * Returns the color of a specific pixel on screen.
     * 
     * @param p Point of the pixel
     * @return the RGB value of the color in the default sRGB ColorModel
     * @see java.awt.Point
     */
    public int getColorOnScreen(final Point p) {
        return getColorOnScreen(p.x, p.y);
    }
    
    /**
     *
     * Returns the color of a specific pixel on screen.
     * 
     * @param x x-coordinate of the pixel
     * @param y y-coordinate of the pixel
     * @return the RGB value of the color in the default sRGB ColorModel
     */
    public int getColorOnScreen(final int x, final int y) {
        return r.getPixelColor(x, y).getRGB();
    }
        
    /**
     *
     * Creates a screen capture of the entire display.
     * 
     * @return a screen capture of the entire screen
     */
    public BufferedImage screenshot() {
        return r.createScreenCapture(new Rectangle(0, 0, d.width, d.height));
    }
    
    /**
     *
     * Creates a screen capture of a specific part of the display.
     * 
     * @param rect bounds to take screen shot
     * @return a screen capture of the entire screen
     * @see java.awt.Rectangle
     */
    public BufferedImage screenshot(final Rectangle rect) {
        return r.createScreenCapture(rect);
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
        return screenshot(new Rectangle(x, y, width, height));
    }
    
    /**
     *
     * Checks if a larger BufferedImage contains a smaller BufferedImage.
     * 
     * @param haystack image to search
     * @param needle image to search for
     * @return true if the smaller image is contained within the larger image
     * @see java.awt.image.BufferedImage
     */
    public static boolean contains(final BufferedImage haystack, final BufferedImage needle) {
        return getContainsPoint(haystack, needle) != null;
    }
    
    /**
     *
     * Checks if the current screen contains a smaller BufferedImage.
     * 
     * @param needle image to search for
     * @return true if the smaller image is contained within the current screen
     * @see java.awt.image.BufferedImage
     */
    public boolean contains(final BufferedImage needle) {
        return getContainsPoint(screenshot(), needle) != null;
    }
    
    /**
     *
     * Checks if an area on the current screen contains a smaller BufferedImage.
     * 
     * @param needle image to search for
     * @param area area on the screen to scan
     * @return true if the smaller image is contained within the area defined on the current screen
     * @see java.awt.image.BufferedImage
     */
    public boolean contains(final BufferedImage needle, final Rectangle area) {
        return getContainsPoint(screenshot(area), needle) != null;
    }
    
    /**
     *
     * Returns Point where the smaller BufferedImage is contained within the larger BufferedImage.
     * 
     * @param haystack image to search
     * @param needle image to search for
     * @return point where the smaller BufferedImage is represented in the larger BufferedImage or null if needle is not a subset of haystack
     * @see java.awt.image.BufferedImage
     */
    public static Point getContainsPoint(final BufferedImage haystack, final BufferedImage needle) {
        if (haystack == null || needle == null) return null;
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
     * Checks if a larger BufferedImage contains a pixel color.
     * 
     * @param haystack image to search
     * @param needle pixel RGB integer to search for
     * @return true if the pixel color is contained within the larger image
     * @see java.awt.image.BufferedImage
     */
    public static boolean contains(final BufferedImage haystack, final int needle) {
        if (haystack == null) return false;
        for (int x = 0; x < haystack.getWidth(); x++) {
            for (int y = 0; y < haystack.getHeight(); y++) {
                if (haystack.getRGB(x, y) == needle) return true;
            }
        }
        return false;
    }
    
    /**
     *
     * Determines if two BufferedImage objects are the same.
     * 
     * @param a first image to compare
     * @param b second image to compare
     * @return true if both sizes are equal and if all the corresponding pixels are the same colors
     * @see java.awt.image.BufferedImage
     */
    public static boolean equals(final BufferedImage a, final BufferedImage b) {
        if (a == null || b == null || a.getWidth() != b.getWidth() || a.getHeight() != b.getHeight()) {
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
