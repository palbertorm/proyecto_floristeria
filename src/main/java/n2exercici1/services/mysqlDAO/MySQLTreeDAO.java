package n2exercici1.services.mysqlDAO;

import n2exercici1.products.Tree;
import n2exercici1.services.productsDAO.TreeDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLTreeDAO implements TreeDAO {

    final String INSERT = "INSERT INTO products (type, name, price, attribute) VALUES (?, ?, ?, ?)";
    final String DELETE = "DELETE FROM products WHERE id = ?";
    final String GETALL = "SELECT * FROM products WHERE type = \"TREE\"";
    final String GETONE = " SELECT * FROM products WHERE type = \"TREE\" && name = ?";
    private final Connection connection;

    public MySQLTreeDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Tree tree) {
        try(PreparedStatement insert = connection.prepareStatement(INSERT)) {
            insert.setString(1, tree.getType());
            insert.setString(2, tree.getName());
            insert.setDouble(3, tree.getPrice());
            insert.setString(4, tree.getAttribute());
            if (insert.executeUpdate() == 0){
                System.out.println("This tree could not be stocked");
            }
        } catch (SQLException e) {
            System.out.println("The stock changes could not be made: " + e.getMessage());
        }
    }
    @Override
    public void delete(Tree tree) {
        try(PreparedStatement delete = connection.prepareStatement(DELETE)) {
            delete.setString(3, tree.getName());
            if (delete.executeUpdate() == 0){
                System.out.println("This tree is not in stock.");
            }
        } catch (SQLException e) {
            System.out.println("The stock changes could not be made: " + e.getMessage());
        }
    }
    @Override
    public List<Tree> getAll() {
        List <Tree> treeList = new ArrayList<>();
        try (ResultSet reader = (connection.prepareStatement(GETALL)).executeQuery()) {
            while (reader.next()) {
                treeList.add(createTree(reader));
            }
        } catch (SQLException e) {
            treeList = null;
        }
        return treeList;
    }
    @Override
    public Tree getOne(String name) {
        Tree tree = null;
        try(PreparedStatement getOne = connection.prepareStatement(GETONE);
            ResultSet reader = getOne.executeQuery()) {
            getOne.setString(1, name);
            if (reader.next()) {
                tree = createTree(reader);
            }
        } catch (SQLException e) {
            tree = null;
        }
        return tree;
    }
    private Tree createTree(ResultSet reader) throws SQLException {
        return new Tree(reader.getString(3), reader.getDouble(4), reader.getDouble(5));
    }

    @Override
    public void update(Tree tree) {

    }
}
