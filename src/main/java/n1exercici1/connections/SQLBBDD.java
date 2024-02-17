package n1exercici1.connections;

import n1exercici1.products.Decoration;
import n1exercici1.products.Flower;
import n1exercici1.products.Product;
import n1exercici1.products.Tree;
import n1exercici1.products.enums.MadeOf;
import n1exercici1.sales.Sale;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SQLBBDD {

    private static String stockDirectory;
    private static String salesDirectory;

    public static List<Product> getProductList(String sqlFileName){
        stockDirectory = sqlFileName;
        List<Product> productList = new ArrayList<>();
        Product newProduct;
        try (ResultSet reader = ((SQLBBDDConnection.getConnection(sqlFileName)).createStatement()).executeQuery("SELECT * FROM products")) {
            while (reader.next()) {
                newProduct = createProductFromBBDD(reader);
                if (newProduct!=null) productList.add(newProduct);
            }
        } catch (SQLException e) {
            System.out.println("Database sql doesn't exists or wrong path provided");
        }
        return productList;
    }
    private static Product createProductFromBBDD(ResultSet reader) throws SQLException {
        Product newProduct;
        switch (reader.getString(2).toUpperCase()){
            case "FLOWER" -> newProduct = new Flower(reader.getString(3),reader.getDouble(4),reader.getString(5));
            case "TREE" -> newProduct = new Tree(reader.getString(3), reader.getDouble(4), reader.getDouble(5));
            case "DECORATION" -> {
                if (reader.getString(5).equalsIgnoreCase("WOOD")){
                    newProduct = new Decoration(reader.getString(3), reader.getDouble(4), MadeOf.WOOD);
                } else {
                    newProduct = new Decoration(reader.getString(3), reader.getDouble(4), MadeOf.PLASTIC);
                }
            }
            default -> newProduct = null;
        }
        return newProduct;
    }
    public static List<Sale> getSaleList (String sqlFileName){
        salesDirectory = sqlFileName;
        List<Sale> saleList = new ArrayList<>();
        List<String> productList;
        try (ResultSet reader = ((SQLBBDDConnection.getConnection(sqlFileName)).createStatement()).executeQuery("SELECT * FROM sales")) {
            while (reader.next()){
                productList = Arrays.asList((reader.getString(4)).split(";"));
                saleList.add(new Sale(reader.getDouble(2),reader.getDate(3),productList));
            }
        } catch (SQLException e) {
            System.out.println("Database sql doesn't exists or wrong path provided");
        }
        return saleList;
    }

    public static void returnProductList(List<Product> productList){
        try (Connection connection = SQLBBDDConnection.getConnection(stockDirectory);
             PreparedStatement delete = connection.prepareStatement("DELETE FROM products");
             PreparedStatement insert = connection.prepareStatement("INSERT INTO products (id, type, name, price, attribute) VALUES (?, ?, ?, ?, ?)")){
            delete.executeUpdate();
            for (Product product : productList) {
                insert.setInt(1, product.getIdProduct());
                insert.setString(2, product.getClass().getSimpleName());
                insert.setString(3, product.getName());
                insert.setDouble(4, product.getPrice());
                switch (product) {
                    case Tree tree -> insert.setDouble(5, tree.getHeight());
                    case Flower flower -> insert.setString(5, flower.getColor());
                    case Decoration decoration -> insert.setString(5, decoration.getMaterial());
                    default -> insert.setString(5, "");
                }
                insert.addBatch();
            }
            insert.executeBatch();
        } catch (SQLException e){
            System.out.println("The changes have not been saved at the database. Wrong path provided.");
        }
    }
    public static void returnSaleList(List<Sale> saleList){
        try (Connection connection = SQLBBDDConnection.getConnection(salesDirectory);
             PreparedStatement delete = connection.prepareStatement("DELETE FROM sales");
             PreparedStatement insert = connection.prepareStatement("INSERT INTO sales (id, price, date, productList) VALUES (?, ?, ?, ?)")){
            delete.executeUpdate();
            for (Sale sale : saleList){
                insert.setInt(1, sale.getIdSale());
                insert.setDouble(2, sale.getSaleAmount());
                insert.setDate(3, (Date) sale.getSaleDate());
                insert.setString(4, String.join(";", sale.getProductList()));
                insert.addBatch();
            }
            insert.executeBatch();
        } catch (SQLException e){
            System.out.println("The changes have not been saved at the database. Wrong path provided.");
        }
    }

}
