package examples.persistence.jdbc;

public class SqlInserter  {

	public void printResult (long durationInMiliSec , double numOfEntries) throws Exception {
		double durationInSec = (double) (durationInMiliSec/ 1000.0 );		
		System.out.println( "_______________________________________________");
		System.out.println("Bulk insert operation took:"+durationInSec+" sec." );
		System.out.println("Bulk insert average duration:"+((int)(numOfEntries/durationInSec))+" entries per sec." );
		System.out.println( "______________________________________");
	}
}
