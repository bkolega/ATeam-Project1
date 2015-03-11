package aerentals.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SqlDatabaseProvider implements DatabaseProvider {
	private Connection conn;
	
	public SqlDatabaseProvider(String url, String username, String password) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			Logger logger = Logger.getLogger(SqlDatabaseProvider.class.getName());
			logger.log(Level.SEVERE, e.getMessage(), e);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() {
		return conn;
	}

	// XXX: Don't let a user directly enter a String that will be given to this method.
	//      There is an SQL injection risk if this happens. Unfortunately, it looks like
	//      a prepared statement can't be used to fix this.
	@Override
	public DatabaseRowSet getRows(String tableName) {
		Statement stmt = null;
		ResultSet results = null;
		try {
			stmt = conn.createStatement();
			stmt.execute("SELECT * FROM " + tableName);
			results = stmt.getResultSet();
			return new SqlDatabaseRowSet(results);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public void close() {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
