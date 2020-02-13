package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SplashController implements Initializable {
    @FXML
    private StackPane pane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new SplashScreen().start();
    }

    class SplashScreen extends Thread{
        @Override
        public void run(){
            try{
                Thread.sleep(2000);

                Platform.runLater(new Runnable(){
                    @Override
                    public void run(){
                        Parent root = null;
                        try {
                            root = FXMLLoader.load(getClass().getResource("fileChoose.fxml"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        //primaryStage.setTitle("Hello World");
                        Stage primaryStage = new Stage();
                        primaryStage.setTitle("Order Stuff");
                        primaryStage.setScene(new Scene(root, 500, 500));
                        primaryStage.show();

                        pane.getScene().getWindow().hide();
                    }
                });
                //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
                //primaryStage.setTitle("Hello World");
                //Stage primaryStage = new Stage();
                //primaryStage.setScene(new Scene(root, 500, 500));
                //primaryStage.show();

                //pane.getScene().getWindow().hide();

            }catch(InterruptedException ex){
                Logger.getLogger(SplashController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
