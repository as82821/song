package Item;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.LogLikelihoodSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.TanimotoCoefficientSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;

public class CalItemSim {

	public static void main(String[] args) throws Exception {
	/*	DataModel dm = new FileDataModel(new File("data/2500user.csv"));
		//ItemSimilarity sim = new PearsonCorrelationSimilarity(dm);
		ItemSimilarity sim = new EuclideanDistanceSimilarity(dm);

		double value = sim.itemSimilarity(131122, 129922);
		
		 * if (Double.isNaN(value)) { System.out.println("nan"); }
		 
		//System.out.println("������ " + i + " �� " + j + "������ ���絵 " + value);
		System.out.println("���絵 "+value);

		System.out.println();*/
		
		
		String name = "data/usersigs.csv";
		try {
			// ������ �� ����
			DataModel dm = new FileDataModel(new File(name));
			// ���絵 �� ����
			//ItemSimilarity sim = new LogLikelihoodSimilarity(dm);
			//ItemSimilarity sim=new PearsonCorrelationSimilarity(dm);
			ItemSimilarity sim=new EuclideanDistanceSimilarity(dm);
			//TanimotoCoefficientSimilarity sim = new TanimotoCoefficientSimilarity(dm);
			GenericItemBasedRecommender recommender = new GenericItemBasedRecommender(dm, sim);
			int i = 1;
			for (LongPrimitiveIterator items = dm.getItemIDs(); items.hasNext();) {
				long itemId = items.nextLong();
				// ���� ���� id�� �´� 2���� ������ ��õ.
				// ��õ ������ ������ mostSimilarItems(itemId, 2) ���� ���ڸ� �ٲٸ� �ȴ�.
				List<RecommendedItem> recommendations = recommender.mostSimilarItems(itemId, 3);

				for (RecommendedItem recommendation : recommendations) {
					//System.out.println(recommendation);
					//if(recommendation.getValue()!=1.0&&recommendation.getValue()!=0.5&&recommendation.getValue()<0.6)
						System.out.println(itemId + "," + recommendation.getItemID() + "," + recommendation.getValue());
				}
				i++;
			/*	if (i > 10)
					System.exit(i);*/
			}
		} catch (IOException e) {
			System.out.println("error");
			e.printStackTrace();
		} catch (TasteException e) {
			System.out.println("taste exception");
			e.printStackTrace();
		}

	}

}
