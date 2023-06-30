package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import model.InHouse;
import model.Outsourced;
import model.Part;

import java.io.IOException;

public class ModifyPartController {

    Stage stage;
    Parent scene;

    private void returnToMainMenu(ActionEvent event) throws IOException {
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/IMS_Application/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

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
    private RadioButton inHouseRBtn;

    @FXML
    private RadioButton outsourceRBtn;


    @FXML
    private ToggleGroup addPartToggle;


    @FXML
    void onActionModifyPartCancelBtn(ActionEvent event) throws IOException {

        returnToMainMenu(event);

    }

    @FXML
    void onActionModifyPartSaveBtn(ActionEvent event) throws IOException {

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

    public void sendPart(Part part){
        partIdTxt.setText(String.valueOf(part.getId()));
        partNameTxt.setText(part.getName());
        partPriceTxt.setText(String.valueOf(part.getPrice()));
        partInvTxt.setText(String.valueOf(part.getStock()));
        partMaxTxt.setText(String.valueOf(part.getMax()));
        partMinTxt.setText(String.valueOf(part.getMin()));

        if(part instanceof InHouse){
            inHouseRBtn.setSelected(true);
            machineId_companyName_Toggle.setText("Machine ID");
            partMachine_CompanyTxt.setText(String.valueOf(((InHouse) part).getMachineId()));
        }
        else {
            outsourceRBtn.setSelected(true);
            machineId_companyName_Toggle.setText("Company Name");
            partMachine_CompanyTxt.setText(((Outsourced) part).getCompanyName());
        }



    }



}