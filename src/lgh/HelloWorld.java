package lgh;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Locale;
import static java.util.Arrays.asList;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.AggregateIterable;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Sorts.*;


public class HelloWorld {
    public static void main(String[] args){
        System.out.println("hello world");

        MongoClient mongoClient = new MongoClient();
        MongoDatabase db = mongoClient.getDatabase("test");

        // INSERT
        //try {
        //   insertOne(db); 
        //}
        //catch (ParseException e)
        //{
        //    System.out.println("parse exception");
        //}

        // FIND
        //FindIterable<Document> iterable = db.getCollection("restaurants").find(or(eq("cuisine", "Italian"), eq("address.zipcode", "10075"))).sort(orderBy(ascending("borough", "address.zipcode"), descending("restaurant_id")));
        //iterable.forEach(new Block<Document>() {
        //   @Override
        //   public void apply(final Document document) {
        //       System.out.println(document);
        //   }
        //});
        

        // UPDATE
        //UpdateResult updateResult = db.getCollection("restaurants").replaceOne(new Document("name", "Juni2"), new Document("name2", "Johnson"), new UpdateOptions().upsert(true));
        //System.out.print("idModifiedCountAvailable: ");
        //System.out.print(updateResult.isModifiedCountAvailable());
        //System.out.println();
        //System.out.print("ModifiedCount: ");
        //System.out.print(updateResult.getModifiedCount());
        //System.out.println();
        //System.out.print("MatchedCount: ");
        //System.out.print(updateResult.getMatchedCount());
        //System.out.println();
        
        //DELETE
        //DeleteResult deleteResult = db.getCollection("restaurants").deleteOne(new Document("name2", "Johnson"));
        //System.out.print("DeletedCount: ");
        //System.out.print(deleteResult.getDeletedCount());
        //System.out.println();

        // AGGREGATE
        //AggregateIterable<Document> iterable = db.getCollection("restaurants").aggregate(asList(new Document("$match", new Document("borough", "Queens").append("cuisine", "Brazilian")), new Document("$group", new Document("_id", "$address.zipcode").append("count", new Document("$sum", 1)))));

        //iterable.forEach(new Block<Document>() {
        //    @Override
        //    public void apply(final Document doc) {
        //        System.out.println(doc);
        //    }
        //});

        // INDEX
        String indexResult = db.getCollection("restaurants").createIndex(new Document("cuisine", 1).append("address.zipcode", -1));
        System.out.print("Index name: ");
        System.out.println(indexResult);

        System.out.println("donex");
    }

    private static void insertOne(MongoDatabase db) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);
        db.getCollection("restaurants").insertOne(
                new Document()
                    .append("street", "Johnson")
                    .append("zipcode", "10075")
                    .append("building", "1480")
                    .append("coord", asList(-73.9557413, 40.7720266))
                    .append("borough", "Manhattan")
                    .append("cuisine", "Italian")
                    .append("grades", asList(
                            new Document()
                                .append("date", format.parse("2014-10-01T00:00:00Z"))
                                .append("grade", "A")
                                .append("score", 11),
                            new Document()
                                .append("date", format.parse("2014-01-16T00:00:00Z"))
                                .append("grade", "B")
                                .append("score", 17)
                            ))
                    .append("name", "Vella")
                    .append("restaurant_id", "41704621")
                );
    }
}
