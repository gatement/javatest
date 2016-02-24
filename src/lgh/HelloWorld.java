package lgh;

import java.lang.Thread;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.ThreadContext;
import lgh.UniqueThreadIdGenerator;

public class HelloWorld
{
    static String loggerName = null;
    private static final Logger logger = LogManager.getLogger(HelloWorld.class);
    private static String name = "Johnson";
    private static int age = 22;

    public static void main(String[] args) throws Exception
    {
        System.out.println("==start");
        logger.entry(name, age);

        ThreadContext.put("profiling.requestStart.millis", String.valueOf(System.currentTimeMillis()));

        logger.info("Hello, {} ({})!", name, age);
        logger.info("Hello, {}!", () -> getName());
        logger.printf(Level.ERROR, "a b %1$s %1$s %2$d, %3$,d", name, age, Integer.MAX_VALUE);


        MyLib.doIt(name, age);
        //while(true)
        //{
        //    logger.info("tick");
        //    Thread.sleep(1000);
        //}

        new ReceiveThread().start();
        new SendThread().start();

        Thread.sleep(1000);
        logger.exit(true);
        System.out.println("==end");
    }

    private static String getName()
    {
        return name;
    }

	static class ReceiveThread extends Thread
    {
		@Override
		public void run() 
        {
            logger.info("ReceiveThread id: {}!", () -> UniqueThreadIdGenerator.getCurrentThreadId());
		}
	}

	static class SendThread extends Thread
    {
		@Override
		public void run() 
        {
            logger.info("SendThread id: {}!", () -> UniqueThreadIdGenerator.getCurrentThreadId());
		}
	}
}
