package json;

import java.util.Calendar;

public class GetDate {
	Calendar cal = Calendar.getInstance();

	int year = cal.get(cal.YEAR);
	int month = cal.get(cal.MONTH) + 1;
	int date = cal.get(cal.DATE);
	String result = "";
	String smonth = "";
	String sdate = "";

	public String getresult() {
		if (month < 10)
			smonth = "0" + month;
		else
			smonth = "" + month;

		if (date < 10)
			sdate = "0" + date;
		else
			sdate = "" + date;

		result = "" + year + smonth + sdate;

		return result;
	}

}
