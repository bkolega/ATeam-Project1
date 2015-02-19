package aerentals.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SqlDatabaseRowSet implements DatabaseRowSet {
	private ResultSet results;
	
	SqlDatabaseRowSet(ResultSet results) {
		this.results = results;
	}

	@Override
	public Object getField(String name) {
		try {
			return results.getObject(name);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public boolean next() {
		try {
			if (!results.next()) {
				results.close();
				return false;
			} else {
				return true;
			}
		} catch (SQLException e) {
			Logger logger = Logger.getLogger(SqlDatabaseRowSet.class.getName());
            logger.log(Level.SEVERE, e.getMessage(), e);
    		return true;
		}
	}
	
//	@Override
//	public void setField(String name, Object value) {
//		// TODO Auto-generated method stub
//
//	}

}
