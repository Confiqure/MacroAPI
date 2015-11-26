package com.github.confiqure.io;

import com.github.confiqure.logic.Images;
import java.awt.Rectangle;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.imageio.ImageIO;

/**
 *
 * Helper class for managing different forms of logging.
 * 
 * @author Dylan Wheeler
 */
public class Logger {
    
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private File log = null;
    
    /**
     *
     * Creates new instance with default date format.
     * 
     * @param log the destination file to save logs to
     */
    public Logger(final File log) {
        this.log = log;
    }
    
    /**
     *
     * Creates new instance.
     * 
     * @param format formatting syntax for log messages
     * @see java.text.SimpleDateFormat
     */
    public Logger(final String format) {
        sdf = new SimpleDateFormat(format);
    }
    
    /**
     *
     * Creates new instance.
     * 
     * @param log the destination file to save logs to
     * @param format formatting syntax for log messages
     * @see java.text.SimpleDateFormat
     */
    public Logger(final File log, final String format) {
        this.log = log;
        sdf = new SimpleDateFormat(format);
    }
    
    /**
     *
     * Prints log message to console.
     * 
     * @param message message to print
     */
    public static void print(final String message) {
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " " + message.trim());
    }
    
    /**
     *
     * Prints message to console and then, if specified, logs it to file. The default directory is the current working directory.
     * 
     * @param message message to log
     */
    public void log(String message) {
        message = sdf.format(new Date()) + " " + message.trim();
        System.out.println(message);
        try (final PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(log == null ? "log.txt" : log.toString(), true)))) {
            out.println(message);
            out.close();
        } catch (final IOException e) {}
    }
    
    /**
     *
     * Logs a screenshot of the current screen to the same directory as the log file. The default directory is the current working directory.
     * 
     * @param images instance of the Images class to handle screenshots
     * @return true if the log was saved
     * @see com.github.confiqure.logic.Images
     */
    public boolean screenshot(final Images images) {
        try {
            if (log == null) {
                return ImageIO.write(images.screenshot(), "png", new File(new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date()) + ".png"));
            }
            return ImageIO.write(images.screenshot(), "png", new File(log.toString().substring(0, log.toString().lastIndexOf(File.separator)), new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date()) + ".png"));
        } catch (final IOException ex) {
            return false;
        }
    }
    
    /**
     *
     * Logs a screenshot of an area of the current screen to the same directory as the log file. The default directory is the current working directory.
     * 
     * @param images instance of the Images class to handle screenshots
     * @param area area of the screen to capture
     * @return true if the log was saved
     * @see com.github.confiqure.logic.Images
     */
    public boolean screenshot(final Images images, final Rectangle area) {
        try {
            if (log == null) {
                return ImageIO.write(images.screenshot(area), "png", new File(sdf.format(new Date()) + ".png"));
            }
            return ImageIO.write(images.screenshot(area), "png", new File(log.toString().substring(0, log.toString().lastIndexOf(File.separator)), sdf.format(new Date()) + ".png"));
        } catch (final IOException ex) {
            return false;
        }
    }
    
    /**
     *
     * Gets the currently configured date format.
     * 
     * @return date format
     * @see java.text.SimpleDateFormat
     */
    public SimpleDateFormat getDateFormat() {
        return sdf;
    }
    
    /**
     *
     * Gets the currently configured log file.
     * 
     * @return log file
     * @see java.io.File
     */
    public File getLogFile() {
        return log;
    }
    
    /**
     *
     * Sets a new date format.
     * 
     * @param format date format
     * @see java.text.SimpleDateFormat
     */
    public void setDateFormat(final String format) {
        sdf = new SimpleDateFormat(format);
    }
    
    /**
     *
     * Gets the currently configured log file.
     * 
     * @param log log file
     * @see java.io.File
     */
    public void setLogFile(final File log) {
        this.log = log;
    }
    
}
