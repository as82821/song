package tourAPI;

import tourAPI.parsing.Culture_Art_HistoryParsing;
import tourAPI.parsing.FoodParsing;
import tourAPI.parsing.HotelParsing;
import tourAPI.parsing.NatureParsing;
import tourAPI.parsing.ReportsParsing;
import tourAPI.parsing.ShoppingParsing;

public class Main {

	public static void main(String[] args) throws Exception {
		//NatureParsing nature=new NatureParsing();
		//Culture_Art_HistoryParsing cul_art_his=new Culture_Art_HistoryParsing();
		//ReportsParsing reports=new ReportsParsing();
		//ShoppingParsing shopping=new ShoppingParsing();
		FoodParsing food=new FoodParsing();
		//HotelParsing hotel=new HotelParsing();
		
		/*System.out.println("�ڿ�");
		//nature.parsing();
		System.out.println("�ι�");
		cul_art_his.parsing();
		System.out.println("������");
		reports.parsing();
		System.out.println("����");
		shopping.parsing();*/
		System.out.println("����");
		food.parsing();
		/*System.out.println("����"); 
		hotel.parsing();*/
	}

}
