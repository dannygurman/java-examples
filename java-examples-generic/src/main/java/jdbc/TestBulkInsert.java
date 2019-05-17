package jdbc;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;

public class TestBulkInsert {

	static final String JDBC_DRIVER="com.mysql.jdbc.Driver";
	static final String SERVER_IP = "127.0.0.1";
	static final String DATABASE_NAME = "vision";	
	static final String URL = "jdbc:mysql://"+SERVER_IP+"/"+DATABASE_NAME;
	static final String USER = "root";
	static final String PASS = "rad123";

	static int sizeOfBulk = 2000;
	static int iterations = 100;

	public static void main(String[] args) {

		FileBulkInserter BulkInserter = new FileBulkInserter();

		Connection conn = initSqlConnection();
		try{
			String testTablename = BdosRealTimeController.getTableName();
			for (int itr =1; itr <= iterations ; itr++ ) {
				List <MsgContent> bdosRealTimeControlerList = createBdosrealTimeControlerList (sizeOfBulk);
				System.out.println(" ** Iteration :"+itr);
				countRecordsInTable ( conn, testTablename);				
				System.out.println();
				System.out.println("Inserting "+sizeOfBulk+" new entries into table: "+testTablename );
				BulkInserter.bulkInsert (conn, testTablename, BdosRealTimeController.getColumnsNames(), bdosRealTimeControlerList);	
				System.out.println();				
			}
		} 	catch(Exception ex) {
			System.out.println("Error: "+ex.getCause()+" message:"+ex.getMessage());			
		} finally {
			try
			{
				if (conn != null)
					conn.close();
			}
			catch (Exception e){}
		}


	}






	private static Connection  initSqlConnection () {
		Connection conn = null;;
		try {
			Class.forName(JDBC_DRIVER);
			conn = (Connection) DriverManager.getConnection(URL, USER, PASS);			
		}
		catch(Exception ex) {
			System.out.println("Error: "+ex.getCause()+" message:"+ex.getMessage());
			System.exit(1);
		}

		return conn; 
	}

	private static void  countRecordsInTable (Connection conn,String tableName) {
		Statement statement = null;
		String query = "select count(*) from "+tableName;
		try{
			statement =conn.createStatement();
			ResultSet rs=statement.executeQuery(query);                  
			//Extact result from ResultSet rs
			while(rs.next()){
				long count = rs.getLong("COUNT(*)");
				System.out.println("Currently there is "+count+ " entries in table "+tableName);                              
			}
			// close ResultSet rs
			rs.close();			
		} 	catch(Exception ex) {
			System.out.println("Error: "+ex.getCause()+" message:"+ex.getMessage());			
		} finally {
			try
			{
				if (statement != null)
					statement.close();
			}
			catch (Exception e){}
		}

	}


	private static ArrayList<MsgContent>  createBdosrealTimeControlerList (int size) {
		ArrayList<MsgContent> bdosrealTimeControlerList = new ArrayList<MsgContent>();

		String deviceIp = "1.1.1.1";
		String policyName = "TestPolicy";
		int isIpv4 = 1;
		int protection = 2;				
		int direction = 1;
		int protectionState = 0;
		int doa = 2;
		int isTcp = 1;

		for (int i = 0 ;i <size ; i++) {
			BdosRealTimeController bdosRealTimeController = new BdosRealTimeController( deviceIp,  policyName, isIpv4,  protection,  direction,  protectionState, doa, isTcp);	
			bdosrealTimeControlerList.add(bdosRealTimeController);
		}

		return bdosrealTimeControlerList;
	}

}
