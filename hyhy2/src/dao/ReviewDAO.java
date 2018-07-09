package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import model.Review;

public class ReviewDAO {
	private static ReviewDAO reviewdao = new ReviewDAO();

	public static ReviewDAO getInstance() {
		return reviewdao;
	}

	private ReviewDAO() {
	}

	public int insert(Connection conn, Review review) throws Exception {
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(
					"insert into review_test (userid,contentid,score,reviewdate,reviewcontent) values (?,?,?,NOW(),?)");
			pstmt.setString(1, review.getUserid());
			pstmt.setInt(2, review.getContentid());
			pstmt.setDouble(3, review.getScore());
			pstmt.setString(4, review.getReviewcontent());

			return pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		return 0;
	}

	public Review select(Connection conn, int reviewno) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select * from review_test where reviewid=(?)");
			pstmt.setInt(1, reviewno);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return makeReviewFromResultSet(rs);
			} else {
				return null;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		if (rs.next()) {
			return makeReviewFromResultSet(rs);
		} else {
			return null;
		}
	}

	private Review makeReviewFromResultSet(ResultSet rs) throws SQLException {
		Review review = new Review();

		review.setUserid(rs.getString("userid"));
		review.setContentid(rs.getInt("contentid"));
		review.setScore(rs.getDouble("score"));
		review.setReviewcontent(rs.getString("reviewcontent"));

		return review;
	}

	public int selectCount(Connection conn, int contentid) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement("select count(*) from review_test where contentid=(?)");
			pstmt.setInt(1, contentid);
			rs = pstmt.executeQuery();

			rs.next();
			return rs.getInt(1);
		} finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
	}

	public List<Review> selectList(Connection conn, int firstRow, int endRow, int contentid) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select * from review_test where contentid=(?) order by reviewid desc limit ?,?");
			pstmt.setInt(1, contentid);
			pstmt.setInt(2, firstRow - 1);
			pstmt.setInt(3, endRow - firstRow + 1);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				List<Review> reviewlist = new ArrayList<Review>();
				do {
					reviewlist.add(makeReviewFromResultSet(rs));
				} while (rs.next());
				return reviewlist;
			} else {
				return Collections.emptyList();
			}
		} finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}

	}

}
