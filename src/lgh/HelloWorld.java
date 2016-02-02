package lgh;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Level;

public class HelloWorld
{
    static String loggerName = null;
    private static final Logger logger = LogManager.getLogger();
    private static String name = "Johnson";
    private static int age = 22;

    public static void main(String[] args) throws Exception
    {
        System.out.println("==start");

        logger.error("Hello, {} ({})!", name, age);
        logger.error("Hello, {}!", () -> getName());
        logger.printf(Level.ERROR, "a b %1$s %1$s %2$d, %3$,d", name, age, Integer.MAX_VALUE);

        System.out.println("==end");
    }

    private static String getName()
    {
        return name;
    }
}
