package popscore;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import hybridscore.CalTS;
import popscore.pop.PopScore;
import popscore.ranking.Ranking;

import hybridscore.User;


public class Main implements User {

	public static void main(String[] args) throws Exception {
		CalTS ts=new CalTS();
		/*
		//PS ��꿡 �ʿ��� popscore ���̺� ����. [2015-01�� ~ 2018-03��]
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-01");
		String updateString = null;
		Calendar cal = Calendar.getInstance();
		try {
			Date updateDate = date.parse("2017-04-01"); //���� �������� �Է�(�����Ϸ��� �������� ������ �Է�)
			updateString = date.format(updateDate);
			while(!updateString.equals("2018-04-01")) { //���� �� �Է�(�����Ϸ��� ������ �������� ������ �Է�)
				PopScore.update(updateString); //input String format>"yyyy-MM-01", �Ѵ� �� �ڷ���� �����Ͽ� �������ش�.
				System.out.println(updateString);
				cal.setTime(updateDate);
				cal.add(Calendar.MONTH, 1);
				updateDate = cal.getTime();
				updateString = date.format(updateDate);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		//PS ���, ����� Ȯ��
		HashMap<Integer, Double> ps = PopScore.calcPS(queryuser, "2017-01");
		Iterator<Entry<Integer, Double>> hashPS = ps.entrySet().iterator();
		System.out.println("�޼ҵ� ȣ�� ����");
		//while(hashPS.hasNext()) {
			//System.out.println(hashPS.next());
		//}
		
		//RS ���, ���� ����� Ȯ��
		Ranking rank = new Ranking(ts.calTS(), ps);
		//Ranking rank = new Ranking(ts, ps);
		System.out.println("������ ����");
		rank.showTopK(30);
		//ArrayList<Integer> list = rank.getTopK(5);
		//for(int contentid : list) System.out.println(contentid);
	}
	
}
