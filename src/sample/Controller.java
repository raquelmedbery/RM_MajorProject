/*The controller that creates the main order scene.*/

package sample;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Observable;

public class Controller {
    private static ObservableList<Customer> cusData = FXCollections.observableArrayList();
    private static ObservableList<Product> prodData = FXCollections.observableArrayList();
    private static ObservableList<Order> ordData = FXCollections.observableArrayList();
    private static ObservableList<String> firstNameData = FXCollections.observableArrayList();
    private static ObservableList<String> lastNameData = FXCollections.observableArrayList();
    private static ObservableList<String> prodNameData = FXCollections.observableArrayList();

    @FXML
    TextField entFName, entLName, entAddress, entCity, entState, entZip, entCell, entEmail;

    @FXML
    Button cusBtn, orderBtn, chooseFileBtnOne, submitFileBtn;

    @FXML
    TableView cusTable, ordTable, prodTable;

    @FXML
    ComboBox cusFCombo, cusLCombo, prodCombo;

    @FXML
    Label totalLbl;

    @FXML
    RadioButton rbUS, rbOut;

    @FXML
    ToggleGroup shippingToggle;

    @FXML
    CheckBox masterCB, visaCB, discoverCB, amexCB;

    @FXML
    Slider quanSlider;

    @FXML
    TabPane tabRoot;

    @FXML
    static ListView prodList;

    @FXML
    TableColumn tcOrdFName, tcOrdLName, tcProdName, tcQuantity, tcShip, tcPay, tcTotal;


    public static void fileChoose(File selectedFile) {
        //the chosen file is read into an arraylist of products.
        try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] products = line.split(" ");
                String id = products[0];
                String name = products[1];
                String strCost = products[2];
                double unitCost = Double.parseDouble(strCost);
                Product prod = new Product(id, name, unitCost);
                prodData.add(prod);
                prodNameData.add(name);
                System.out.println(id + "\n" + name + "\n" + strCost);
                System.out.println(line);
                System.out.println(prodData);
                String outStr = "Product Id: " + id + " Product Name: " + name + " Product Cost: " + unitCost;
                //prodList.getItems().addAll(prodData);
                //prodList.setItems(prodData);
                /*for(int i = 0; i<prodData.size(); i++){
                    Product newProd = (Product) prodData.get(i);
                    prodList.getItems().add(newProd);
                }*/

                //prodList.getItems().add(outStr);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void fileChooseTwo(File selectedFileTwo) {
        //the chosen file is read into an arraylist of customers
        try (BufferedReader reader = new BufferedReader(new FileReader(selectedFileTwo))) {

            String line;
            int ct =0;
            while ((line = reader.readLine()) != null) {
                String[] customers = line.split(" ");
                String fName = customers[0];
                String lName = customers[1];
                String homeNum = customers[2];
                String streetNum = customers[3];
                String streetAbr = customers[4];
                String cardinal = customers[5];
                String city = customers[6];
                String state = customers[7];
                String zip = customers[8];
                String cell = customers[9];
                String email = customers[10];
                String street = homeNum + " " + streetNum + " " + streetAbr + " " + cardinal;
                Customer cust = new Customer(fName, lName, street, city, state, zip, cell, email);
                cusData.add(cust);
                firstNameData.add(fName);
                lastNameData.add(lName);
                System.out.println(street + "\n" + fName + "\n" + cell);
                System.out.println(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static ObservableList<Customer> getCusData() {
        return cusData;
    }

    public static ObservableList<Order> getOrdData() {
        return ordData;
    }

    //figure out matches and patterns. Does this match this pattern?

    public void initialize(){
        //the creation of the tables and disabling buttons until info is entered
        cusBtn.setDefaultButton(true);
        rbUS.setUserData("United States");
        rbOut.setUserData("Outside States");

        cusFCombo.setItems(firstNameData);
        cusLCombo.setItems(lastNameData);
        prodCombo.setItems(prodNameData);

        //prodList.getItems.addAll(prodData);
        //prodList.setItems(prodData);

        TableColumn tcFName = new TableColumn("First Name");
        TableColumn tcLName = new TableColumn("Last Name");
        TableColumn tcAdd = new TableColumn("Address");
        TableColumn tcCity = new TableColumn("City");
        TableColumn tcState = new TableColumn("State");
        TableColumn tcZip = new TableColumn("Zip Code");
        TableColumn tcCell = new TableColumn("Cell Phone");
        TableColumn tcEmail = new TableColumn("Email");

        populateCusTable(tcFName, tcLName, tcAdd, tcCity, tcState, tcZip, tcCell, tcEmail);

        TableColumn tcId = new TableColumn("Product ID");
        TableColumn tcProd = new TableColumn("Product");
        TableColumn tcCost = new TableColumn("Product Cost");

        populateProdTable(tcId, tcProd, tcCost);

        tcOrdFName = new TableColumn("First Name");
        tcOrdLName = new TableColumn("Last Name");
        tcProdName = new TableColumn("Product");
        tcQuantity = new TableColumn("Quantity");
        tcShip = new TableColumn("Shipping");
        tcPay = new TableColumn("Payment Method");
        tcTotal = new TableColumn("Total");

        //populateOrdTable(tcOrdFName, tcOrdLName, tcProdName, tcQuantity, tcShip, tcPay, tcTotal);

        orderBtn.setDisable(true);
        cusBtn.setDisable(true);

        BooleanBinding bb = new BooleanBinding() {
            {
                super.bind(entFName.textProperty(),
                        entLName.textProperty(), entAddress.textProperty(), entCity.textProperty(),
                        entState.textProperty(), entZip.textProperty(), entCell.textProperty(),
                        entEmail.textProperty());
            }

            @Override
            protected boolean computeValue() {
                return (entFName.getText().isEmpty()
                        || entLName.getText().isEmpty() || entAddress.getText().isEmpty() || entCity.getText().isEmpty()||
                        entState.getText().isEmpty() || entZip.getText().isEmpty() || entCell.getText().isEmpty() ||
                        entEmail.getText().isEmpty());
            }
        };

        /*BooleanBinding bb2 = new BooleanBinding() {
            {
                super.bind((javafx.beans.Observable) cusFCombo.getValue(), (javafx.beans.Observable) cusLCombo.getValue(),
                        (javafx.beans.Observable) prodCombo.getValue(), (javafx.beans.Observable) shippingToggle.getSelectedToggle());
            }

            @Override
            protected boolean computeValue() {
                return !(!cusLCombo.getSelectionModel().isEmpty() && !cusFCombo.getSelectionModel().isEmpty() && !prodCombo.getSelectionModel().isEmpty() && (shippingToggle.getSelectedToggle() == null));
            }
        };*/

        shippingToggle.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                if(shippingToggle.getSelectedToggle() != null){
                    orderBtn.setDisable(false);
                }
            }
        });

        /*entCell.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d{3}-\\d{7}")) {
                    entCell.setText(oldValue);
                }
            }
        });

        /*if((!(cusLCombo.getSelectionModel().isEmpty()) && shippingToggle.getSelectedToggle() != null)){
            orderBtn.setDisable(false);
        }*/

        cusBtn.disableProperty().bind(bb);
        //orderBtn.disableProperty().bind(bb2);
    }

    private void populateProdTable(TableColumn tcId, TableColumn tcProd, TableColumn tcCost) {
        prodTable.setEditable(true);
        tcId.setCellValueFactory(new PropertyValueFactory<Product, String>("prodId"));
        tcProd.setCellValueFactory(new PropertyValueFactory<Product, String>("prodName"));
        tcCost.setCellValueFactory(new PropertyValueFactory<Product, String>("strCost"));
        prodTable.setItems(prodData);
        prodTable.getColumns().addAll(tcId, tcProd, tcCost);
    }

    private void populateOrdTable(TableColumn tcOrdFName, TableColumn tcOrdLName, TableColumn tcProdName, TableColumn tcQuantity, TableColumn tcShip, TableColumn tcPay, TableColumn tcTotal) {
        //populates the order table
        ordTable.setEditable(true);
        tcOrdFName.setCellValueFactory(new PropertyValueFactory<Order, String>("fName"));
        tcOrdLName.setCellValueFactory(new PropertyValueFactory<Order, String>("lName"));
        tcProdName.setCellValueFactory(new PropertyValueFactory<Order, String>("prodName"));
        tcQuantity.setCellValueFactory(new PropertyValueFactory<Order, String>("ordQuan"));
        tcShip.setCellValueFactory(new PropertyValueFactory<Order, String>("shipping"));
        tcPay.setCellValueFactory(new PropertyValueFactory<Order, String>("payMethod"));
        tcTotal.setCellValueFactory(new PropertyValueFactory<Order, String>("total"));
        ordTable.setItems(ordData);
        ordTable.getColumns().addAll(tcOrdFName, tcOrdLName, tcProdName, tcQuantity, tcShip, tcPay, tcTotal);

    }

    @FXML
    private void populateCusTable(TableColumn tcFName, TableColumn tcLName, TableColumn tcAdd, TableColumn tcCity, TableColumn tcState, TableColumn tcZip, TableColumn tcCell, TableColumn tcEmail) {
        //populates the customer table
        cusTable.setEditable(true);
        tcFName.setCellValueFactory(new PropertyValueFactory<Customer, String>("fName"));
        tcLName.setCellValueFactory(new PropertyValueFactory<Customer, String>("lName"));
        tcAdd.setCellValueFactory(new PropertyValueFactory<Customer, String>("address"));
        tcCity.setCellValueFactory(new PropertyValueFactory<Customer, String>("city"));
        tcState.setCellValueFactory(new PropertyValueFactory<Customer, String>("state"));
        tcZip.setCellValueFactory(new PropertyValueFactory<Customer, String>("zipCode"));
        tcCell.setCellValueFactory(new PropertyValueFactory<Customer, String>("phone"));
        tcEmail.setCellValueFactory(new PropertyValueFactory<Customer, String>("email"));
        cusTable.setItems(cusData);
        cusTable.getColumns().addAll(tcFName, tcLName, tcAdd, tcCity, tcState, tcZip, tcCell, tcEmail);
    }

    @FXML
    private void cusBtnClicked(ActionEvent event){
        //grabs the user info on the add customer screen and adds it to the arraylist of customers
        String fName = entFName.getText();
        String lName = entLName.getText();
        String add = entAddress.getText();
        String city = entCity.getText();
        String state = entState.getText();
        String zip = entZip.getText();
        String cell = entCell.getText();
        String email = entEmail.getText();

        Customer cus = new Customer(fName, lName, add, city, state, zip, cell, email);
        cusData.add(cus);
        firstNameData.add(fName);
        lastNameData.add(lName);

        entFName.clear();
        entLName.clear();
        entAddress.clear();
        entCity.clear();
        entState.clear();
        entZip.clear();
        entCell.clear();
        entEmail.clear();
        entFName.requestFocus();


        System.out.println(cusData);
        System.out.println(firstNameData);
    }

    @FXML
    private void ordBtnClicked(ActionEvent event){
        //grabs the info entered for orders and adds it to the order arraylist
        String shipping = shippingToggle.getSelectedToggle().getUserData().toString();
        String payment = "";
        if(amexCB.isSelected()){
            payment = payment + " American Express";
        }
        if(masterCB.isSelected()){
            payment = payment + " MasterCard";
        }
        if(visaCB.isSelected()){
            payment = payment + " Visa";
        }
        if(discoverCB.isSelected()){
            payment = payment + " Discover";
        }

        int quantity = (int) quanSlider.getValue();
        //totalLbl.setText(String.valueOf(quanSlider.getValue()));

        /*quanSlider.valueProperty().addListener(new ChangeListener<Number>(){
            public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val){
                totalLbl.setText(String.format("%.0f", new_val));
            }
        });*/

        System.out.println(payment);
        System.out.println(quantity);
        String fName = String.valueOf(cusFCombo.getValue());
        String lName = String.valueOf(cusLCombo.getValue());
        String product = String.valueOf(prodCombo.getValue());
        Order ord = new Order(fName, lName, product, quantity, shipping, payment);

        totalLbl.setText(ord.getTotal());
        populateOrdTable(tcOrdFName, tcOrdLName, tcProdName, tcQuantity, tcShip, tcPay, tcTotal);

        ordData.add(ord);
        System.out.println(ordData);
        System.out.println(fName + " " + lName);

        visaCB.setSelected(false);
        amexCB.setSelected(false);
        masterCB.setSelected(false);
        discoverCB.setSelected(false);
        rbOut.setSelected(false);
        rbUS.setSelected(false);
    }

}
