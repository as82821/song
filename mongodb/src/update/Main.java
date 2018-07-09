package update;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;

public class Main {

	public static void main(String[] args) {
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		MongoDatabase database = mongoClient.getDatabase("songdb");
		MongoCollection<Document> collection = database.getCollection("crawling");

		// collection.updateOne(eq("i",2),new Document("$set",new Document("i",6)));

		UpdateResult update = collection.updateMany(eq("i", 1), inc("i", 4));

	}

}
