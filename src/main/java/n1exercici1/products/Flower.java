package n1exercici1.products;

public class Tree extends Product{

    private int idTree;
    private int idCounter;
    private String height;

    public Tree (String name, double price, String height){
        super(name, price);
        this.height = height;
        this.idTree += idCounter++;
    }

    public int getIdTree(){
        return this.idTree;
    }
    public String getHeight(){
        return this.height;
    }
    public void setHeight(){
        this.height = height;
    }

    public String toString(){
        return "Tree: " + getName() + ", Height: " + this.height + ", Price: " + getPrice() + "€.";
    }

}
