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
		System.out.println("��ǥ���� " + baseDate);
		System.out.println("��ǥ�ð� " + baseTime);
		System.out.println("�������� " + fcstDate);
		System.out.println("�����ð� " + fcstTime);
		System.out.println("x��ǥ " + nx);
		System.out.println("y��ǥ " + ny);
	}
}
