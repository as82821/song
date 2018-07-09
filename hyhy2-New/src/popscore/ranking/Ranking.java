package popscore.ranking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * Ranking 클래스. TS, PS를 받아서 RS를 계산 후 TreeMap을 갱신한다.
 *
 */
public class Ranking {
	private TreeMap<Double, Integer> sortedRS = new TreeMap<>();
	
	/**
	 * Ranking 클래스 생성자. RS계산 후 내림차순으로 정렬.
	 * @param TS - HCF모듈에서 계산된 HashMap. [contentid : TS]
	 * @param PS - PS모듈에서 계산된 HashMap. [contentid : PS]
	 */
	public Ranking(HashMap<Integer, Double> TS, HashMap<Integer, Double> PS) {
		Iterator<Integer> keyIterator = TS.keySet().iterator();
		while(keyIterator.hasNext()) {
			int contentid = keyIterator.next();
			if(!(TS.get(contentid).isNaN()))
				this.sortedRS.put((TS.get(contentid) + PS.get(contentid)), contentid);
			
		}
		System.out.println("calculating RS complete!");
	}
	/**
	 * 
	 * @param k - top-k 설정
	 * @return rankedList - 내림차순으로 정렬 된 contentid 리스트.
	 */
	public ArrayList<Integer> getTopK(int k) {
		int i=0;
		ArrayList<Integer> rankedList = new ArrayList<Integer>();
		Iterator<Integer> iterator = sortedRS.descendingMap().values().iterator();
		while(iterator.hasNext()) {
			if(i>=k) break;
			rankedList.add(iterator.next());
			i++;
		}
		return rankedList;
	}
	
	/**
	 * top-k 관광지id, RS를 확인하기 위한 메소드.
	 * @param k - top-k 설정
	 */
	
	public void showTopK(int k) {
	int i=0;
	Iterator<Entry<Double, Integer>> iterator = sortedRS.descendingMap().entrySet().iterator();
	if(iterator.hasNext() == false) 
		System.out.println("비었다...");
	while(iterator.hasNext()) {
		if(i>=k) 
			break;
		System.out.printf("%2d위 : %s\n",i+1,iterator.next());
		i++;
	}
}
	
}
