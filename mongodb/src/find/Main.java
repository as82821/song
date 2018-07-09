package find;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import static com.mongodb.client.model.Filters.*;

public class Main {

	public static void main(String[] args) {
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		MongoDatabase database=mongoClient.getDatabase("songdb");
		MongoCollection<Document> collection=database.getCollection("home");
		
		/*MongoCursor<Document> cursor=collection.find().iterator();
		
		try {
			while(cursor.hasNext()) {
				System.out.println(cursor.next().toJson());
			}
		}finally {
			cursor.close();
		}
		
		for(Document cur:collection.find()) {
			System.out.println(cur.toJson());
		}*/
		
		Document mydoc=collection.find().first();
		System.out.println(mydoc.toJson());
		
		

	}

}
