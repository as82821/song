package member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class MemberDAO {
	private static MemberDAO instance;

	// 싱글톤 패턴
	private MemberDAO() {
	}

	public static MemberDAO getInstance() {
		if (instance == null)
			instance = new MemberDAO();
		return instance;
	}

	// 정상이면 1, 아이디 중복이면 -1
	public int insertMember(Member mem) throws SQLException {
		System.out.println("씹라");
		Connection conn = null;
		PreparedStatement pstmt = null;
		int value = 10;
		String jdbcDriver = "jdbc:mysql://localhost:3306/jspprac?"
				+ "useUnicode=true&characterEncoding=utf8&autoReconnect=true&useSSL=false";
		String user = "root";
		String pass = "wlgus132!!";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(jdbcDriver, user, pass);
			
			//System.out.println("아ㅇ;ㄷ;"+mem.getId());
			
			pstmt = conn.prepareStatement("INSERT INTO signup values (?, ?, ?, ?, ?, ?, ?, ?)");
			//System.out.println(mem.getId());
			pstmt.setString(1, mem.getId());
			pstmt.setString(2, mem.getPw());
			pstmt.setString(3, mem.getSex());
			pstmt.setString(4, mem.getYears());
			pstmt.setString(5, mem.getBody());
			pstmt.setString(6, mem.getAddr());
			pstmt.setString(7, mem.getPhone());
			pstmt.setString(8, mem.getJob());
			System.out.println("여기냐??");
			pstmt.executeUpdate();
			System.out.println("insert success");
			return value = 1;
			
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
			System.out.println(ce.getMessage());
		} catch (SQLException e) {
			if (e.getErrorCode() == 1062) {
				System.out.println("중복");
				return value = -1;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return value;
	}

}
