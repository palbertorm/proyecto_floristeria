package n3exercici1.connections;

import com.mongodb.client.MongoCollection;
import n3exercici1.products.Decoration;
import n3exercici1.products.Flower;
import n3exercici1.products.Product;
import n3exercici1.products.Tree;
import n3exercici1.products.enums.MadeOf;
import n3exercici1.sales.Sale;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NoSQLBBDD {

    private static String dataBaseName;

    public static void setNoSqlFileName(String fileName){
        dataBaseName = fileName;
    }

    public static List<Product> getProductList(){
        List<Product> productList = new ArrayList<>();
        try {
            Product newProduct;
            for (Document doc : NoSQLBBDDConnection.getConnection(dataBaseName,"products").find()){
                newProduct = createProductFromBBDD(doc);
                if (newProduct!=null) productList.add(newProduct);
            }
        } catch (Exception e) {
            System.out.println("The product list could not be recovered.");
            productList = null;
        }
        return productList;
    }
    private static Product createProductFromBBDD(Document doc) {
        Product newProduct;
        switch (doc.getString("type")){
            case "FLOWER" -> newProduct = new Flower(doc.getString("name"),doc.getDouble("price"),doc.getString("attribute"));
            case "TREE" -> newProduct = new Tree(doc.getString("name"),doc.getDouble("price"),doc.getDouble("attribute"));
            case "DECORATION" -> {
                if (doc.getString("attribute").equalsIgnoreCase("WOOD")){
                    newProduct = new Decoration(doc.getString("name"),doc.getDouble("price"), MadeOf.WOOD);
                } else {
                    newProduct = new Decoration(doc.getString("name"),doc.getDouble("price"), MadeOf.PLASTIC);
                }
            }
            default -> newProduct = null;
        }
        return newProduct;
    }
    public static List<Sale> getSaleList (){
        List<Sale> saleList = new ArrayList<>();
        try {
            List<String> productList;
            for (Document doc : NoSQLBBDDConnection.getConnection(dataBaseName,"sales").find()){
                productList = Arrays.asList(doc.getString("productList").split(";"));
                saleList.add(new Sale(doc.getDouble("totalPrice"), doc.getDate("date"), productList));
            }
        } catch (Exception e) {
            System.out.println("The sales list could not be recovered.");
            saleList = null;
        }
        return saleList;
    }

    public static void returnProductList(List<Product> productList){
        try {
            MongoCollection<Document> productCollection = NoSQLBBDDConnection.getConnection(dataBaseName,"products");
            productCollection.deleteMany(new Document());
            List<Document> productDocuments = new ArrayList<>();
            for (Product product : productList){
                productDocuments.add(productToDocument(product));
            }
            productCollection.insertMany(productDocuments);
        } catch (Exception e){
            System.out.println("The stock changes could not have been saved at the database. Could not connect to the database.");
        }
    }
    private static Document productToDocument(Product product){
        return new Document()
                .append("type", product.getType())
                .append("name", product.getName())
                .append("price", product.getPrice())
                .append("attribute", product.getAttribute());
    }
    public static void returnSaleList(List<Sale> saleList){
        try {
            MongoCollection<Document> saleCollection = NoSQLBBDDConnection.getConnection(dataBaseName,"sales");
            saleCollection.deleteMany(new Document());
            List<Document> saleDocuments = new ArrayList<>();
            for (Sale sale : saleList){
                saleDocuments.add(saleToDocument(sale));
            }
            saleCollection.insertMany(saleDocuments);
        } catch (Exception e){
            System.out.println("The history sales changes could not have been saved at the database. Could not connect to the database.");
        }
    }
    private static Document saleToDocument(Sale sale){
        return new Document()
                .append("totalPrice", sale.getSaleAmount())
                .append("date", sale.getSaleDate())
                .append("productList", String.join(";", sale.getProductList()));
    }

}
