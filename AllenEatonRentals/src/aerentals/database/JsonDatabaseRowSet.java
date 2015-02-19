package aerentals.database;

import org.json.JSONObject;

// TODO Implement
public class JsonDatabaseRowSet implements DatabaseRowSet {
	JSONObject jsonObject;
	
	public JsonDatabaseRowSet(JSONObject jsonObject) {
		this.jsonObject = jsonObject;
	}

	@Override
	public Object getField(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean next() {
		// TODO Implement
		return false;
	}

//	@Override
//	public void setField(String name, Object value) {
//		// TODO Auto-generated method stub
//
//	}

}
