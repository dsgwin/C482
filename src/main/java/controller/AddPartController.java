package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javafx.scene.control.RadioButton;
import model.InHouse;
import model.Inventory;
import model.Outsourced;

import java.io.IOException;
import java.util.EventObject;

public class AddPartController {

    Stage stage;
    Parent scene;
    
    private void returnToMainMenu(ActionEvent event) throws IOException {
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/IMS_Application/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    private RadioButton inHouseRBtn;
    @FXML
    private RadioButton outsourcedRBtn;

    @FXML
    private Label machineId_companyName_Toggle;

    @FXML
    private TextField partIdTxt;

    @FXML
    private TextField partNameTxt;

    @FXML
    private TextField partInvTxt;

    @FXML
    private TextField partPriceTxt;

    @FXML
    private TextField partMaxTxt;

    @FXML
    private TextField partMachine_CompanyTxt;

    @FXML
    private TextField partMinTxt;


    @FXML
    private ToggleGroup addPartToggle;


    @FXML
    void onActionCancelBtn(ActionEvent event) throws IOException {

        returnToMainMenu(event);

    }

    @FXML
    void onActionSaveBtn(ActionEvent event) throws IOException {

        int id = Integer.parseInt(partIdTxt.getText());
        String name = partNameTxt.getText();
        int stock = Integer.parseInt(partInvTxt.getText());
        double price = Double.parseDouble(partPriceTxt.getText());
        int max = Integer.parseInt(partMaxTxt.getText());
        int min = Integer.parseInt(partMinTxt.getText());
        int machineId = -1;
        String companyName = null;
        
        if (inHouseRBtn.isSelected()){
            machineId = Integer.parseInt(partMachine_CompanyTxt.getText());
        }else{
            companyName = partMachine_CompanyTxt.getText();
        }

        if (inHouseRBtn.isSelected()){
            InHouse part = new InHouse(id,name,price,stock,min,max,machineId);
            Inventory.addPart(part);
        }else{
            Outsourced part = new Outsourced(id,name,price,stock,min,max,companyName);
            Inventory.addPart(part);
        }

        returnToMainMenu(event);

    }


    @FXML
    void inHouseSelected(ActionEvent event) {

        machineId_companyName_Toggle.setText("Machine ID");

    }

    @FXML
    void outsourcedSelected(ActionEvent event) {

        machineId_companyName_Toggle.setText("Company Name");

    }

}