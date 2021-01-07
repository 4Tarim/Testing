package UtilityLibraries;

import static org.testng.Assert.assertEquals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.log4j.Logger;

public class DatabaseManager {
	final static Logger logger = Logger.getLogger(DatabaseManager.class);

	private String databaseServerName;
	private String databasePort;
	private String databaseName;
	private String userName;
	private String userPassword;
	private String dbConnectionURL;

	private Connection conn = null;
	private Statement statement = null;
	private ResultSet resultSet = null;

	private void connectToOracleDB() {
		databaseServerName = "localhost";
		databasePort = "1521";
		databaseName = "XEPDB1";
		userName = "hr";
		userPassword = "hr";
		dbConnectionURL = "jdbc:oracle:thin:@//" + databaseServerName + ":" + databasePort + "/" + databaseName;

		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection(dbConnectionURL, userName, userPassword);
			statement = conn.createStatement();
			logger.info("Oracle dababase connection is successful !");
		} catch (Exception e) {
			logger.error("Error: ", e);
		}
	}

	public ResultSet runSQLQuery(String sqlQuery) {
		try {
			connectToOracleDB();
			resultSet = statement.executeQuery(sqlQuery);
		} catch (Exception e) {
			logger.error("Error: ", e);
		}
		logger.info("resultset data: " + resultSet);
		return resultSet;
	}

	public void extractDBData(ResultSet resultSet) {
		logger.info("data ----------");
		try {
			logger.info("Country_ID" + " \t" + "Country_Name" + " \t" + "Region_ID");
			logger.info("-----------------------------------------------------------------");

			while (resultSet.next()) {
				String countryID = resultSet.getString("COUNTRY_ID");
				String countryName = resultSet.getString("COUNTRY_NAME");
				int regionID = resultSet.getInt("REGION_ID");

				logger.info(countryID + " \t" + countryName + " \t" + regionID);
			}
		} catch (Exception e) {
			logger.error("Error: ", e);
		}
	}

	public String extractDBData(ResultSet resultSet, boolean isSeondMethod) {
		logger.info("data ----------");
		String finaldata = null;
		try {
			if (resultSet.next()) {
				logger.info("Country_ID" + " \t" + "Country_Name" + " \t" + "Region_ID");
				logger.info("-----------------------------------------------------------------");
			}
			String countryName = resultSet.getString("COUNTRY_NAME");
			finaldata = countryName;
			logger.info("data: " + countryName);

		} catch (Exception e) {
			logger.error("Error: ", e);
		}
		return finaldata;
	}

	public String getData(String sqlQuery) {
		String resultData = null;		
		try {
			resultSet = runSQLQuery(sqlQuery);
			//ResultSetMetaData meta = resultSet.getMetaData();
			//int col = meta.getColumnCount();
			while(resultSet.next()) {
				resultData = resultSet.getObject(1).toString();
				break;
			}			
		} catch (Exception e) {
			logger.error("Error: ", e);
		}
		return resultData;
	}
		
	public static void main(String[] args) {
		// String sql1 = "Select * From Countries";
		String sql2 = "Select Country_Name from Countries where Country_ID='FR'";

		DatabaseManager myDB = new DatabaseManager();
		//ResultSet data = myDB.runSQLQuery(sql2);
		//String result = myDB.extractDBData(data, true);
		String result = myDB.getData(sql2);
		assertEquals(result, "france");
	}
}
