package n1exercici1;

import n1exercici1.products.Product;
import n1exercici1.services.DAOService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SalesManager {

    private static SalesManager salesManager;
    private final List<Sale> salesHistoryList;
    private double earnedMoney;

    private SalesManager(DAOService service, String flowerShopName) {
        this.salesHistoryList = service.getSaleList(flowerShopName);
        calculateTotalSalesValue();
    }
    public static SalesManager getSalesManager(DAOService service, String flowerShopName) {
        if (salesManager == null) salesManager = new SalesManager(service, flowerShopName);
        return salesManager;
    }
    private void calculateTotalSalesValue(){
        for (Sale sale : this.salesHistoryList){
            this.earnedMoney += sale.getSalePrice();
        }
    }

    public List<Sale> getSalesHistoryList() {
        return this.salesHistoryList;
    }
    public double getEarnedMoney(){
        return this.earnedMoney;
    }
    public void manageTheCart(List<Product> productList, double salePrice){
        productList.sort(Comparator.comparingInt(Product::getIdProduct));
        addSale(new Sale(productList,salePrice));
    }
    private void addSale(Sale sale) {
        this.salesHistoryList.add(sale);
        System.out.println("Sale registered.");
        updateEarnedMoney(sale);
    }
    private void updateEarnedMoney(Sale sale){
        this.earnedMoney += sale.getSalePrice();
    }
    public void printSalesHistory() {
        this.salesHistoryList.forEach(System.out::println);
    }
    public void printTcket(int idSale){
        this.salesHistoryList.get(idSale);
        //AÑADIR MÉTODO PARA GUARDAR INFO EN UN TXT
        System.out.println("\nThe ticket has been printed.");
    }
}
