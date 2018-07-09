package delete;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;

import static com.mongodb.client.model.Filters.*;

public class Main {

	public static void main(String[] args) {
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		MongoDatabase database = mongoClient.getDatabase("songdb");
		MongoCollection<Document> collection = database.getCollection("crawling");

		collection.deleteOne(eq("i", 5));
		
		DeleteResult deleteresult=collection.deleteMany(gte("i",4));
	}

}
