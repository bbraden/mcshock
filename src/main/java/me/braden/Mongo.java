package me.braden;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;

public class Mongo {

    static ConnectionString connectionString = new ConnectionString("MONGODB URL");
    static MongoClientSettings settings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .build();
    static MongoClient mongoClient = MongoClients.create(settings);
    static MongoDatabase database = mongoClient.getDatabase("DATABASE NAME");
    static MongoCollection<Document> collection = database.getCollection("COLLECTION NAME");
    static ArrayList<Document> inserts = new ArrayList<>();
    static FindIterable<Document> iterDoc = collection.find();


    public static void send(String request) {
        collection.insertOne(new Document()
                .append("_id", new ObjectId())
                .append("message", request));
        System.out.println("Sent: " + request);
    }
}
