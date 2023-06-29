package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AddProductController {

    Stage stage;
    Parent scene;

    private void returnToMainMenu(ActionEvent event) throws IOException {
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/IMS_Application/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    @FXML
    private TextField addProductIdTxt;

    @FXML
    private TextField addProductNameTxt;

    @FXML
    private TextField addProductInvTxt;

    @FXML
    private TextField addProductPriceTxt;

    @FXML
    private TextField addProductMaxTxt;

    @FXML
    private TextField addProductMinTxt;

    @FXML
    private TableColumn<?, ?> selectedParts_PartIdCol;

    @FXML
    private TableColumn<?, ?> selectedParts_PartNameCol;

    @FXML
    private TableColumn<?, ?> selectedParts_PartInvCol;

    @FXML
    private TableColumn<?, ?> selectedParts_PartPriceCol;

    @FXML
    private TableColumn<?, ?> addParts_PartIdCol;

    @FXML
    private TableColumn<?, ?> addParts_PartNameCol;

    @FXML
    private TableColumn<?, ?> addParts_PartInvCol;

    @FXML
    private TableColumn<?, ?> addParts_PartPriceCol;

    @FXML
    void onActionAddPartBtn(ActionEvent event) throws IOException {

        returnToMainMenu(event);

    }

    @FXML
    void onActionCancelBtn(ActionEvent event) throws IOException {

        returnToMainMenu(event);

    }

    @FXML
    void onActionRemovePartBtn(ActionEvent event) {

    }

    @FXML
    void onActionSaveBtn(ActionEvent event) {

    }

}