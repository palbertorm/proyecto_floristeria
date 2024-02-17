package n1exercici1.services;

import n1exercici1.connections.SQLBBDD;
import n1exercici1.connections.TxtBBDD;
import n1exercici1.products.Product;
import n1exercici1.sales.Sale;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class DAOService {

    private List<Product> productList;
    private List<Sale> saleList;

    private static final String directory = "";

    public boolean checkShopName (String shopName){
        boolean exists;
        try (Stream<Path> files = Files.walk(Paths.get(directory))){
            exists = files.map(Path::getFileName).map(Path::toString).anyMatch(fileName -> fileName.equalsIgnoreCase(shopName));
        } catch (IOException e){
            exists = false;
        }
        return exists;
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
