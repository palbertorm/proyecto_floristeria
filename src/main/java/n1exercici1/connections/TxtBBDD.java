package n1exercici1.connections;

import n1exercici1.products.Decoration;
import n1exercici1.products.Flower;
import n1exercici1.products.Product;
import n1exercici1.products.Tree;
import n1exercici1.products.enums.MadeOf;
import n1exercici1.sales.Sale;

import java.io.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TxtBBDD {

    private static String stockDirectory;
    private static String salesDirectory;

    public static List<String> getFileList (){
        List<String> fileList = null;
        File directory = new File("");
        if (directory.isDirectory()){
            File[] files = directory.listFiles();
            if (files != null) {
                fileList = new ArrayList<>();
                for (File file : files){
                    if (file.isFile()) {
                        fileList.add(file.getName());
                    }
                }
            }
        }
        return fileList;
    }

    public static List<Product> getProductList(String txtFileName){
        stockDirectory = txtFileName;
        List<Product> productList = new ArrayList<>();
        for (String[] s : getTxtLines(txtFileName)) {
            switch (s[1].toUpperCase()){
                case "FLOWER" -> productList.add(new Flower(s[2],Double.parseDouble(s[3]),s[4]));
                case "TREE" -> productList.add(new Tree(s[2],Double.parseDouble(s[3]),Double.parseDouble(s[4])));
                case "DECORATION" -> {
                    if (s[4].equalsIgnoreCase("WOOD")){
                        productList.add(new Decoration(s[2],Double.parseDouble(s[3]), MadeOf.WOOD));
                    } else {
                        productList.add(new Decoration (s[2],Double.parseDouble(s[3]), MadeOf.PLASTIC));
                    }
                }
            }
        }
        return productList;
    }
    public static List<Sale> getSaleList (String txtFileName){
        salesDirectory = txtFileName;
        List<Sale> saleListFromTxt = new ArrayList<>();
        List<String> productListFromTxt = new ArrayList<>();
        for (String[] s : getTxtLines(txtFileName)){
            productListFromTxt.addAll(Arrays.asList(s).subList(3, s.length));
            saleListFromTxt.add(new Sale(Integer.parseInt(s[1]), Date.valueOf(s[2]), productListFromTxt));
        }
        return saleListFromTxt;
    }
    private static List<String[]> getTxtLines(String fileToRead) {
        List<String[]> txtLines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileToRead))) {
            String line;
            while ((line = reader.readLine()) != null) {
                txtLines.add(line.split(";"));
            }
        } catch (IOException e) {
            System.out.println("Database txt doesn't exists or wrong path provided");
        }
        return txtLines;
    }

    public static void returnProductList(List<Product> productList){
        try (PrintWriter writer = new PrintWriter(new FileWriter(new File(stockDirectory).getAbsoluteFile(), true))){
            String productAttribute;
            for (Product product : productList){
                switch (product){
                    case Tree tree -> productAttribute = String.valueOf(tree.getHeight());
                    case Flower flower -> productAttribute = flower.getColor();
                    case Decoration decoration -> productAttribute = decoration.getMaterial();
                    default -> productAttribute="";
                }
                writer.println(product.getIdProduct() + ";"  + product.getClass().getSimpleName() + ";" + product.getName()+ ";" + product.getPrice() + ";" + productAttribute);
            }
        } catch (IOException e){
            System.out.println("The changes have not been saved at the database. Wrong path provided.");
        }
    }
    public static void returnSaleList(List<Sale> saleList){
        try (PrintWriter writer = new PrintWriter(new FileWriter(new File(salesDirectory).getAbsoluteFile(), true))){
            for (Sale sale : saleList){
                writer.print(sale.getIdSale()+ ";" + sale.getSaleAmount() + ";" + sale.getSaleDate());
                for (String product : sale.getProductList()){
                    writer.print(";" + product);
                }
                writer.println();
            }
        } catch (IOException e){
            System.out.println("The changes have not been saved at the database. Wrong path provided.");
        }
    }

}
