package sample;

import View.Command;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Controller {
    private Map<String, Command> commands;
    @FXML private Button btn;
    @FXML private ListView<String> list;
    private Stage mainStage;

    @FXML
    public void initialize(){
        commands=new HashMap<>();
        btn.setText("Run");
        list.setOnMouseClicked(e->listSelection());
        btn.setOnAction(e->btnPressed());
        /*list.getItems().clear();
        btn.setText("marcu");
        list.getItems().add("ion");
        list.getItems().add("ion2");
        list.getItems().add("ion3");
        list.getItems().add("ion4");
        list.setOnMouseClicked(e->listSelection());

        btn.setOnAction(e->btnPressed());*/
        //btn.setOnMouseClicked(e->btnPressed());
    }
    void addCommand(Command c){ commands.put(c.getKey(),c);}
    void show(){
        list.getItems().clear();
        for(Command com : commands.values()){
            String line=String.format("%4s : %s", com.getKey(), com.getDescription());
            list.getItems().add(line);
        }
    }
    public void btnPressed() {
        int index=list.getSelectionModel().getSelectedIndex();
        if(index>=0){
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("window2.fxml"));
                GridPane gp = fxmlLoader.load();
                Window2 controller = fxmlLoader.getController();
                //controller.setProgramIndex(index);
                Command com=commands.get(String.valueOf(index));
                controller.setController(com.getCtrl());
                //loadstmt
                controller.loadInfo();
                Scene scena = new Scene(gp);
                Stage newStage = new Stage();
                newStage.setScene(scena);
                newStage.setTitle("Titlu" + index);

                //newStage.initModality(Modality.WINDOW_MODAL);
                //newStage.initOwner(mainStage);
                newStage.show();
            } catch (Exception e) {
                System.out.println("eroare here");
                System.out.println(e.getMessage());
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("No program selected to run.");

            alert.showAndWait();
        }
    }
    public void listSelection(){
        int index=list.getSelectionModel().getSelectedIndex();
        //System.out.println(index);
        //Alert alert = new Alert(Alert.AlertType.INFORMATION);
        //alert.setTitle("Info");
        //alert.setHeaderText(null);
        Command com=commands.get(String.valueOf(index));
        String line=String.format("%4s : %s", com.getKey(), com.getDescription());
        System.out.println(line);
        //alert.setContentText(line);

        //alert.showAndWait();
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }
}
