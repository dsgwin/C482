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
import model.*;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * @author
 * Duncan Gwin
 * dgwin4@wgu.edu
 * 008698673
 */

/**
 * This class is the controller for the "Add Product" menu of the application.
 */
public class AddProductController implements Initializable {

    Stage stage;
    Parent scene;
    private ObservableList<Part> selectedParts = FXCollections.observableArrayList();

    /**
     * Method to return to the main menu screen.
     * @param event
     * @throws IOException if I/O error occurs.
     */
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
    private TextField partSearchTxt;

    /**
     * Adds product to inventory and returns user to main menu screen.
     * @param event
     * @throws IOException
     */
    @FXML
    void onActionAddPartBtn(ActionEvent event) throws IOException {
        Part selectedPart = addPartsTableView.getSelectionModel().getSelectedItem();
        if (!(selectedPart == null)) {
            try {
                selectedParts.add(selectedPart);
                selectedPartsTableView.refresh();

            } catch (Exception e) {
                System.out.println("No part selected");
            }
        }
    }

    /**
     * Returns user to the main menu if the cancel button is pressed.
     * Confirmation prompt will appear on screen.
     * @param event
     * @throws IOException if I/O error occurs.
     */
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
                selectedParts.remove(selectedPart);
                selectedPartsTableView.refresh();

            } catch (Exception e) {
                System.out.println("No part selected");
            }
        }
    }

    /**
     * Creates the product from input form and returns user to the main menu.
     * @param event
     * @throws IOException if I/O error occurs.
     */
    @FXML
    void onActionSaveBtn(ActionEvent event) throws IOException {

        String alertText = null;

        try {

            alertText = Inventory.formInputCheck(addProductInvTxt.getText(),addProductPriceTxt.getText(),
                    addProductMinTxt.getText(),addProductMaxTxt.getText());


            int id = Inventory.getNextProductId();
            String name = addProductNameTxt.getText();
            int stock = Integer.parseInt(addProductInvTxt.getText());
            double price = Double.parseDouble(addProductPriceTxt.getText());
            int max = Integer.parseInt(addProductMaxTxt.getText());
            int min = Integer.parseInt(addProductMinTxt.getText());
            ObservableList associatedParts = selectedParts;

            if((Inventory.minMaxCheck(min, max)) && (Inventory.inventoryCheck(min, max, stock))) {
                Inventory.addProduct(new Product(associatedParts, id, name, price, stock, min, max));

                returnToMainMenu(event);
            }
            else {Inventory.nextProductId--;}
        }
        catch(NumberFormatException e){

            Inventory.nextProductId--;
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Adding Product");
            alert.setContentText(alertText);
            alert.showAndWait();

        }

    }

    /**
     * Facilitates the search functionality.
     * @param event
     */
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

    /**
     * Initializes scene and loads data into view.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        addPartsTableView.setItems(Inventory.getAllParts());
        addParts_PartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        addParts_PartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        addParts_PartInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        addParts_PartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));


        selectedPartsTableView.setItems(selectedParts);
        selectedParts_PartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        selectedParts_PartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        selectedParts_PartInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        selectedParts_PartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

}