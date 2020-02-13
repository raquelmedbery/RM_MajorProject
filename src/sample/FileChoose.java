/*Description: The controller for the first file chosen that contains the products*/

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

public class FileChoose {
    @FXML
    Button chooseFileBtnOne;

    @FXML
    Button submitFileBtn;

    @FXML
    private ListView fileList;

    private File selectedFile;

    @FXML
    private void fileChooseOne(ActionEvent event) throws IOException {
        FileChooser fc = new FileChooser();
        selectedFile = fc.showOpenDialog(null);

        if(selectedFile != null){
            fileList.getItems().add(selectedFile.getName());
            submitFileBtn.setDisable(false);
        }else{
            System.out.println("File is not valid");
        }
    }

    @FXML
    private void getNewScene(ActionEvent e) throws IOException{
        Stage stage;
        Parent root;
        if (selectedFile != null){
            Controller.fileChoose(selectedFile);
            submitFileBtn.setDisable(false);
        }
        stage = (Stage) submitFileBtn.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("fileChooseTwo.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
