package com.github.confiqure.input;

import com.github.confiqure.util.Time;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

/**
 *
 * Class for methods relating to the Keyboard input.
 * 
 * @author Dylan Wheeler
 */
public class Keyboard {
    
    private final Robot r;
    private long PRESS_SLEEP = 50;
    
    /**
     *
     * Creates new instance of Keyboard.
     * 
     * @throws AWTException if the Robot could not be instanced
     * @see java.awt.Robot
     */
    public Keyboard() throws AWTException {
        r = new Robot();
    }
    
    /**
     *
     * Presses and releases two keys at the same time.
     * 
     * @param key1 first key to type
     * @param key2 second key to type
     */
    public void comboType(final int key1, final int key2) {
        r.keyPress(key1);
        Time.sleep(PRESS_SLEEP);
        r.keyPress(key2);
        Time.sleep(PRESS_SLEEP);
        r.keyRelease(key2);
        Time.sleep(PRESS_SLEEP);
        r.keyRelease(key1);
    }
    
    /**
     *
     * Pastes specific text.
     * 
     * @param text text to paste
     */
    public void paste(final String text) {
        paste(text, false);
    }
    
    /**
     *
     * Pastes specific text.
     * 
     * @param text text to paste
     * @param enter create a new line after pasting text
     */
    public void paste(final String text, final boolean enter) {
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(text), null);
        comboType(KeyEvent.VK_CONTROL, KeyEvent.VK_V);
        if (enter) type(KeyEvent.VK_ENTER);
    }
    
    /**
     *
     * Presses and releases a key.
     * 
     * @param key key to type
     */
    public void type(final int key) {
        r.keyPress(key);
        Time.sleep(PRESS_SLEEP);
        r.keyRelease(key);
    }
    
    /**
     *
     * Sets the time to sleep after pressing a key.
     * 
     * @param sleep milliseconds to sleep: 50ms by default
     */
    public void setPressSleep(final long sleep) {
        PRESS_SLEEP = sleep;
    }
    
}
