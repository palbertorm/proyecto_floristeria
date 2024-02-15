package n1exercici1;

import n1exercici1.products.Product;

import java.util.List;

public class Sale {

    private int idSale;
    private static int idCounter;
    private final List<Product> productList;
    private final double salePrice;

    public Sale (List<Product> productList, double salePrice){
        this.productList = productList;
        this.salePrice = salePrice;
        this.idSale += idCounter++;
    }

    public double getSalePrice(){
        return this.salePrice;
    }
    public int getSaleID(){
        return this.idSale;
    }
    public String toString(){
        return "Sale: " + this.productList + ", Price: " + this.salePrice + "€.";
    }
}
