package test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

public class HelloMahout {

	public static void main(String[] args) throws Exception {
		// ������ ������ ���� ��� ��ȣ,�����,�׸��� ������ ������ �����ϰų� �����ϵ��� �Ѵ�
		DataModel model = new FileDataModel(new File("data/convertedreview.csv"));
		// �ϳ� Ȥ�� �������� ������ ���� Ȥ�� ������ ���� �󸶳� �� ����ڰ� �������� ���Ѵ�
		//UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
		UserSimilarity similarity = new EuclideanDistanceSimilarity(model);
		// �־��� ����ڿ� ���� ������ ����� �׷��� �˷��ش�. ����� ��� ��õ �˰����� ���� �ʿ��ϴ�.
		// Computes a neighborhood consisting of the nearest n users to a given user.
		// "Nearest" is defined by the given UserSimilarity.
		UserNeighborhood neighborhood = new NearestNUserNeighborhood(3, similarity, model);
		// ���� ������ Ȱ���Ͽ� ����ڿ��� �������� ��õ�Ѵ�
		Recommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);
		// ����� 1���� å �� �� ��õ
		List<RecommendedItem> recommendations = recommender.recommend(1, 4);

		for (RecommendedItem recommendation : recommendations) {
			System.out.println(recommendation);
		}

	}

}
