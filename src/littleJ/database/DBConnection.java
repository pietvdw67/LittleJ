package littleJ.database;

import java.sql.Connection;
import java.sql.SQLException;

import za.co.infinity.db.adapter.DataAdapter;
import za.co.infinity.db.adapter.MySQLDataAdapter;
import za.co.infinity.db.adapter.PostgreSQLDataAdapter;


public class DBConnection {
	private static Connection conn = null;
	private static DataAdapter adapter;

	private DBConnection() {
		// Singleton
	}

	public static void setConnection(String url, String username, String password) {
		if (url.contains("postgresql")) {
			adapter = new PostgreSQLDataAdapter();
		} else if (url.contains("mysql")) {
			adapter = new MySQLDataAdapter();
		}

		try {
			conn = adapter.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		return conn;
	}

	public static void closeConnection() {
		adapter.closeConnection(conn);
	}
}
