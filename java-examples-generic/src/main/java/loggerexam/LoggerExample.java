package loggerexam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerExample {

    private static final Logger logger = LoggerFactory.getLogger(LoggerExample.class);
    public static void main(String[] args) {
        logger.info("bla");



    }
}
