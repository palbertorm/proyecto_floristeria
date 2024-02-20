package n2exercici1.services.mysqlDAO;

public class MySQLQueries {

    public static final String INSERT_PRODUCT = "INSERT INTO products (type, name, price, attribute) VALUES (?, ?, ?, ?)";
    public static final String INSERT_SALE = "INSERT INTO sales (totalPrice, date, productList) VALUES (?, ?, ?)";
    public static final String DELETE_PRODUCT = "DELETE FROM products WHERE LOWER(name) = LOWER(?)";
    public static final String GET_ALL_FLOWERS = "SELECT * FROM products WHERE type = \"FLOWER\"";
    public static final String GET_ALL_TREES = "SELECT * FROM products WHERE type = \"TREE\"";
    public static final String GET_ALL_DECORATIONS = "SELECT * FROM products WHERE type = \"DECORATION\"";
    public static final String GET_ALL_SALES = "SELECT * FROM sales";
    public static final String GET_FLOWER = "SELECT * FROM products WHERE type = \"FLOWER\" AND LOWER(name) = LOWER(?)";
    public static final String GET_TREE = "SELECT * FROM products WHERE type = \"TREE\" AND LOWER(name) = LOWER(?)";
    public static final String GET_DECORATION = "SELECT * FROM products WHERE type = \"DECORATION\" AND LOWER(name) = LOWER(?)";
    public static final String GET_SALE = "SELECT * FROM sales WHERE id = ?";

}
