package n2exercici1.services.productsDAO;

import java.util.List;

public interface DAOGeneric<T, K> {
    void insert(T item);
    void delete(T item);
    void update(T item);
    List<T> getAll();
    T getOne(K id);
}
