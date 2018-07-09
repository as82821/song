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
		//PS 계산에 필요한 popscore 테이블 갱신. [2015-01월 ~ 2018-03월]
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-01");
		String updateString = null;
		Calendar cal = Calendar.getInstance();
		try {
			Date updateDate = date.parse("2017-04-01"); //갱신 시작일자 입력(갱신하려는 데이터의 다음달 입력)
			updateString = date.format(updateDate);
			while(!updateString.equals("2018-04-01")) { //갱신 끝 입력(갱신하려는 마지막 데이터의 다음달 입력)
				PopScore.update(updateString); //input String format>"yyyy-MM-01", 한달 전 자료들을 종합하여 갱신해준다.
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
		
		//PS 계산, 결과셋 확인
		HashMap<Integer, Double> ps = PopScore.calcPS(queryuser, "2017-01");
		Iterator<Entry<Integer, Double>> hashPS = ps.entrySet().iterator();
		System.out.println("메소드 호출 종료");
		//while(hashPS.hasNext()) {
			//System.out.println(hashPS.next());
		//}
		
		//RS 계산, 최종 결과셋 확인
		Ranking rank = new Ranking(ts.calTS(), ps);
		//Ranking rank = new Ranking(ts, ps);
		System.out.println("생성자 종료");
		rank.showTopK(30);
		//ArrayList<Integer> list = rank.getTopK(5);
		//for(int contentid : list) System.out.println(contentid);
	}
	
}
