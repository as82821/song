package json;

public class SKY {
	public static void printREH(long fcstValue) {
		if (fcstValue == 1)
			System.out.println("맑음");
		else if (fcstValue == 2)
			System.out.println("구름조금");
		else if (fcstValue == 3)
			System.out.println("구름많음");
		else if (fcstValue == 4)
			System.out.println("흐림");
	}
}
