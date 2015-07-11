package lgh;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("hello world");
        MongoClient mongoClient = new MongoClient();
        System.out.println("done2");
    }
}
