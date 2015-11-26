package com.github.confiqure.io;

import java.awt.Toolkit;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

/**
 *
 * Class for getting and setting data to the user's clipboard.
 * 
 * @author Dylan Wheeler
 */
public class Clipboard {
    
    /**
     *
     * Reads the system clipboard contents as a String.
     * 
     * @return clipboard contents or null if there was an error thrown
     */
    public static String get() {
        try {
            return (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
        } catch (final IOException | UnsupportedFlavorException ex) {
            return null;
        }
    }
    
    /**
     *
     * Sets the system clipboard contents.
     * 
     * @param contents value to change the clipboard to
     */
    public static void set(final String contents) {
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(contents), null);
    }
    
    
    /**
     *
     * Sets the system clipboard contents as well as defining a new ClipboardOwner.
     * 
     * @param contents value to change the clipboard to
     * @param owner owner of new clipboard contents
     * @see java.awt.datatransfer.ClipboardOwner
     */
    public static void set(final String contents, final ClipboardOwner owner) {
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(contents), owner);
    }
    
}
