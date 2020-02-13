/*Description: The controller for the second file chosen that contains the customers*/

package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class FileChooseTwo {
    @FXML
    Button submitFileBtnTwo, chooseFileBtnTwo;

    @FXML
    private ListView fileListTwo;

    private File selectedFileTwo;

    @FXML
    private void fileChooseTwo(ActionEvent event) throws IOException {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt")
        );
        fc.setInitialDirectory(new File(System.getProperty("user.home")));

        selectedFileTwo = fc.showOpenDialog(null);

        if(selectedFileTwo != null){
            fileListTwo.getItems().add(selectedFileTwo.getName());
            submitFileBtnTwo.setDisable(false);
        }else{
            System.out.println("File is not valid");
        }
    }

    @FXML
    private void getOrderScene(ActionEvent e) throws IOException{
        Stage stage;
        Parent root;
        if (selectedFileTwo != null){
            Controller.fileChooseTwo(selectedFileTwo);
            submitFileBtnTwo.setDisable(false);
        }
        stage = (Stage) submitFileBtnTwo.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
