package sql.ex1;

import sql.DbHandler;

import java.util.ArrayList;
import java.util.List;

public class SqlExample1 {



    public static void main(String[] args)  {

        DbHandler dbHandler = new DbHandler();

        dbHandler.addStatement("CREATE TABLE PERSON(id int primary key, name varchar(255))");
        dbHandler.addStatement("INSERT INTO PERSON(id, name) VALUES(1, 'Anju')");
        dbHandler.addStatement("SELECT * FROM PERSON");
        dbHandler.addStatement("DROP TABLE PERSON");

        dbHandler.executeSqlStatements();
    }



}
