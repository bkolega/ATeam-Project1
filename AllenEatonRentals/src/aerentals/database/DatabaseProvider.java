package aerentals.database;

public interface DatabaseProvider {
	public DatabaseRowSet getRows(String tableName);
	
	public void close();
}
