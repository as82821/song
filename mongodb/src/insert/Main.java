package insert;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Main {

	public static void main(String[] args) {
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		MongoDatabase database=mongoClient.getDatabase("songdb");
		MongoCollection<Document> collection=database.getCollection("crawling");
		List<Document> doc=new ArrayList<>();
		for(int i=0;i<5;i++) {
			doc.add(new Document("i",i).append("value", 1000*i));
		}
		collection.insertMany(doc);
		

	}

}
