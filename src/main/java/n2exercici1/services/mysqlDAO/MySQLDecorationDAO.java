package n2exercici1.services.mysqlDAO;

import n2exercici1.products.Decoration;
import n2exercici1.products.enums.MadeOf;
import n2exercici1.services.productsDAO.DecorationDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLDecorationDAO implements DecorationDAO {

    final String INSERT = "INSERT INTO products (type, name, price, attribute) VALUES (?, ?, ?, ?)";
    final String DELETE = "DELETE FROM products WHERE id = ?";
    final String GETALL = "SELECT * FROM products WHERE type = \"DECORATION\"";
    final String GETONE = " SELECT * FROM products WHERE type = \"DECORATION\" && name = ?";
    private final Connection connection;

    public MySQLDecorationDAO(Connection connection) {
        this.connection = connection;
    }
    @Override
    public void insert(Decoration decoration) {
        try(PreparedStatement insert = connection.prepareStatement(INSERT)) {
            insert.setString(1, decoration.getType());
            insert.setString(2, decoration.getName());
            insert.setDouble(3, decoration.getPrice());
            insert.setString(4, decoration.getAttribute());
            if (insert.executeUpdate() == 0){
                System.out.println("This decoration could not be stocked");
            }
        } catch (SQLException e) {
            System.out.println("The stock changes could not be made: " + e.getMessage());
        }
    }
    @Override
    public void delete(Decoration decoration) {
        try(PreparedStatement delete = connection.prepareStatement(DELETE)) {
            delete.setString(3, decoration.getName());
            if (delete.executeUpdate() == 0){
                System.out.println("This decoration is not in stock.");
            }
        } catch (SQLException e) {
            System.out.println("The stock changes could not be made: " + e.getMessage());
        }
    }
    @Override
    public List<Decoration> getAll() {
        List <Decoration> decorationList = new ArrayList<>();
        try (ResultSet reader = (connection.prepareStatement(GETALL)).executeQuery()) {
            while (reader.next()) {
                decorationList.add(createDecoration(reader));
            }
        } catch (SQLException e) {
            decorationList = null;
        }
        return decorationList;
    }
    @Override
    public Decoration getOne(String name) {
        Decoration decoration = null;
        try(PreparedStatement getOne = connection.prepareStatement(GETONE);
            ResultSet reader = getOne.executeQuery()) {
            getOne.setString(1, name);
            if (reader.next()) {
                decoration = createDecoration(reader);
            }
        } catch (SQLException e) {
            decoration = null;
        }
        return decoration;
    }
    private Decoration createDecoration(ResultSet reader) throws SQLException {
        return new Decoration(reader.getString(3),
                reader.getDouble(4),
                reader.getString(5).equalsIgnoreCase("WOOD") ? MadeOf.WOOD : MadeOf.PLASTIC);
    }

    @Override
    public void update(Decoration decoration) {

    }
}
