package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import dao.ReviewDAO;
import jdbc.connection.ConnectionProvider;
import model.Review;

public class WriteReviewService {
	private static WriteReviewService instance = new WriteReviewService();

	public static WriteReviewService getInstance() {
		return instance;
	}

	private WriteReviewService() {

	}

	public void write(Review review) throws Exception {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			ReviewDAO reviewdao = ReviewDAO.getInstance();
			reviewdao.insert(conn, review);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
	}

}
