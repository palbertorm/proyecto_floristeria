package n1exercici1.services;

import n1exercici1.connections.SQLBBDD;
import n1exercici1.connections.TxtBBDD;
import n1exercici1.products.Product;
import n1exercici1.sales.Sale;

import java.util.List;

public class DAOService {

    private List<Product> productList;
    private List<Sale> saleList;
    private List<String> flowerShopList;

    public boolean compareShopName (String shopName){
        flowerShopList = TxtBBDD.getFileList();
        return flowerShopList.stream().anyMatch(shop -> shop.contains(shopName));
    }

    public List<Product> getProductList (String flowerShopName){
        //this.productList = FakeBBDD.getProductList();
        this.productList = TxtBBDD.getProductList(flowerShopName+"ProductList.txt");
        this.productList = SQLBBDD.getProductList(flowerShopName+"ProductList.sql");
        // GET DATA FROM NOSQL
        return this.productList;
    }
    public List<Sale> getSaleList (String flowerShopName){
        //this.saleList = FakeBBDD.getSaleList();
        this.saleList = TxtBBDD.getSaleList(flowerShopName+"SaleList.txt");
        this.saleList = SQLBBDD.getSaleList(flowerShopName+"SaleList.sql");
        // GET DATA FROM NOSQL
        return this.saleList;
    }

    public void exportProductList (List<Product> productList){
        //this.productList = productList;
        TxtBBDD.returnProductList(productList);
        SQLBBDD.returnProductList(productList);
        // RETURN DATA TO NOSQL
    }
    public void exportSaleList (List<Sale> saleList){
        //this.saleList = saleList;
        TxtBBDD.returnSaleList(saleList);
        SQLBBDD.returnSaleList(saleList);
        // RETURN DATA TO NOSQL
    }

}
