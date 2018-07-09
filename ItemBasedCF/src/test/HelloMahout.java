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
		// 데이터 연산을 위해 모든 선호,사용자,그리고 아이템 정보를 저장하거나 접근하도록 한다
		DataModel model = new FileDataModel(new File("data/convertedreview.csv"));
		// 하나 혹은 여러개의 가능한 측정 혹은 연산을 통해 얼마나 두 사용자가 유사한지 구한다
		//UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
		UserSimilarity similarity = new EuclideanDistanceSimilarity(model);
		// 주어진 사용자와 가장 유사한 사용자 그룹을 알려준다. 사용자 기반 추천 알고리즘을 위해 필요하다.
		// Computes a neighborhood consisting of the nearest n users to a given user.
		// "Nearest" is defined by the given UserSimilarity.
		UserNeighborhood neighborhood = new NearestNUserNeighborhood(3, similarity, model);
		// 위의 정보를 활용하여 사용자에게 아이템을 추천한다
		Recommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);
		// 사용자 1에게 책 한 권 추천
		List<RecommendedItem> recommendations = recommender.recommend(1, 4);

		for (RecommendedItem recommendation : recommendations) {
			System.out.println(recommendation);
		}

	}

}
