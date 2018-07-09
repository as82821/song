package trash;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

public class Main {

	public static void main(String[] args) throws Exception {
		DataModel dm = new FileDataModel(new File("data/usersigs.csv"));
		UserSimilarity sim = new EuclideanDistanceSimilarity(dm);
		// UserSimilarity sim = new PearsonCorrelationSimilarity(dm);
		BufferedReader br = new BufferedReader(new FileReader("data\\usersigs.csv"));
		List<Integer> list1 = new ArrayList<Integer>();
		List<Integer> list2 = new ArrayList<Integer>();
		String line = "";
		int cnt = 0;

		for (int i = 1; i <= 1; i++) {

			while ((line = br.readLine()) != null) {
				String value = line;
				String user = value.substring(0, value.indexOf(","));

				if (Integer.parseInt(user) != i)
					break;

				// System.out.println(value);
				String contentid = line.substring(value.indexOf(",") + 1);
				// System.out.println(contentid);
				contentid = contentid.substring(0, contentid.indexOf(","));
				// System.out.println(contentid);
				// System.out.println();
				list1.add(Integer.parseInt(contentid));
			}
			for (int j = 1; j <= 5; j++) {
				System.out.println("j"+j);
				cnt=0;
				// 자기 자신과는 비교할 필요 없음
				if (i == j)
					continue;
				
				while ((line = br.readLine()) != null) {
				while ((line = br.readLine()) != null) {
					String value = line;
					String user = value.substring(0, value.indexOf(","));
					if (Integer.parseInt(user) != j)
						continue;
					System.out.println("3일때?");
					System.out.println(user);
					if (Integer.parseInt(user) == j + 1)
						break;
					//System.out.println(value);

					String contentid = line.substring(value.indexOf(",") + 1);
					contentid = contentid.substring(0, contentid.indexOf(","));
					//System.out.println(contentid);
					//System.out.println();
					list2.add(Integer.parseInt(contentid));
				}
				}
				
			/*	for (int a = 0; a < list1.size(); a++) {
					System.out.println(list1.get(a));
				}
				System.out.println();
				for (int b = 0; b < list2.size(); b++) {
					System.out.println(list2.get(b));
				}*/
				for (int k = 0; k < list1.size(); k++) {
					if (list2.contains(list1.get(k))) {
						cnt++;
					}
				}
				
				//System.out.println(cnt);

				if (cnt > 2) {
					double value = sim.userSimilarity(i, j);
				/*	if (Double.isNaN(value))
						continue;*/
					System.out.println("유저 1과 " + i + "사이의 유사도 " + value);
				}
				list2.clear();
			}
			list1.clear();
		}
	}

}
