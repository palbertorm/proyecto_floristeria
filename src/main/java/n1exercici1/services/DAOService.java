package n1exercici1.services;

import n1exercici1.products.Product;

import java.util.List;

public class DAOService {

    private List<Product> productList;

    public List<Product> getProductList (String shopName){
        return productList;
    }

}
