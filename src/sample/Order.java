package sample;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class Order {
    enum ProductCost{
        CANDLES(12.99),
        BAG (60.99),
        SHIRT (100.00);

        private final double productCost;

        ProductCost(double productCost) {
            this.productCost = productCost;
        }

        public double getProductCost() {
            return this.productCost;
        }
    }

    private final SimpleStringProperty fName;
    private final SimpleStringProperty lName;
    private final SimpleStringProperty prodName;
    private final SimpleIntegerProperty ordQuan;
    private final SimpleStringProperty shipping;
    private final SimpleStringProperty payMethod;
    private BigDecimal total;
    private double prodCost;
    private String strTotal;
    private String strQuan;

    public Order(String tmpFName, String tmpLName, String tmpProdName, int tmpOrdQuan, String tmpShipping, String tmpPay){
        this.fName = new SimpleStringProperty(tmpFName);
        this.lName = new SimpleStringProperty(tmpLName);
        this.prodName = new SimpleStringProperty(tmpProdName);
        this.ordQuan = new SimpleIntegerProperty(tmpOrdQuan);
        this.shipping = new SimpleStringProperty(tmpShipping);
        this.payMethod = new SimpleStringProperty(tmpPay);
        
        if (getProdName().equals("Candles")){
            ProductCost prod = ProductCost.CANDLES;
            prodCost = prod.getProductCost();
        }

        if (getProdName().equals("Bag")){
            ProductCost prod = ProductCost.BAG;
            prodCost = prod.getProductCost();
        }
        
        if (getProdName().equals("Shirt")){
            ProductCost prod = ProductCost.SHIRT;
            prodCost = prod.getProductCost();
        }
        
        strQuan = String.valueOf(tmpOrdQuan);
        total = calculateTotal(tmpOrdQuan, prodCost);
    }

    public String getFName() {
        return fName.get();
    }

    public String getLName() {
        return lName.get();
    }

    public String getProdName() {
        return prodName.get();
    }

    public int getOrdQuan() {
        return ordQuan.get();
    }

    public String getStrQuan(){
        return strQuan;
    }

    public String getShipping() {
        return shipping.get();
    }

    public String getPayMethod() {
        return payMethod.get();
    }

    public String getTotal() {
        String pattern = "$###,###.00";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        String strTotal= decimalFormat.format(total);
        return strTotal;
    }

    public double getProdCost() {
        return prodCost;
    }

    public String toString(){
        return("First Name: " + getFName() + "\n" + "Last Name: " + getLName() + "\n" +
                "Product Name: " + getProdName() + "\n" + "Order Quantity: " + getOrdQuan() + "\n"
        + "Shipping: " + getShipping() + "\n" + "Payment Method: " + getPayMethod() + "\n");
    }

    private BigDecimal calculateTotal(int ordQuan, double prodCost) {
        BigDecimal bigQuan = new BigDecimal(ordQuan);
        BigDecimal bigCost = new BigDecimal(prodCost);
        total = bigQuan.multiply(bigCost);
        return total;
    }
}
