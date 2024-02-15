package n1exercici1.services;

import n1exercici1.Sale;
import n1exercici1.products.Product;

import java.util.ArrayList;
import java.util.List;

public class DAOService {

    private List<Product> productList;
    private List<Sale> saleList;
    private List<String> flowerShopList;

    public boolean compareShopName (String shopName){
        flowerShopList = FakeBBDD.getFlowerShopList();
        return flowerShopList.stream().anyMatch(shop -> shop.equalsIgnoreCase(shopName));
    }

    public List<Product> getProductList (String flowerShopName){
        this.productList = FakeBBDD.getProductList(); // GET FAKE DATA
        this.productList = GetDataFromTxt.getProductList(flowerShopName+"ProductList.txt"); // GET DATA FROM TXT
        // GET DATA FROM SQL
        // GET DATA FROM NOSQL
        return this.productList;
    }
    public List<Sale> getSaleList (String flowerShopName){
        this.saleList = FakeBBDD.getSaleList(); // GET FAKE DATA
        // GET DATA FROM TXT
        // GET DATA FROM SQL
        // GET DATA FROM NOSQL
        return this.saleList;
    }

    public void exportProductList (List<Product> productList){
        this.productList = productList;
    }
    public void exportSaleList (List<Sale> saleList){
        this.saleList = saleList;
    }

}
