package org.otfusion.java.conways.log;

/**
 * Created with IntelliJ IDEA.
 * User: jms
 * Date: 6/6/13
 * Time: 3:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class Log {


    private static void print(String msg) {
        System.out.println(msg);
    }

    private static String format(String tag, String message) {
        return String.format("[%s] - %s", tag, message);
    }

    public static void v(String tag, String message) {
        print(format(tag, message));
    }

    public static void e(String tag, String message) {
        print(String.format("[ERROR] - %s", format(tag, message)));
    }

    public static void i(String tag, String message) {
        print(String.format("[WARNING] - %s", format(tag, message)));
    }
}
