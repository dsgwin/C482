package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ModifyProductController implements Initializable {

    Stage stage;
    Parent scene;

    private ObservableList<Part> modifySelectedParts = FXCollections.observableArrayList();

    private void returnToMainMenu(ActionEvent event) throws IOException {
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/IMS_Application/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    private TextField modifyProductIdTxt;

    @FXML
    private TextField modifyProductNameTxt;

    @FXML
    private TextField modifyProductInvTxt;

    @FXML
    private TextField modifyProductPriceTxt;

    @FXML
    private TextField modifyProductMaxTxt;

    @FXML
    private TextField modifyProductMinTxt;

    @FXML
    private TextField partSearchTxt;

    @FXML
    private TableView<Part> addPartsTableView;

    @FXML
    private TableView<Part> selectedPartsTableView;

    @FXML
    private TableColumn<Part, Integer> selectedParts_PartIdCol;

    @FXML
    private TableColumn<Part, String> selectedParts_PartNameCol;

    @FXML
    private TableColumn<Part, Integer> selectedParts_PartInvCol;

    @FXML
    private TableColumn<Part, Double> selectedParts_PartPriceCol;

    @FXML
    private TableColumn<Part, Integer> addParts_PartIdCol;

    @FXML
    private TableColumn<Part, String> addParts_PartNameCol;

    @FXML
    private TableColumn<Part, Integer> addParts_PartInvCol;

    @FXML
    private TableColumn<Part, Double> addParts_PartPriceCol;

    @FXML
    void onActionAddPartBtn(ActionEvent event) {
            Part selectedPart = addPartsTableView.getSelectionModel().getSelectedItem();
            if (!(selectedPart == null)) {
                try {
                    modifySelectedParts.add(selectedPart);
                    selectedPartsTableView.refresh();

                } catch (Exception e) {
                    System.out.println("No part selected");
                }
            }

    }

    @FXML
    void onActionCancelBtn(ActionEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to cancel?\nAll values will be discarded.");

        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            returnToMainMenu(event);
        }

    }

    @FXML
    void onActionRemovePartBtn(ActionEvent event) {

        Part selectedPart = selectedPartsTableView.getSelectionModel().getSelectedItem();
        if (!(selectedPart == null)) {
            try {

                selectedPartsTableView.refresh();

            } catch (Exception e) {
                System.out.println("No part selected");
            }
        }
    }

    @FXML
    void onActionSaveBtn(ActionEvent event) throws IOException {

        returnToMainMenu(event);

    }

    @FXML
    void onSearchTextChanged(KeyEvent event) {

        String partName = partSearchTxt.getText();
        if (partName != null) {
            try {
                int partId = Integer.parseInt(partName);
                addPartsTableView.getSelectionModel().select(Inventory.lookupPart(partId));
            } catch (Exception e) {
                addPartsTableView.setItems(Inventory.lookupPart(partName));
            }
        } else {
            addPartsTableView.setItems(Inventory.getAllParts());
        }
    }

    public void sendProduct(Product product) {

        modifyProductIdTxt.setText(String.valueOf(product.getId()));
        modifyProductNameTxt.setText(product.getName());
        modifyProductPriceTxt.setText(String.valueOf(product.getPrice()));
        modifyProductInvTxt.setText(String.valueOf(product.getStock()));
        modifyProductMaxTxt.setText(String.valueOf(product.getMax()));
        modifyProductMinTxt.setText(String.valueOf(product.getMin()));
        addPartsTableView.setItems(Inventory.getAllParts());
        modifySelectedParts = product.getAllAssociatedParts();
        selectedPartsTableView.setItems(modifySelectedParts);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        addPartsTableView.setItems(Inventory.getAllParts());
        addParts_PartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        addParts_PartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        addParts_PartInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        addParts_PartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        modifySelectedParts.clear();
        selectedParts_PartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        selectedParts_PartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        selectedParts_PartInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        selectedParts_PartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

    }



}