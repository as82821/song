package jdbc;

import java.sql.DriverManager;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDriver;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

public class DBCPInit extends HttpServlet {

	@Override
	public void init() throws ServletException {
		loadJDBCDriver();
		initConnectionPool();
	}

	private void loadJDBCDriver() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
			throw new RuntimeException("fail to load JDBC Driver", ex);
		}
	}
	
	private void initConnectionPool() {
		try {
			//AWS 서버 접속 url
			/*String jdbcUrl = "jdbc:mysql://aws-song.cdzl90330te3.us-east-2.rds.amazonaws.com/TourAPI?"
					+ "useUnicode=true&characterEncoding=utf8&autoReconnect=true&useSSL=false";
			String username = "HYsong";
			String pw = "1q2w3e4r";*/
			
			//로컬 서버 접속 url
			String jdbcUrl =  "jdbc:mysql://localhost:3306/tourdb?"
					+ "useUnicode=true&characterEncoding=utf8&autoReconnect=true&useSSL=false";
			String username = "root";
			String pw = "wlgus132!!";

			ConnectionFactory connFactory = new DriverManagerConnectionFactory(jdbcUrl, username, pw);

			PoolableConnectionFactory poolableConnFactory = new PoolableConnectionFactory(connFactory, null);
			poolableConnFactory.setValidationQuery("select 1");

			GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
			poolConfig.setTimeBetweenEvictionRunsMillis(1000L * 60L * 5L);
			poolConfig.setTestWhileIdle(true);
			/*커넥션 풀이 저장할 수 있는 최대 유휴(사용되지 않는 커넥션) 개수 저장*/
			poolConfig.setMinIdle(8);
			/*커넥션 풀이 보관할 수 있는 최대 유휴 개수. 음수면 제한 없음*/
			poolConfig.setMaxTotal(50);

			GenericObjectPool<PoolableConnection> connectionPool = new GenericObjectPool<>(poolableConnFactory,
					poolConfig);
			poolableConnFactory.setPool(connectionPool);

			Class.forName("org.apache.commons.dbcp2.PoolingDriver");
			PoolingDriver driver = (PoolingDriver) DriverManager.getDriver("jdbc:apache:commons:dbcp:");
			//dbcp 이름 설정
			driver.registerPool("hyhy", connectionPool);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
