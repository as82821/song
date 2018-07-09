package jsoup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Main {

	public static void main(String[] args) throws IOException {
		String url = "https://www.zillow.com/homes/for_sale/Denver-CO/pmf,pf_pt/11093_rid/globalrelevanceex_sort/39.999479,-104.48719,39.530261,-105.225334_rect/10_zm/";
		Document dc = Jsoup.connect(url).get();
		Elements addr = dc.getElementsByClass("zsg-photo-card-address");
		Elements price = dc.getElementsByClass("zsg-photo-card-price");
		
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		MongoDatabase database=mongoClient.getDatabase("songdb");
		MongoCollection<org.bson.Document> collection=database.getCollection("home");
		List<org.bson.Document> doc=new ArrayList<>();

		for (int i = 0; i < addr.size(); i++) {
			System.out.println("주소 : " + addr.get(i).text() + " 가격 : " + price.get(i).text());
			doc.add(new org.bson.Document("price",price.get(i).text()).append("address",addr.get(i).text()));
		}
		
		collection.insertMany(doc);

	}

}
