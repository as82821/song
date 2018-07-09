package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jdbc.connection.ConnectionProvider;

public class UserDAO {
	public UserDAO() {
	}
	
	/**
	 * �α��� ���θ� üũ�ϴ� �Լ�.
	 * @param in_Username
	 * @param in_Password
	 * @return 1 - �α��� ����, 0 - pw �ٸ�, -1 - �������� �ʴ� id, -2 - �����ͺ��̽� ����
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
					return 1;	//�α��� ����
				}
				else
					return 0;	//pw �ٸ�
			}
			return -1;	//�������� �ʴ� id
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -2; 	//�����ͺ��̽� ����
	}
	
	/**
	 * user���̺��� ������ �޾ƿͼ� User ��ü�� ����� ��ȯ�ϴ� �Լ�.
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
	 * ȸ������ �޼ҵ�. User�� �Է¹޾Ƽ� �����ͺ��̽��� ����.
	 * @param User user - ȸ������ ����
	 * @return -1 - �����ͺ��̽� ����
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
		return -1;	//�����ͺ��̽� ����
	}
	
	/**
	 * userid �ߺ� �˻� �޼ҵ�.
	 * @param userid - Ȯ���Ϸ��� ���̵�
	 * @return �����ͺ��̽��� �ش� ���̵� �����ϴ� ����.
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
				return rs.getInt(1);	//�����ϴ� id ���� ��ȯ.
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;	//�����ͺ��̽� ����
	}
	
	/**
	 * ����� ������ ������Ʈ �޼ҵ�.
	 * @param User user - ����� ������
	 * @return -1 - �����ͺ��̽� ����
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
		return -1;	//�����ͺ��̽� ����
	}
}
