package jdbc;

import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class FileBulkInserter extends SqlInserter  {

	public void bulkInsert(Connection conn,String tableName, String [] colNames, List <MsgContent> testEntities)  throws Exception {
		Statement statement = null;
		try {
			statement = (com.mysql.jdbc.Statement) conn.createStatement();
			//statement.execute("SET UNIQUE_CHECKS=0; ");
			//statement.execute("ALTER TABLE "+tableName+" DISABLE KEYS");

			// Define the query we are going to execute
			StringBuffer statementText = new StringBuffer(200);
			statementText.append("LOAD DATA LOCAL INFILE 'file.txt' INTO TABLE ");
			statementText.append(tableName);
			statementText.append(" (");
			for (int i = 0; i < colNames.length-1; i++) {
				statementText.append(colNames[i]);
				statementText.append(",");
			}
			statementText.append(colNames[colNames.length-1]);		
			statementText.append(")");

			//Create StringBuilder to String that will become lambdaAndStream
			StringBuilder builder = new StringBuilder(testEntities.size()*20);
			// Iterate over map and create tab-text string
			for (MsgContent testEntity : testEntities) {
				List <String> entityValues = testEntity.getCellsValuesAsStr();
				for (String testEntityStr :entityValues) {
					builder.append(testEntityStr);
					builder.append('\t');
				}			
				builder.append('\n');
			}

			// Create lambdaAndStream from String Builder
			InputStream is = IOUtils.toInputStream(builder.toString());

			// Setup our input lambdaAndStream as the source for the local infile
			statement.setLocalInfileInputStream(is);

			// Execute the load infile
			long startTime = System.currentTimeMillis();
			statement.execute(statementText.toString());
			
			//Print results
			long durationInMiliSec = System.currentTimeMillis() - startTime;
			printResult (durationInMiliSec , (double) testEntities.size());
             
			// Turn the checks back on
			//statement.execute("ALTER TABLE affinity ENABLE KEYS");
			//statement.execute("SET UNIQUE_CHECKS=1; ");

		} finally {
			if (statement != null)
				statement.close();
		}			
	}





}
