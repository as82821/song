package test2;

import java.io.File;
import java.io.IOException;

import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;

public class Please {

	public static void main(String[] args) throws Exception {
		DataModel dm = new FileDataModel(new File("data/pp.csv"));
		//ItemSimilarity sim = new PearsonCorrelationSimilarity(dm);
		ItemSimilarity sim = new EuclideanDistanceSimilarity(dm);
		double value = 0;

		int[] list = { 2373223, 1845253, 2520855, 1367251, 2520984, 1117141, 130307, 2521030, 130257, 1829586, 126513,
				130781, 1836287, 129730, 985264, 1603543, 2520693, 130366, 2520874, 1362516, 131307, 1918710, 2520986,
				482153, 131008, 2520884, 1021171, 2520745, 130211, 127215, 130764, 2520460, 126482, 268186, 2519639,
				132531, 130577, 2520924, 2499807, 231967, 127269, 1606096, 990140, 126500, 309340, 1139131, 130597,
				2521040, 761264, 1304880, 987592, 130721, 2520936, 129502, 264353, 2373204, 130630, 1964932, 2520982,
				130217, 1164746, 1179932, 479649, 129927, 984586, 132558, 866393, 1829573, 688998 };

		/* user1과 유사한 사용자들이 평점을 남긴 아이템들의 유사도 계산 */
		for (int i = 0; i < list.length; i++) {
			for (int j = 0; j < list.length; j++) {
				value = sim.itemSimilarity(list[i], list[j]);
				if (Double.isNaN(value))
					continue;
				System.out.println(list[i] + " 와 " + list[j] + "의 유사도 " + value);
			}
		}
	}

}
