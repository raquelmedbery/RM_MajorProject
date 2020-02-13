package sample;

import javafx.beans.property.SimpleStringProperty;

public class Customer {
    private final SimpleStringProperty fName;
    private final SimpleStringProperty lName;
    private final SimpleStringProperty address;
    private final SimpleStringProperty city;
    private final SimpleStringProperty state;
    private final SimpleStringProperty zipCode;
    private final SimpleStringProperty phone;
    private final SimpleStringProperty email;

    public Customer(String tmpFName, String tmpLName, String tmpAdd, String tmpCity, String tmpState, String tmpZip, String tmpPhone, String tmpEmail){
        this.fName = new SimpleStringProperty(tmpFName);
        this.lName = new SimpleStringProperty(tmpLName);
        this.address = new SimpleStringProperty(tmpAdd);
        this.city = new SimpleStringProperty(tmpCity);
        this.state = new SimpleStringProperty(tmpState);
        this.zipCode = new SimpleStringProperty(tmpZip);
        this.phone = new SimpleStringProperty(tmpPhone);
        this.email = new SimpleStringProperty(tmpEmail);
    }

    public String getFName() {
        return fName.get();
    }

    public void setFName(String tmpFName){
        fName.set(tmpFName);
    }

    public String getLName() {
        return lName.get();
    }

    public void setLName(String tmpLName){
        fName.set(tmpLName);
    }

    public String getAddress() {
        return address.get();
    }

    public void setAddress(String tmpAdd){
        fName.set(tmpAdd);
    }

    public String getCity() {
        return city.get();
    }

    public void setCity(String tmpCity){
        fName.set(tmpCity);
    }

    public String getState() {
        return state.get();
    }

    public void setState(String tmpState){
        fName.set(tmpState);
    }

    public String getZipCode() {
        return zipCode.get();
    }

    public void setZipCode(String tmpZipCode){
        fName.set(tmpZipCode);
    }

    public String getPhone() {
        return phone.get();
    }

    public void setPhone(String tmpPhone){
        fName.set(tmpPhone);
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String tmpEmail){
        fName.set(tmpEmail);
    }

    public String toString(){
        return ("First Name: " + getFName() + "\n" + "Last Name: " + getLName() + "\n" + "Address: " + getAddress() +
                "\n" + "City: " + getCity() + "\n" + "State: " + getState() + "\n" + "Zip Code: " + getZipCode() + "\n"
                + "Phone: " + getPhone() + "\n" + "Email: " + getEmail() + "\n");
    }
}
