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

public class ModifyPartController {

    Stage stage;
    Parent scene;

    @FXML
    private Label machineId_companyName_Toggle;

    @FXML
    private ToggleGroup addPartToggle;


    @FXML
    void onActionModifyPartCancelBtn(ActionEvent event) throws IOException {

        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/IMS_Application/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onActionModifyPartId(ActionEvent event) {

    }

    @FXML
    void onActionModifyPartInv(ActionEvent event) {

    }

    @FXML
    void onActionModifyPartMax(ActionEvent event) {

    }

    @FXML
    void onActionModifyPartMin(ActionEvent event) {

    }

    @FXML
    void onActionModifyPartName(ActionEvent event) {

    }

    @FXML
    void onActionModifyPartPrice(ActionEvent event) {

    }

    @FXML
    void onActionModifyPartSaveBtn(ActionEvent event) {

    }

    @FXML
    void onActionModifyPart_mId_cName(ActionEvent event) {

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