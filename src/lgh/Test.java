package lgh;

public class Test {
    public static synchronized String getName(String param) throws Exception
    {
        System.out.println(String.valueOf(System.currentTimeMillis()) +" getting name: " + param);
        Thread.sleep(5000);
        return "Johnson";
    }

    public static String getName2(String param) throws Exception
    {
        synchronized (Test.class)
        {
            System.out.println(String.valueOf(System.currentTimeMillis()) +" getting name2: " + param);
            Thread.sleep(5000);
            return "Johnson";
        }
    }
}
