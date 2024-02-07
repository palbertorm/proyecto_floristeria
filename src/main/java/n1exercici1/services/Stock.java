package n1exercici1;

import n1exercici1.products.*;
import java.util.List;

public class Stock {

    private DAOService service;
    private Stock stock;
    private List<Tree> treeStock;
    private List<Flower> flowerStock;
    private List<Decoration> decorationStock;
    private double stockValue;

    private Stock (List<Product> productStock){
        productStock.forEach(this::addProduct);
    }
    public Stock getStock (){
        if (stock == null) stock = new Stock(service.getProductList());
        return stock;
    }

    public List<Tree> getTreeStock(){
        return treeStock;
    }
    public List<Flower> getFlowerStock(){
        return flowerStock;
    }
    public List<Decoration> getDecorationStock(){
        return decorationStock;
    }
    public double getStockValue(){
        return stockValue;
    }

    public void addProduct (Product product) {
        if (product instanceof Tree) {;
            treeStock.add((Tree) product);
        } else if (product instanceof Flower) {
            flowerStock.add((Flower) product);
        } else if (product instanceof Decoration) {
            decorationStock.add((Decoration) product);
        }
        updateStockValue(product, "add");
    }
    public void removeProduct (Product product){
        if (product instanceof Tree) {
            treeStock.remove((Tree) product);
        } else if (product instanceof Flower) {
            flowerStock.remove((Flower) product);
        } else if (product instanceof Decoration) {
            decorationStock.remove((Decoration) product);
        }
        updateStockValue(product, "remove");
    }
    private void updateStockValue(Product product, String action){
        stockValue += (action.equals("add") ? product.getPrice() : -product.getPrice());
    }

    public void printTreeStock(){
        treeStock.forEach(System.out::println);
    }
    public void printFlowerStock(){
        flowerStock.forEach(System.out::println);
    }
    public void printDecorationStock(){
        decorationStock.forEach(System.out::println);
    }
    public void printStock() {
        System.out.println("Tree's quantity: " + treeStock.size() + ".\n" +
                "Flower's quantity: " + flowerStock.size() + ".\n" +
                "Decoration's quantity: " + decorationStock.size() + ".");
    }
}
