package n1exercici1.services;

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
    public Stock getStock (String shopName){
        if (stock == null) stock = new Stock(service.getProductList(shopName));
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
        //UPDATE DATABASE??
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
        //UPDATE DATABASE??
    }
    private void updateStockValue(Product product, String action){
        stockValue += (action.equals("add") ? product.getPrice() : -product.getPrice());
    }


}
