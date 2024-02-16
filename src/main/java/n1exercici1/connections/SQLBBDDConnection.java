package n1exercici1.connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static n1exercici1.services.InputData.askString;

public class SQLBBDDConnection {

    private static String url;
    private static String user;
    private static String password;
    private static boolean initConnection = false;

    private static void checkDataConnection(String sqlFileName){
        if (!initConnection) askData(sqlFileName);
    }
    private static void askData(String sqlFileName){
        url = "jdbc:mysql://localhost:3306/"+sqlFileName.toLowerCase();
        user = askString("Introduce your username: ");
        password = askString("Introduce your password: ");
        initConnection = true;
    }

    public static Connection getConnection(String sqlFileName) throws SQLException {
        checkDataConnection(sqlFileName);
        return DriverManager.getConnection(url, user, password);
    }

}
