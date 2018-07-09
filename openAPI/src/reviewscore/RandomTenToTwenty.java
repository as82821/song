package reviewscore;

import java.util.Random;

/*10~20사이의 랜덤수를 반환*/
public class RandomTenToTwenty {
	int cnt;

	public int getcnt() {
		Random ran = new Random();
		cnt = ran.nextInt(5) + 1;
		return cnt;
	}
}
