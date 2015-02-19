package aerentals.database;

import java.net.URL;

import org.json.JSONObject;

// TODO Implement
public class JsonDatabaseProvider implements DatabaseProvider {
	JSONObject jsonObject;
	
	JsonDatabaseProvider(URL jsonURL) {
		// TODO: Implement
	}

	@Override
	public DatabaseRowSet getRows(String tableName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub

	}

}
