package reviewscore;

import java.util.Random;

/*10~20������ �������� ��ȯ*/
public class RandomTenToTwenty {
	int cnt;

	public int getcnt() {
		Random ran = new Random();
		cnt = ran.nextInt(5) + 1;
		return cnt;
	}
}
