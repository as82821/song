package jdbc;

public class Main {

	public static void main(String[] args) throws Exception {
		InsertNature nature=new InsertNature();
		InsertCulture_Art_History culture=new InsertCulture_Art_History();
		InsertHotel hotel=new InsertHotel();
		InsertReports reports=new InsertReports();
		InsertShopping shop=new InsertShopping();
		InsertFood food=new InsertFood();
		
		nature.insert();
		culture.insert();
		hotel.insert();
		reports.insert();
		shop.insert();
		food.insert();
	}	

}
