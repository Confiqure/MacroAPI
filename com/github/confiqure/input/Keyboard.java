package com.github.confiqure.input;

import com.github.confiqure.io.Clipboard;
import com.github.confiqure.util.Time;
import java.awt.AWTException;
import java.awt.Robot;
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
     * Pastes specific text. Compatible with Macintosh, Windows, and Linux operating systems.
     * 
     * @param text text to paste
     * @param enter create a new line after pasting text
     * @return true if text pasted successfully
     */
    public boolean paste(final String text, final boolean enter) {
        Clipboard.set(text);
        if (System.getProperty("os.name").contains("Mac")) {
            comboType(KeyEvent.VK_META, KeyEvent.VK_V);
            if (enter) type(KeyEvent.VK_ENTER);
            return true;
        } else if (System.getProperty("os.name").contains("Windows") || System.getProperty("os.name").contains("Linux")) {
            comboType(KeyEvent.VK_CONTROL, KeyEvent.VK_V);
            if (enter) type(KeyEvent.VK_ENTER);
            return true;
        }
        return false;
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
     * Uses quit hotkey. Compatible with Macintosh, Windows, and Linux operating systems.
     * 
     * @return true if hotkey was applied, false if not
     */
    public boolean quit() {
        if (System.getProperty("os.name").contains("Mac")) {
            comboType(KeyEvent.VK_META, KeyEvent.VK_Q);
            return true;
        } else if (System.getProperty("os.name").contains("Windows")) {
            comboType(KeyEvent.VK_ALT, KeyEvent.VK_F4);
            return true;
        } else if (System.getProperty("os.name").contains("Linux")) {
            comboType(KeyEvent.VK_CONTROL, KeyEvent.VK_Q);
            return true;
        }
        return false;
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
