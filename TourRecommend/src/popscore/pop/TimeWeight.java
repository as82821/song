package popscore.pop;

/**
 * 시간가중치를 적용하는 클래스.
 *
 */
public class TimeWeight {
	/**
	 * 월 단위의 시간가중치. AgeS와 GenS 계산에 사용.
	 * 
	 * @param score
	 *            - 가중치를 적용할 점수
	 * @param k
	 *            - 현재와의 개월차
	 * @return - 시간가중치 T1이 적용된 결과값
	 */
	public static double t1(double score, int k) {
		if (k >= 24)
			return score * 0.4;
		else
			return score * (1 - 0.025 * k);
	}

	/**
	 * 년 단위의 시간가중치. MonS 계산에 사용.
	 * 
	 * @param score
	 *            - 가중치를 적용할 점수
	 * @param k
	 *            - 현재와의 년도차
	 * @return - 시간가중치 T2가 적용된 결과값
	 */
	public static double t2(double score, int k) {
		if (k >= 5)
			return score * 0.4;
		else
			return score * (1 - 0.15 * k);
	}
}
