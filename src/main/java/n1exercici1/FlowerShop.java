package n1exercici1;

import n1exercici1.products.*;
import n1exercici1.products.enums.MadeOf;
import n1exercici1.services.Stock;

public class FlowerShop {

    private FlowerShop flowerShop;
    private final String flowerShopName;
    private final Stock stock;

    private FlowerShop (String flowerShopName, Stock stock){
        this.flowerShopName = flowerShopName;
        this.stock = stock;
    }
    public FlowerShop openFlowerShop(String flowerShopName){
        if (flowerShop == null) new FlowerShop(flowerShopName, stock.getStock(flowerShopName));
        return flowerShop;
    }


    public void addTree(String treeName, double treePrice, double treeHeigth){
        stock.addProduct(new Tree(treeName, treePrice, treeHeigth));
    }
    public void printTreeStock(){
        stock.getTreeStock().forEach(System.out::println);
    }
    public void addFlower(String flowerName, double flowerPrice, String flowerColor){
        stock.addProduct(new Flower(flowerName, flowerPrice, flowerColor));
    }
    public void printFlowerStock(){
        stock.getFlowerStock().forEach(System.out::println);
    }
    public void addDecoration(String decorationName, double decorationPrice, MadeOf madeof) {
        stock.addProduct(new Decoration(decorationName, decorationPrice, madeof));
    }
    public void printDecorationStock(){
        stock.getDecorationStock().forEach(System.out::println);
    }
    public void removeProduct(Product product){
        stock.removeProduct(product);
    }
    public void printStock(){
        System.out.println("Tree's quantity: " + stock.getTreeStock().size() + ".\n" +
                "Flower's quantity: " + stock.getFlowerStock().size() + ".\n" +
                "Decoration's quantity: " + stock.getDecorationStock().size() + ".");
    }
    public void printShopValue(){
        System.out.println("The actual value of the " + flowerShopName + " shop is " + stock.getStockValue() + "€.");
    }

}
