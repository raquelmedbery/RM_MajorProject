/* Name: Raquel Medbery
   Date: 10/15/2018
   Description: The user grabs a file of customers and products. The files they grab
   are used in the program. The user can add more customers to the program through
   the customer creation screen. There is an order screen where the user chooses a
   customer and product from the existing choices and submits a new order. With every new
   order and new customer the tables depicting this information will update. There is also
   a table listing all the products. Once the user ends the program they have the option to save
   the existing customers and orders to another file.
*/

package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;

public class Main extends Application {
    private static ObservableList<Customer> cusData = FXCollections.observableArrayList();
    private static ObservableList<Order> ordData = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("splashScreen.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.show();

        primaryStage.getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, this::closeWindowEvent);
    }

    private void closeWindowEvent(WindowEvent event) {
        System.out.println("Window close request ...");

        /*if(storageModel.dataSetChanged()) {  // if the dataset has changed, alert the user with a popup
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.getButtonTypes().remove(ButtonType.OK);
            alert.getButtonTypes().add(ButtonType.CANCEL);
            alert.getButtonTypes().add(ButtonType.YES);
            alert.setTitle("Quit application");
            alert.setContentText(String.format("Close without saving?"));
            //alert.initOwner(primaryStage.getOwner());
            Optional<ButtonType> res = alert.showAndWait();

            if(res.isPresent()) {
                if(res.get().equals(ButtonType.CANCEL))
                    event.consume();
            }
        }*/
    }

    public void stop(){
        System.out.println("Stage is closing");
        cusData = Controller.getCusData();
        ordData = Controller.getOrdData();
        FileChooser fc = new FileChooser();
        /*Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Save Customer");
        alert.setContentText(String.format("Save your customer information"));*/

        File selectedFile = fc.showSaveDialog(null);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(selectedFile, true))) {

            for(int i = 0; i<cusData.size(); i++){
                Customer cus = cusData.get(i);
                String cusLine = cus.getFName() + " " + cus.getLName() + " " + cus.getAddress() + " " + cus.getCity() + " " + cus.getState() + " " + cus.getZipCode() + " "
                        + cus.getPhone() + " " + cus.getEmail();
                bw.write(cusLine);
                bw.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();

        }

        File ordFile = fc.showSaveDialog(null);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ordFile, true))) {

            for(int i = 0; i<ordData.size(); i++){
                Order ord = ordData.get(i);

                bw.write(ord.getFName());
                bw.newLine();
                bw.write(ord.getLName());
                bw.newLine();
                bw.write(ord.getProdName());
                bw.newLine();
                bw.write(ord.getStrQuan());
                bw.newLine();
                bw.write(ord.getShipping());
                bw.newLine();
                bw.write(ord.getPayMethod());
                bw.newLine();
                bw.write(ord.getTotal());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();

        }
        //System.out.println(cusData);
        // Save file
    }


    public static void main(String[] args) {
        launch(args);
    }
}
