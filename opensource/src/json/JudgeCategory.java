package json;

public class JudgeCategory {
	String category;
	long fcstValue;
	long temp;

	public JudgeCategory(String category, long fcstValue) {
		this.category = category;
		this.fcstValue = fcstValue;
	}

	public void printfInfo(String category, long fcstValue) {
		switch (category) {
		case "POP":
			POP.printPOP(fcstValue);
			break;
		case "REH":
			REH.printREH(fcstValue);
			break;
		case "SKY":
			SKY.printREH(fcstValue);
			break;
		case "T3H":
			T3H.printREH(fcstValue);
			temp=fcstValue;
			break;
		}
	}

}
