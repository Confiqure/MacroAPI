package com.github.confiqure.input;

import com.github.confiqure.util.Time;
import java.awt.AWTException;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.MouseEvent;

/**
 *
 * Class for methods relating to the Mouse input.
 * 
 * @author Dylan Wheeler
 */
public class Mouse {
    
    private final Robot r;
    private long CLICK_SLEEP = 50, MOVE_SLEEP = 100;
    
    /**
     *
     * Creates new instance of Mouse.
     * 
     * @throws AWTException if the Robot could not be instanced
     * @see java.awt.Robot
     */
    public Mouse() throws AWTException {
        r = new Robot();
    }
    
    /**
     *
     * Clicks the mouse at the top left corner of a Rectangle and drags it to the bottom right corner.
     * 
     * @param selection the selection of pixels to drag the mouse across
     * @see java.awt.Rectangle
     */
    public void drag(final Rectangle selection) {
        drag(selection.x, selection.y, selection.x + selection.width, selection.y + selection.height);
    }
    
    /**
     *
     * Clicks the mouse at one point and drags it to another point.
     * 
     * @param x x-coordinate for press event to occur
     * @param y y-coordinate for press event to occur
     * @param x2 x-coordinate for release event to occur
     * @param y2 y-coordinate for release event to occur
     */
    public void drag(final int x, final int y, final int x2, final int y2) {
        move(x, y);
        r.mousePress(MouseEvent.BUTTON1_MASK);
        Time.sleep(CLICK_SLEEP);
        move(x2, y2);
        r.mouseRelease(MouseEvent.BUTTON1_MASK);
        Time.sleep(CLICK_SLEEP);
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
     * Clicks the Mouse.
     * 
     * @param left true for left click, false for right click
     */
    public void click(final boolean left) {
        if (left) {
            r.mousePress(MouseEvent.BUTTON1_MASK);
            Time.sleep(CLICK_SLEEP);
            r.mouseRelease(MouseEvent.BUTTON1_MASK);
        } else {
            r.mousePress(MouseEvent.BUTTON3_MASK);
            Time.sleep(CLICK_SLEEP);
            r.mouseRelease(MouseEvent.BUTTON3_MASK);
        }
    }
    
    /**
     *
     * Left clicks the Mouse at a specific Point on the screen.
     * 
     * @param p Point for click event to occur
     * @see java.awt.Point
     */
    public void click(final Point p) {
        click(p.x, p.y);
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
        click(left);
    }
    
    /**
     *
     * Moves Mouse to a specific Point on the screen.
     * 
     * @param p Point to move to
     * @see java.awt.Point
     */
    public void move(final Point p) {
        move(p.x, p.y);
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
        Time.sleep(MOVE_SLEEP);
    }
    
    /**
     *
     * Sets the time to sleep in between mouse clicks.
     * 
     * @param sleep milliseconds to sleep: 50ms by default
     */
    public void setClickSleep(final long sleep) {
        CLICK_SLEEP = sleep;
    }
    
    /**
     *
     * Sets the time to sleep after moving the mouse.
     * 
     * @param sleep milliseconds to sleep: 100ms by default
     */
    public void setMoveSleep(final long sleep) {
        MOVE_SLEEP = sleep;
    }
    
}
