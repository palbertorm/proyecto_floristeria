package n2exercici1.services.mysqlDAO;

import n2exercici1.connections.SQLBBDD;
import n2exercici1.services.productsDAO.DAOManager;
import n2exercici1.services.productsDAO.DecorationDAO;
import n2exercici1.services.productsDAO.FlowerDAO;
import n2exercici1.services.productsDAO.TreeDAO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.stream.Stream;

import static n2exercici1.services.InputData.askString;

public class MySQLManager implements DAOManager {

    private Connection connection;
    private DecorationDAO decorationDAO;
    private FlowerDAO flowerDAO;
    private TreeDAO treeDAO;

    public MySQLManager() {
        try {
            this.connection = MySQLConnection.getConnection();
        } catch (SQLException e) {
            System.out.println("Connection failed." + e.getMessage());
        }
    }

    @Override
    public FlowerDAO getFlowerDAO() {
        if (flowerDAO == null) flowerDAO = new MySQLFlowerDAO(this.connection);
        return flowerDAO;
    }
    @Override
    public TreeDAO getTreeDAO() {
        //if (treeDAO == null) treeDAO = new MySQLTreeDAO(connection);
        return treeDAO;
    }
    @Override
    public DecorationDAO getDecorationDAO() {
        //if (decorationDAO == null) decorationDAO = new MySQLDecorationDAO(connection);
        return decorationDAO;
    }
}
