package json;

public class PrintDateTime {
	long baseDate;
	String baseTime;
	long fcstDate;
	String fcstTime;
	long nx;
	long ny;

	public PrintDateTime(long baseDate, String baseTime, long fcstDate, String fcstTime, long nx, long ny) {
		this.baseDate = baseDate;
		this.baseTime = baseTime;
		this.fcstDate = fcstDate;
		this.fcstTime = fcstTime;
		this.nx = nx;
		this.ny = ny;
	}

	public void printDateTime() {
		System.out.println("발표일자 " + baseDate);
		System.out.println("발표시각 " + baseTime);
		System.out.println("예보일자 " + fcstDate);
		System.out.println("예보시각 " + fcstTime);
		System.out.println("x좌표 " + nx);
		System.out.println("y좌표 " + ny);
	}
}
