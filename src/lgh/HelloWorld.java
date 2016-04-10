package lgh;

import java.util.List;
import java.util.Arrays;

public class HelloWorld {
    public static void main(String[] args) 
    {
        System.out.println("==start");

        Singleton singleton1 = Singleton.getSingleton();
        singleton1.setName("Johnson");
        System.out.println("name: " + singleton1.getName());

        Singleton singleton2 = Singleton.getSingleton();
        singleton2.setName("lgh");
        System.out.println("name: " + singleton1.getName());


        System.out.println("==end");
    }
}
