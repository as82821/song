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
		
		/*System.out.println("ÀÚ¿¬");
		//nature.parsing();
		System.out.println("ÀÎ¹®");
		cul_art_his.parsing();
		System.out.println("·¹Æ÷Ã÷");
		reports.parsing();
		System.out.println("¼îÇÎ");
		shopping.parsing();*/
		System.out.println("À½½Ä");
		food.parsing();
		/*System.out.println("¼÷¹Ú"); 
		hotel.parsing();*/
	}

}
