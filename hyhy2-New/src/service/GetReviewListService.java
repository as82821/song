package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import dao.ReviewDAO;
import jdbc.connection.ConnectionProvider;
import model.Review;

public class GetReviewListService {
	private static GetReviewListService instance = new GetReviewListService();

	public static GetReviewListService getInstance() {
		return instance;
	}

	private GetReviewListService() {
	}

	private static final int REVIEW_COUNT_PER_PAGE = 3;

	public ReviewListView getReviewList(int pageNumber,int contentid) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int currentPageNumber = pageNumber;
		try {
			conn = ConnectionProvider.getConnection();
			ReviewDAO reviewdao = ReviewDAO.getInstance();
			int reviewTotalCount = reviewdao.selectCount(conn, contentid);

			List<Review> reviewlist = null;
			int firstRow = 0;
			int endRow = 0;
			if (reviewTotalCount > 0) {
				firstRow = (pageNumber - 1) * REVIEW_COUNT_PER_PAGE + 1;
				endRow = firstRow + REVIEW_COUNT_PER_PAGE - 1;
				reviewlist = reviewdao.selectList(conn, firstRow, endRow, contentid);
			} else {
				currentPageNumber = 0;
				reviewlist = Collections.emptyList();
			}
			return new ReviewListView(reviewTotalCount, currentPageNumber, reviewlist, REVIEW_COUNT_PER_PAGE, firstRow,
					endRow, contentid);

		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
	}

}
