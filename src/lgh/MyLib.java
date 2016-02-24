package lgh;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

public class MyLib 
{
   private static final Logger logger = LogManager.getLogger();
   public static boolean doIt(String name, int age)
   {
       logger.entry(name, age);
       logger.info("start doing it!");

       ThreadContext.put("myname", "Johnson");
       ThreadContext.put("myage", "22");
       ThreadContext.put("mdc.fa", "fav");
       ThreadContext.put("mdc.fb", "fbv");
       ThreadContext.put("other.fc", "otherv");
       ThreadContext.push("stackv");
       ThreadContext.push("stackv2");
       try
       {
           throw new Exception("lgh");
       }
       catch(Exception e)
       {
           logger.error(e.toString());
       }
       logger.error("done it!");
       return logger.exit(false);
   } 
}

