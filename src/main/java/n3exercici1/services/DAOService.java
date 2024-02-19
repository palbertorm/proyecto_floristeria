package n3exercici1.services;

import n3exercici1.connections.NoSQLBBDD;
import n3exercici1.products.Product;
import n3exercici1.sales.Sale;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class DAOService {

    private static final String directory = "src/main/resources/";

    public boolean checkShopName (String shopName){
        boolean exists;
        try (Stream<Path> files = Files.walk(Paths.get(directory))){
            exists = files.map(Path::getFileName).map(Path::toString).anyMatch(fileName -> fileName.toLowerCase().contains(shopName.toLowerCase()));
            NoSQLBBDD.setNoSqlFileName(shopName+"flowershop");
        } catch (IOException e){
            exists = false;
        }
        return exists;
    }

    public List<Product> getProductList (){
        return NoSQLBBDD.getProductList();
    }
    public List<Sale> getSaleList (){
        return NoSQLBBDD.getSaleList();
    }

    public void exportProductList (List<Product> productList){
        NoSQLBBDD.returnProductList(productList);
    }
    public void exportSaleList (List<Sale> saleList){
        NoSQLBBDD.returnSaleList(saleList);
    }

}
