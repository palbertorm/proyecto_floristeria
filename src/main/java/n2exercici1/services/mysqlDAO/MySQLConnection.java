package n2exercici1.services.mysqlDAO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.stream.Stream;

import static n2exercici1.services.InputData.askString;

public class MySQLConnection {

    private static final String directory = "src/main/resources/";
    private static String sqlFileName;
    private  String url;
    private  String user;
    private  String password;
    private static boolean initConnection = false;

    public static boolean checkShopName(String shopName){
        boolean exists;
        try (Stream<Path> files = Files.walk(Paths.get(directory))){
            exists = files.map(Path::getFileName).map(Path::toString).anyMatch(fileName -> fileName.toLowerCase().contains(shopName.toLowerCase()));
            sqlFileName = shopName+"flowershop";
        } catch (IOException e){
            exists = false;
        }
        return exists;
    }

    public MySQLConnection (){
        if (!initConnection) askData();
    }

    private void askData(){
        this.url = "jdbc:mysql://localhost:3306/" + sqlFileName;
        this.user = askString("Introduce your username: ");
        this.password = askString("Introduce your password: ");
        initConnection = true;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(this.url, this.user, this.password);
    }

}
