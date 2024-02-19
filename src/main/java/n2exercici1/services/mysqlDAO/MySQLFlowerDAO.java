package n2exercici1.services.mysqlDAO;

import n2exercici1.products.Flower;
import n2exercici1.products.Product;
import n2exercici1.services.productsDAO.FlowerDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLFlowerDAO implements FlowerDAO {
    final String INSERT = "INSERT INTO products (type, name, price, attribute) VALUES (?, ?, ?, ?)";
    final String DELETE = "DELETE FROM products WHERE id = ?";
    final String UPDATE = "UPDATE Tabla SET nombre = ? WHERE id = ?";
    final String GETALL = "SELECT * FROM products WHERE type = \"FLOWER\"";
    final String GETONE = " SELECT flower.id FROM flower";

    private final Connection conn;

    public MySQLFlowerDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Flower item) {
        try(PreparedStatement insert = conn.prepareStatement(INSERT)) {
            insert.setString(1, item.getType());
            insert.setString(2, item.getName());
            insert.setDouble(3, item.getPrice());
            insert.setString(4, item.getAttribute());
            if (insert.executeUpdate() == 0){
                System.out.println("The flower was not saved into stock");
            }
        } catch (SQLException e) {
            System.out.println("The flower was not saved into stock: " + e.getMessage());
        }
    }

    @Override
    public void delete(Flower item) {
        try(PreparedStatement stmt = conn.prepareStatement(DELETE)) {
            stmt.setInt(1, item.getIdProduct());
            if (stmt.executeUpdate() == 0){
                System.out.println("This product does not exist.");
            }
        } catch (SQLException e) {
            System.out.println("The stock changes could not be made: " + e.getMessage());
        }
    }

    @Override
    public void update(Flower item) {
        try (PreparedStatement stmt = conn.prepareStatement(UPDATE)) {

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private Flower convertir(ResultSet res) throws SQLException {
        String name = res.getString("name");
        String attribute = res.getString("attribute");
        double price = res.getDouble("price");
        Product flower = new Flower(name, price, attribute);
        // en caso de id hacer un setId(res.getInt("id"));
        return (Flower) flower;
    }

        @Override
    public List<Flower> getAll() {
           List <Flower> flower = new ArrayList<>();
            try(PreparedStatement stmt = conn.prepareStatement(GETALL)) {
                try (ResultSet res =  stmt.executeQuery()) {
                    while (res.next()) {
                        flower.add(convertir(res));

                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return flower;
    }

    @Override
    public Flower getOne(Integer id) {
        Flower flower = null;
        try(PreparedStatement stmt = conn.prepareStatement(GETONE)) {
            stmt.setInt(1, id);
            try (ResultSet res =  stmt.executeQuery()) {
                if (res.next()) {
                    flower = convertir(res);

                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return flower;
    }
}
