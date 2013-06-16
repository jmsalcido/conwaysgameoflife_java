package org.otfusion.java.conways.log;

import org.otfusion.java.conways.utils.Tools;

import java.io.*;
import java.util.Date;

/**
 * Created by:
 * User: jms
 * Date: 6/6/13
 * Time: 3:47 PM
 */
public class Log {

    /**
     * format: Format a the 2 entries.
     * @param tag Tag of our message.
     * @param message literally, our message.
     * @return
     */
    private static String format(String tag, String message) {
        return String.format("[%s] - %s", tag, message);
    }

    private static void log(String msg) {
        BufferedWriter bufferedWriter = null;

        // I should save all the log in a file?
        String appDir = System.getProperty("user.dir");
        String directoryName = appDir + File.separator + "log";
        String fileName = directoryName + File.separator + "log.txt";

        File directory = new File(directoryName);
        if(!directory.exists()) {
            directory.mkdir();
        } else { /* Directory exists. */}
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(fileName, true));
            bufferedWriter.append(msg + "\n");
            bufferedWriter.close();
        } catch(IOException fnfe) {
            Tools.print("Error de Logging: " + fnfe.getMessage());
        }
    }

    /**
     * v: Verbose output
     * @param tag
     * @param message
     */
    public static void v(String tag, String message) {
        String msg = new Date().toString() + " - ";
        msg += format(tag, message);
        Tools.print(msg);
        log(msg);
    }

    /**
     * e: Error output
     * @param tag
     * @param message
     */
    public static void e(String tag, String message) {
        String msg = String.format("%s - [ERROR] - %s", new Date().toString(), format(tag, message));
        Tools.print(msg);
        log(msg);
    }

    /**
     * w: Warning output
     * @param tag
     * @param message
     */
    public static void w(String tag, String message) {
        String msg = String.format("%s - [WARNING] - %s", new Date().toString(), format(tag, message));
        Tools.print(msg);
        log(msg);
    }

    /**
     * i: Info output
     * @param tag
     * @param message
     */
    public static void i(String tag, String message) {
        String msg = String.format("%s - [INFO] - %s", new Date().toString(), format(tag, message));
        Tools.print(msg);
        log(msg);
    }
}
