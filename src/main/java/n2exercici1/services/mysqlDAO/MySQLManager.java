package n2exercici1.services.mysqlDAO;

import n2exercici1.services.productsDAO.*;

import java.sql.Connection;
import java.sql.SQLException;

public class MySQLManager implements DAOManager {

    private Connection connection;
    private DecorationDAO decorationDAO;
    private FlowerDAO flowerDAO;
    private TreeDAO treeDAO;
    private SaleDAO saleDAO;

    public MySQLManager() {
        try {
            this.connection = new MySQLConnection().getConnection();
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
        if (treeDAO == null) treeDAO = new MySQLTreeDAO(connection);
        return treeDAO;
    }
    @Override
    public DecorationDAO getDecorationDAO() {
        if (decorationDAO == null) decorationDAO = new MySQLDecorationDAO(connection);
        return decorationDAO;
    }
    @Override
    public SaleDAO getSaleDAO() {
        if (saleDAO == null) saleDAO = new MySQLSaleDAO(this.connection);
        return saleDAO;
    }
}
