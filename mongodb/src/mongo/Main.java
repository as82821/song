package mongo;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bson.Document;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Main {

	public static void main(String[] args) {
		//IgnLog();
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		MongoDatabase database=mongoClient.getDatabase("songdb");
		MongoCollection<Document> collection=database.getCollection("crawling");
		Document doc=new Document();
		doc.append("title", "sampletitle").append("body", "samplebody").append("tag", Arrays.asList("java","mongodb"))
		.append("version", new Document("Java","1.8").append("MongoDriver", "3.7"));
		collection.insertOne(doc);
		System.out.println("추가끝");
		
		
	}

	// 경고가 심각 이상이면 경고를 출력하게 한다. 심각하지 않은 에러는 무시
	public static void IgnLog() {
		Logger mongoLogger = Logger.getLogger("com.mongodb");
		mongoLogger.setLevel(Level.SEVERE);
	}

}
