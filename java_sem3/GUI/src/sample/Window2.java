package sample;

import Model.ADT.*;
import Model.PrgState;
import Model.Statement.IStmt;
import Model.Value.StringValue;
import Model.Value.Value;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import Controller.ControllerOld;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Window2 {
    private ControllerOld control;
    private int currentPrgIndex;
    private List<PrgState> list;
    @FXML Button runButton;
    @FXML TableView<Map.Entry<String, Value>> symTableView;
    @FXML TableColumn<Map.Entry<String, Value>, String> symCol1;
    @FXML TableColumn<Map.Entry<String, Value>, Value> symCol2;
    @FXML TableView<Map.Entry<Integer, Value>> heapTableView;
    @FXML TableColumn<Map.Entry<Integer, Value>, Integer> heapCol1;
    @FXML TableColumn<Map.Entry<Integer, Value>, Value> heapCol2;
    @FXML TableView<Map.Entry<Integer, Integer>> latchTableView;
    @FXML TableColumn<Map.Entry<Integer, Integer>, Integer> latchCol1;
    @FXML TableColumn<Map.Entry<Integer, Integer>, Integer> latchCol2;
    @FXML TextField psTextField;
    @FXML ListView<String> psListView;
    @FXML ListView<Value> outListView;
    @FXML ListView<String> stackListView;
    @FXML ListView fileListView;
    void setController(ControllerOld ctrl){
        control=ctrl;
    }
    @FXML
    public void initialize(){
        symCol1.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getKey() + ""));
        symCol2.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getValue()));

        heapCol1.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().getKey()).asObject());
        heapCol2.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getValue()));

        latchCol1.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().getKey()).asObject());
        latchCol2.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().getValue()).asObject());

        currentPrgIndex=0;
        psListView.setOnMouseClicked(e->prgStateSelected());
        runButton.setOnAction(e->btnPressed());
    }
    private void btnPressed() {
        //currentPrgIndex=psListView.getSelectionModel().getSelectedIndex();
        //List<PrgState> list=control.getPrgList();
        //garbage
        //PrgState curent=list.get(currentPrgIndex);
        //log maybe
        /*if(curent.getStk().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Nothing left to execute", ButtonType.OK);
            alert.showAndWait();
            return;
        }*/
        try { list=control.executeOneStep(); }
        catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());

            alert.showAndWait();
        }
        loadPrgStateInfo();
        populatepsListView();
        refreshpsTextField(list.size());
        populatepsListView();
    }
    private void prgStateSelected(){
        currentPrgIndex=psListView.getSelectionModel().getSelectedIndex();
        //List<PrgState> list=control.getPrgList();
        if(currentPrgIndex>=0 && currentPrgIndex<list.size()){
            loadPrgStateInfo();
        }
        //        else{
        //            Alert alert = new Alert(Alert.AlertType.WARNING);
        //            alert.setTitle("Error");
        //            alert.setHeaderText(null);
        //            alert.setContentText("Please click on the f#cking program");
        //
        //            alert.showAndWait();
        //        }
    }
    private void loadPrgStateInfo(){
        if(list.size()==0){
            stackListView.getItems().clear();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Info");
            alert.setHeaderText(null);
            alert.setContentText("The Program Finished");

            alert.showAndWait();
            return;
        }
        if(currentPrgIndex>=0 && currentPrgIndex<list.size()){
        PrgState curent=list.get(currentPrgIndex);//maybe check if index is ok
        //System.out.println(currentPrgIndex+" and the size is "+list.size());
        populateSym(curent);
        populateOutput(curent);
        populateExecutionStack(curent);
        populateFileTable(curent);
        populateHeap(curent);
        populateLatch(curent);
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Wrong Program Selected");

            alert.showAndWait();
        }
    }
    private void populateFileTable(PrgState prg) {
        MyIDictionary<StringValue, BufferedReader> fileTable = prg.getFileTable();
        Map<StringValue, BufferedReader> fileTableMap = fileTable.getContent();
        //for (Map.Entry<Integer, MyFilePair> entry : fileTable.getContent())
         //   fileTableMap.put(entry.getKey(), entry.getValue().getFileName());

        List<Map.Entry<StringValue, BufferedReader>> fileTableList = new ArrayList<>(fileTableMap.entrySet());
        fileListView.setItems(FXCollections.observableList(fileTableList));
        fileListView.refresh();
    }
    private void populateExecutionStack(PrgState prg) {
        MyIStack<IStmt> executionStack = prg.getStk();

        List<String> executionStackList = new ArrayList<>();
        for(IStmt s : executionStack.getContent()){
            executionStackList.add(s.toString());
        }

        stackListView.setItems(FXCollections.observableList(executionStackList));
        stackListView.refresh();
    }
    private void populateOutput(PrgState prg) {
        MyIList<Value> output = prg.getOut();
        outListView.setItems(FXCollections.observableList(output.getContent()));
        outListView.refresh();
    }

    public void loadInfo(){ ///!aici e inceputul si in initialize
        list=control.getPrgList();
        refreshpsTextField(list.size());
        populatepsListView();
    }
    private void populatepsListView(){
        psListView.getItems().clear();
        for(PrgState i:list)
            psListView.getItems().add("PrgState"+i.getId());
    }
    private void populateSym(PrgState prg) {
        MyIDictionary<String, Value> sym = prg.getSymTable();
        List<Map.Entry<String, Value>> symbolTableListed = new ArrayList<>(sym.getContent().entrySet());
        //System.out.println(symbolTableListed);
        symTableView.setItems(FXCollections.observableList(symbolTableListed));
        symTableView.refresh();
        //Map<String,Integer> m=new HashMap<>();
        //m.put("ana",3);
        //m.put("marcel",45);
        //List<Map.Entry<String,Integer>> st=new ArrayList<>(m.entrySet());
    }
    private void populateHeap(PrgState prg) {
        MyIHeap<Integer, Value> heap = prg.getHeapTable();
        List<Map.Entry<Integer, Value>> heapTbl = new ArrayList<>(heap.getContent().entrySet());
        //System.out.println(heapTbl);
        heapTableView.setItems(FXCollections.observableList(heapTbl));
        heapTableView.refresh();
        //Map<String,Integer> m=new HashMap<>();
        //m.put("ana",3);
        //m.put("marcel",45);
        //List<Map.Entry<String,Integer>> st=new ArrayList<>(m.entrySet());
    }
    private void populateLatch(PrgState prg) {
        MyILatchTable lt=prg.getLatchTable();
        List<Map.Entry<Integer, Integer>> ltb = new ArrayList<>(lt.getContent().entrySet());
        //System.out.println(heapTbl);
        latchTableView.setItems(FXCollections.observableList(ltb));
        latchTableView.refresh();
        //Map<String,Integer> m=new HashMap<>();
        //m.put("ana",3);
        //m.put("marcel",45);
        //List<Map.Entry<String,Integer>> st=new ArrayList<>(m.entrySet());
    }
    private void refreshpsTextField(int howMany){
        psTextField.setText("Number of PrgStates is: "+howMany);
    }
}
