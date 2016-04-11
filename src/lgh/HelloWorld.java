package lgh;

import java.util.List;
import java.util.Arrays;

public class HelloWorld {
    public static void main(String[] args) throws Exception
    {
        System.out.println("==start");
        
        new MyThread().start();

        String name = Test.getName2("main");
        System.out.println(String.valueOf(System.currentTimeMillis()) +" name(main): " + name);

        Thread.sleep(5100);

        System.out.println("==end");
    }

    public static class MyThread extends Thread
    {
        @Override
        public void run()
        {
            System.out.println("==thread start");
            try
            {
                String name = Test.getName2("thread");
                System.out.println(String.valueOf(System.currentTimeMillis()) +" name(thread): " + name);
            }
            catch (Exception e)
            {}

            System.out.println("==thread end");
        }
    }
}
