package sample;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.text.DecimalFormat;

public class Product {
    private final SimpleStringProperty prodId;
    private final SimpleStringProperty prodName;
    private final SimpleDoubleProperty unitCost;
    private double doubCost;
    private String strCost;

    public Product(String tmpProdId, String tmpProdName, double tmpUnitCost){
        this.prodId = new SimpleStringProperty(tmpProdId);
        this.prodName = new SimpleStringProperty(tmpProdName);
        this.unitCost = new SimpleDoubleProperty(tmpUnitCost);

        doubCost = tmpUnitCost;
    }

    public String getProdId() {
        return prodId.get();
    }

    public String getProdName() {
        return prodName.get();
    }

    public double getUnitCost() {
        return unitCost.get();
    }

    public String getStrCost(){
        String pattern = "$###,###.00";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        String strCost= decimalFormat.format(doubCost);
        return strCost;
    }

    public String toString(){
        return ("Product Number: " + getProdId() + "\n" + "Product Name: " + getProdName()
        + "\n" + "Unit Cost: " + getUnitCost() + "\n");
    }
}
