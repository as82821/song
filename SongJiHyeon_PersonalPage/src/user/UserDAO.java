package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jdbc.connection.ConnectionProvider;

public class UserDAO {
	public UserDAO() {
	}
	
	/**
	 * 로그인 여부를 체크하는 함수.
	 * @param in_Username
	 * @param in_Password
	 * @return 1 - 로그인 성공, 0 - pw 다름, -1 - 존재하지 않는 id, -2 - 데이터베이스 오류
	 */
	public static int signin(String in_Username, String in_Password) {
		Connection conn;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = "SELECT pw from user where userid = ?";
		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(SQL); 
			pstmt.setString(1,  in_Username);
			rs = pstmt.executeQuery(); 
			if (rs.next()) {
				if(rs.getString("pw").equals(in_Password)) {
					return 1;	//로그인 성공
				}
				else
					return 0;	//pw 다름
			}
			return -1;	//존재하지 않는 id
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -2; 	//데이터베이스 오류
	}
	
	/**
	 * user테이블의 정보럴 받아와서 User 객체를 만들어 반환하는 함수.
	 * @param userid
	 * @return User user
	 */
	public static User getUserinfo(String userid) {
		Connection conn;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = "SELECT * from user where userid = ?";
		User user = new User();
		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(SQL); 
			pstmt.setString(1,  userid);
			rs = pstmt.executeQuery(); 
			if (rs.next()) {
				user.setUserid(rs.getString("userid"));
				user.setGender(rs.getString("gender"));
				user.setAge(rs.getInt("age"));
				user.setName(rs.getString("name"));
				user.setPw(rs.getString("pw"));
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 회원가입 메소드. User를 입력받아서 데이터베이스에 저장.
	 * @param User user - 회원가입 정보
	 * @return -1 - 데이터베이스 오류
	 */
	public static int signup(User user) {
		Connection conn;
		PreparedStatement pstmt = null;
		String SQL = "insert into user values ( ?, ?, ?, ?, ? )";
		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, user.getUserid());
			pstmt.setString(2, user.getGender());
			pstmt.setInt(3, user.getAge());
			pstmt.setString(4, user.getName());
			pstmt.setString(5, user.getPw());
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;	//데이터베이스 오류
	}
	
	/**
	 * userid 중복 검사 메소드.
	 * @param userid - 확인하려는 아이디
	 * @return 데이터베이스에 해당 아이디가 존재하는 갯수.
	 */
	public static int useridCheck(String userid) {
		Connection conn;
		PreparedStatement pstmt = null;
		ResultSet rs;
		String SQL = "select count(*) from user where userid = ?";
		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			if(rs.next())
				return rs.getInt(1);	//존재하는 id 갯수 반환.
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;	//데이터베이스 오류
	}
	
	/**
	 * 사용자 프로필 업데이트 메소드.
	 * @param User user - 변경된 정보들
	 * @return -1 - 데이터베이스 오류
	 */
	public static int updateUser(User user) {
		Connection conn;
		PreparedStatement pstmt = null;
		String SQL = "UPDATE user SET pw = ?, age = ?, gender = ?, name = ? where userid = ?";
		try {
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, user.getPw());
			pstmt.setInt(2, user.getAge());
			pstmt.setString(3, user.getGender());
			pstmt.setString(4, user.getName());
			pstmt.setString(5, user.getUserid());
			int result = pstmt.executeUpdate();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;	//데이터베이스 오류
	}
}
