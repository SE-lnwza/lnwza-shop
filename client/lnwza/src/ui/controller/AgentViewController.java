package ui.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import application.entity.Agent;
import application.handler.UserHandler;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author SE-lnwza
 */
public class AgentViewController {

    @FXML
    private TableView<Agent> tableview;

    @FXML
    private TableColumn<Agent, Long> tb_id;

    @FXML
    private TableColumn<Agent, String> tb_first;

    @FXML
    private TableColumn<Agent, String> tb_last;

    @FXML
    private TableColumn<Agent, String> tb_address;

    @FXML
    private TableColumn<Agent, String> tb_tel;
    
    @FXML
    protected void initialize() {
        tb_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        tb_first.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tb_last.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tb_address.setCellValueFactory(value -> {
            return new SimpleStringProperty(value.getValue().getAddress() + " " + value.getValue().getPostCode());
        });
        tb_tel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        
//        AgentHandler.load();
        ObservableList<Agent> data = FXCollections.observableArrayList(UserHandler.getAgents());
        tableview.setItems(data);
    }
    
    public void hello() {
        System.out.println("Hello world!");
    }

}