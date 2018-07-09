package popscore.ranking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * Ranking Ŭ����. TS, PS�� �޾Ƽ� RS�� ��� �� TreeMap�� �����Ѵ�.
 *
 */
public class Ranking {
	private TreeMap<Double, Integer> sortedRS = new TreeMap<>();
	
	/**
	 * Ranking Ŭ���� ������. RS��� �� ������������ ����.
	 * @param TS - HCF��⿡�� ���� HashMap. [contentid : TS]
	 * @param PS - PS��⿡�� ���� HashMap. [contentid : PS]
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
	 * @param k - top-k ����
	 * @return rankedList - ������������ ���� �� contentid ����Ʈ.
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
	 * top-k ������id, RS�� Ȯ���ϱ� ���� �޼ҵ�.
	 * @param k - top-k ����
	 */
	
	public void showTopK(int k) {
	int i=0;
	Iterator<Entry<Double, Integer>> iterator = sortedRS.descendingMap().entrySet().iterator();
	if(iterator.hasNext() == false) 
		System.out.println("�����...");
	while(iterator.hasNext()) {
		if(i>=k) 
			break;
		System.out.printf("%2d�� : %s\n",i+1,iterator.next());
		i++;
	}
}
	
}
