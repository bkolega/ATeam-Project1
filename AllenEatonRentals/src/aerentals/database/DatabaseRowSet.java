package aerentals.database;

public interface DatabaseRowSet {
	public Object getField(String name);
	public boolean next();
//	public void setField(String name, Object value);
}
