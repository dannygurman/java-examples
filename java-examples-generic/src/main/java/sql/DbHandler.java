package sql;

import com.mysql.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DbHandler {
    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_CONNECTION = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";


    private  List<String> sqlStatements = new ArrayList<>();


    public  void executeSqlStatements()  {
        Connection connection = getDBConnection();
        Statement stmt = null;
        try {
            connection.setAutoCommit(false);
            stmt = connection.createStatement();
            for (String statement : sqlStatements) {
                if (statement.toLowerCase().contains("select")) {
                    ResultSet rs = stmt.executeQuery(statement);
                    printResult(statement, rs);
                }
                else {
                    System.out.println("Running statement: "+statement);
                    stmt.execute(statement);
                }
            };
            stmt.close();
            connection.commit();
        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static Connection getDBConnection() {
        Connection dbConnection = null;

        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            return dbConnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }

    private  static void printResult (String query , ResultSet rs ) throws SQLException {
        System.out.println("querying:" + query);
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnsNumber = rsmd.getColumnCount();

        while (rs.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1) System.out.print(",  ");
                String columnValue = rs.getString(i);
                System.out.print( rsmd.getColumnName(i) + " " +columnValue);
            }
            System.out.println("");
        }
    }


    public  void addStatement(String statement) {
        sqlStatements.add(statement);
    }

    public static void main(String[] args) throws  SQLException{
        DbHandler dbHandler = new DbHandler();

        dbHandler.addStatement("CREATE TABLE PERSON(id int primary key, name varchar(255))");
        dbHandler.addStatement("INSERT INTO PERSON(id, name) VALUES(1, 'Anju')");
        dbHandler.addStatement("SELECT * FROM PERSON");
        dbHandler.addStatement("DROP TABLE PERSON");

        dbHandler.executeSqlStatements();
    }

}
