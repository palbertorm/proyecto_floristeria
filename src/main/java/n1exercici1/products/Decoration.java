package n1exercici1.products;

import n1exercici1.products.enums.MadeOf;

public class Decoration extends Product{

    private int idTree;
    private int idCounter;
    private MadeOf madeOf;

    public Decoration(String name, double price, MadeOf madeOf){
        super(name, price);
        this.madeOf = madeOf;
        this.idTree += idCounter++;
    }

    public int getIdTree(){
        return this.idTree;
    }
    public MadeOf getMadeOf(){
        return this.madeOf;
    }
    public void setMadeOf(MadeOf madeOf){
        this.madeOf = madeOf;
    }

    public String toString(){
        return "Decoration: " + getName() + ", Made of: " + this.madeOf + ", Price: " + getPrice() + "€.";
    }

}
