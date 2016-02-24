package lgh;

import java.util.concurrent.atomic.AtomicInteger;

public class UniqueThreadIdGenerator
{
     private static final AtomicInteger uniqueId = new AtomicInteger(0);

     private static final InheritableThreadLocal < Integer > uniqueNum = 
         new InheritableThreadLocal<Integer> () 
         {
             @Override protected Integer initialValue() 
             {
                 return uniqueId.getAndIncrement();
             }
         };
 
     public static int getCurrentThreadId() 
     {
         return uniqueNum.get();
     }
     public static void setCurrentThreadId(int id) 
     {
         uniqueNum.set(id);
     }
 } // UniqueThreadIdGenerator
