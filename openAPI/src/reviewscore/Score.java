package reviewscore;

import java.text.DecimalFormat;
import java.util.Random;

public class Score {
	public double getScore() {
		Random ran = new Random();
		DecimalFormat df = new DecimalFormat("#.#");
		int iscore;
		double dscore;
		double score;
		dscore = ran.nextDouble();
		iscore = ran.nextInt(5) + 1;
		String str;
		str = df.format(dscore);
		dscore = Double.parseDouble(str);

		if (dscore == 0.5 && iscore != 5)
			score = iscore + dscore;
		else
			score = iscore;

		return score;
	}

}
