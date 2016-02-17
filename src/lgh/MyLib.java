package lgh;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MyLib 
{
   private static final Logger logger = LogManager.getLogger();
   public static boolean doIt(String name, int age)
   {
       logger.entry(name, age);
       logger.info("start doing it!");
       logger.error("done it!");
       return logger.exit(false);
   } 
}

