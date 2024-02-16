package n1exercici1.connections;

import n1exercici1.products.Decoration;
import n1exercici1.products.Flower;
import n1exercici1.products.Product;
import n1exercici1.products.Tree;
import n1exercici1.products.enums.MadeOf;
import n1exercici1.sales.Sale;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class FakeBBDD {


    public static List<String> getFlowerShopList (String shopName){
        List<String> flowerShopList = new ArrayList<>();
        flowerShopList.add("Hola");
        flowerShopList.add("Adios");
        return flowerShopList;
    }

    public static List<Product> getProductList(){
        List<Product> productList = new ArrayList<>();
        productList.add(new Tree("Roble",154,5.4));
        productList.add(new Flower("Margarita",1.5,"blanca"));
        productList.add(new Decoration("Espejo",69.99, MadeOf.WOOD));
        return productList;
    }

    public static List<Sale> getSaleList(){
        List<Sale> saleList = new ArrayList<>();
        List<String> productList = new ArrayList<>();
        saleList.add(new Sale(155, new Date(2024-2-15),productList));
        return saleList;
    }

}
