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
    
    /**
     *
     * Creates new instance of Keyboard.
     * 
     * @throws AWTException if the Robot could not be instanced
     * @see java.awt.Robot;
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
        Time.sleep(50);
        r.keyPress(key2);
        Time.sleep(50);
        r.keyRelease(key2);
        Time.sleep(50);
        r.keyRelease(key1);
    }
    
    /**
     *
     * Pastes specific text.
     * 
     * @param text text to paste
     */
    public void paste(final String text) {
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(text), null);
        comboType(KeyEvent.VK_CONTROL, KeyEvent.VK_V);
    }
    
    /**
     *
     * Presses and releases a key.
     * 
     * @param key key to type
     */
    public void type(final int key) {
        r.keyPress(key);
        Time.sleep(50);
        r.keyRelease(key);
    }
    
}
