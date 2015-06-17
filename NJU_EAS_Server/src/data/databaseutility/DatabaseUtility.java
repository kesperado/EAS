package data.databaseutility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * @author 作者 :knox
 * 类说明：
 * 这个是数据库操作类，包含了对数据库的初始化，连接数据库，释放等操作
 */
public class DatabaseUtility {
	static String drivername = "com.mysql.jdbc.Driver";
	static String url = "jdbc:mysql://localhost:3306/easdatabase2";
	static String user = "root";
	static String password = "215898";
	static {
		try {
			Class.forName(drivername);
			System.out.println("服務器端：數據庫驅動創建成功");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static Connection getConnection() {
		Connection connection = null;
		try {
			connection = (Connection) DriverManager.getConnection(url, user,
					password);
			System.out.println("服務器端：數據庫連接成功");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	public static void free(ResultSet resultSet, Connection connection,
			Statement statement) {

		try {
			if (resultSet != null)
				resultSet.close();
		} catch (SQLException e) {
			System.out.println("服務器端：數據庫关闭ResultSet失败");
			e.printStackTrace();
		} finally {

			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				System.out.println("服務器端：數據庫关闭Connection失败");
				e.printStackTrace();
			} finally {

				try {
					if (statement != null)
						statement.close();
				} catch (SQLException e) {
					System.out.println("服務器端：數據庫关闭Statement失败");
					e.printStackTrace();
				}
			}
		}

	}

}

