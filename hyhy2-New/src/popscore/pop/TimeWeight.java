package popscore.pop;

/**
 * �ð�����ġ�� �����ϴ� Ŭ����.
 *
 */
public class TimeWeight {
	/**
	 * �� ������ �ð�����ġ. AgeS�� GenS ��꿡 ���.
	 * 
	 * @param score
	 *            - ����ġ�� ������ ����
	 * @param k
	 *            - ������� ������
	 * @return - �ð�����ġ T1�� ����� �����
	 */
	public static double t1(double score, int k) {
		if (k >= 24)
			return score * 0.4;
		else
			return score * (1 - 0.025 * k);
	}

	/**
	 * �� ������ �ð�����ġ. MonS ��꿡 ���.
	 * 
	 * @param score
	 *            - ����ġ�� ������ ����
	 * @param k
	 *            - ������� �⵵��
	 * @return - �ð�����ġ T2�� ����� �����
	 */
	public static double t2(double score, int k) {
		if (k >= 5)
			return score * 0.4;
		else
			return score * (1 - 0.15 * k);
	}
}
