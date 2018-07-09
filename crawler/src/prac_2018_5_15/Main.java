package prac_2018_5_15;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Main {

	public static void main(String[] args) throws IOException {
		String url = "https://www.zillow.com/homes/for_sale/Denver-CO/pmf,pf_pt/11093_rid/globalrelevanceex_sort/39.999479,-104.48719,39.530261,-105.225334_rect/10_zm/";
		Document dc = Jsoup.connect(url).get();
		Elements addr = dc.getElementsByClass("zsg-photo-card-address");
		Elements price = dc.getElementsByClass("zsg-photo-card-price");

	/*	ArrayList<String> prlist = new ArrayList<>();
		ArrayList<String> addrlist = new ArrayList<>();

		for (Element pr : price) {
			// System.out.println(pr.text());
			prlist.add(pr.text());

		}

		for (Element add : addr) {
			// System.out.println(add.text());
			addrlist.add(add.text());

		}*/

		for (int i = 0; i < addr.size(); i++) {
			System.out.println("가격 : " + price.get(i).text() + "    주소 : " + addr.get(i).text());
		}

	}

}
