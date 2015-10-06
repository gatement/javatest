package lgh;

import java.util.List;
import java.util.Arrays;

public class HelloWorld {
    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
        System.out.println("==start");

        List<Integer> li = Arrays.asList(1, 2, 3);
        List<String>  ls = Arrays.asList("one", "two", "three");
        printList(li);
        printList(ls);

        System.out.println("==end");
    }

    public static void printList(List<?> list) {
        for (Object elem: list)
            System.out.print(elem + " ");
        System.out.println();
    }

    void foo(List<?> i) {
        i.set(0, i.get(0));
    }
    
}
