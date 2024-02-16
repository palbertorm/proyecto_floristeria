package n1exercici1.services.mysqlDAO;

import n1exercici1.connections.MysqlConnection;
import n1exercici1.products.Flower;
import n1exercici1.services.productsDAO.FlowerDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class MySQLFlowerDAO implements FlowerDAO {
    final String INSERT = "INSERT INTO Tabla (col1, clo2) VALUES(?,?)";
    final String UPDATE = "UPDATE Tabla SET nombre = ? WHERE id = ?";
    final String DELETE = "DELETE FROM Tabla WHERE ID = ?";

    private Connection conn;

    public MySQLFlowerDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Flower item) {
    try(PreparedStatement stmt = conn.prepareStatement(INSERT)) {
    stmt.setInt(1, item.getIdProduct());
    stmt.setString(2, item.getName());
    stmt.setString(3, item.getColor());
    stmt.setDouble(4, item.getPrice());
    if (stmt.executeUpdate() == 0){
        System.out.println(" was not inserted");
    }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    }

    @Override
    public void delete(Flower item) {
    try(PreparedStatement stmt = conn.prepareStatement(DELETE)) {

    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    }

    @Override
    public void update(Flower item) {

    }

    @Override
    public List<Flower> getAll() {
        return null;
    }

    @Override
    public Flower getOne(Integer id) {
        return null;
    }
}
