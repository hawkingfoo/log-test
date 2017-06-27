package main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

/**
 * Created by hawkingfoo on 2017/6/27 0027.
 */
public class LogTest {
    private static final Logger logger = LogManager.getLogger(LogTest.class);

    static class test extends Thread {
        @Override
        public void run() {
            long time = System.currentTimeMillis();
            ThreadContext.put("logid", String.valueOf( time));
            logger.info("test thread.");
            ThreadContext.clearAll();
        }
    }

    public static void main(String[] args) {
        long time = System.currentTimeMillis() ;
        ThreadContext.put("logid", String.valueOf(time));
        try {
            logger.trace("trace...");
            logger.debug("debug...");
            logger.info("info...");
            logger.warn("warn...");
            logger.error("error...");
            logger.fatal("fatal...");

            test test1 = new test();
            test test2 = new test();
            test1.start();
            test2.start();

        } catch (Exception e) {
            e.printStackTrace();
        }

        ThreadContext.clearAll();
    }
}