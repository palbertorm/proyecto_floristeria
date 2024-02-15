package n1exercici1.services;

import n1exercici1.Sale;
import n1exercici1.products.Decoration;
import n1exercici1.products.Flower;
import n1exercici1.products.Product;
import n1exercici1.products.Tree;
import n1exercici1.products.enums.MadeOf;

import java.util.ArrayList;
import java.util.List;

public class FakeBBDD {


    public static List<String> getFlowerShopList (){
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
        saleList.add(new Sale(getProductList(),155));
        return saleList;
    }

}
