package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;

public class AddPartController {

    Stage stage;
    Parent scene;



    @FXML
    private Label machineId_companyName_Toggle;

    @FXML
    private ToggleGroup addPartToggle;


    @FXML
    void onActionCancelBtn(ActionEvent event) throws IOException {

        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/IMS_Application/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onActionIdTxt(ActionEvent event) {

    }

    @FXML
    void onActionInvTxt(ActionEvent event) {

    }

    @FXML
    void onActionMaxTxt(ActionEvent event) {

    }

    @FXML
    void onActionMinTxt(ActionEvent event) {

    }

    @FXML
    void onActionNameTxt(ActionEvent event) {

    }

    @FXML
    void onActionPriceTxt(ActionEvent event) {

    }

    @FXML
    void onActionSaveBtn(ActionEvent event) {

    }

    @FXML
    void onAction_MachineID_CompanyNameTxt(ActionEvent event) {

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