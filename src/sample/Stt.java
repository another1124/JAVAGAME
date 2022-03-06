package sample;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
//...
public class Stt implements Initializable {
    public Button start;
    public Button end;
    private Parent sn= null;
    private Scene scene=null;
    private Stage stage=null;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        start.setOnAction(e->
        {
            try {
                sn=FXMLLoader.load(getClass().getResource("sample.fxml"));
                scene=new Scene(sn);
                stage=(Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("test");
                stage.show();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }
}
