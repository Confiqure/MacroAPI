package com.github.confiqure.input;

import com.github.confiqure.util.Time;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.MouseEvent;

/**
 *
 * Class for methods relating to the Mouse input.
 * 
 * @author Dylan Wheeler
 */
public class Mouse {
    
    private Robot r = null;
    
    /**
     *
     * Creates new instance of Mouse.
     * 
     * @throws AWTException if the Robot could not be instanced.
     * @see java.awt.Robot;
     */
    public Mouse() throws AWTException {
        r = new Robot();
    }
    
    /**
     *
     * Left clicks the Mouse.
     * 
     */
    public void click() {
        click(true);
    }
    
    /**
     *
     * Left clicks the Mouse at a specific point on the screen.
     * 
     * @param x x-coordinate for click event to occur
     * @param y y-coordinate for click event to occur
     */
    public void click(final int x, final int y) {
        move(x, y);
        Time.sleep(100);
        click(true);
    }
    
    /**
     *
     * Clicks the Mouse at a specific point on the screen.
     * 
     * @param x x-coordinate for click event to occur
     * @param y y-coordinate for click event to occur
     * @param left true for left click, false for right click
     */
    public void click(final int x, final int y, final boolean left) {
        move(x, y);
        Time.sleep(100);
        click(left);
    }
    
    /**
     *
     * Clicks the Mouse.
     * 
     * @param left true for left click, false for right click
     */
    public void click(final boolean left) {
        if (left) {
            r.mousePress(MouseEvent.BUTTON1_MASK);
            Time.sleep(50);
            r.mouseRelease(MouseEvent.BUTTON1_MASK);
        } else {
            r.mousePress(MouseEvent.BUTTON3_MASK);
            Time.sleep(50);
            r.mouseRelease(MouseEvent.BUTTON3_MASK);
        }
    }
    
    /**
     *
     * Moves Mouse to a specific point on the screen.
     * 
     * @param x x-coordinate to move to
     * @param y y-coordinate to move to
     */
    public void move(final int x, final int y) {
        r.mouseMove(x, y);
    }
    
}
