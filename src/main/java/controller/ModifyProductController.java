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
 * This class is the controller for the "Modify Product" menu of the application.
 */
public class ModifyProductController implements Initializable {

    Stage stage;
    Parent scene;

    private int index;

    private ObservableList<Part> modifySelectedParts = FXCollections.observableArrayList();

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

    /**
     * Adds part from available parts list to associatd parts list.
     * @param event
     */
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

    /**
     * Removes associated part from table.
     * @param event
     */
    @FXML
    void onActionRemovePartBtn(ActionEvent event) {

        Part selectedPart = selectedPartsTableView.getSelectionModel().getSelectedItem();
            if (selectedPart != null) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to remove " + selectedPart.getName() + " from the list?");

                Optional<ButtonType> result = alert.showAndWait();
                if(result.isPresent() && result.get() == ButtonType.OK) {
                    modifySelectedParts.remove(selectedPart);
                    selectedPartsTableView.refresh();
                }
                ;

            }
            else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("No part selected");
                    alert.setContentText("Please select a part from the list to remove");
                    alert.showAndWait();
                }



    }

    /**
     * Saves the modified product and returns user to the main menu.
     * @param event
     * @throws IOException if I/O error occurs.
     */
    @FXML
    void onActionSaveBtn(ActionEvent event) throws IOException {

        String alertText = null;

        try {
            alertText = Inventory.formInputCheck(modifyProductInvTxt.getText(),modifyProductPriceTxt.getText(),
                    modifyProductMinTxt.getText(),modifyProductMaxTxt.getText());


            int id = Integer.parseInt(modifyProductIdTxt.getText());
            int stock = Integer.parseInt(modifyProductInvTxt.getText());
            double price = Double.parseDouble(modifyProductPriceTxt.getText());
            int max = Integer.parseInt(modifyProductMaxTxt.getText());
            int min = Integer.parseInt(modifyProductMinTxt.getText());
            String name = modifyProductNameTxt.getText();
            ObservableList associatedParts = modifySelectedParts;

            if((Inventory.minMaxCheck(min, max)) && (Inventory.inventoryCheck(min, max, stock))) {

                Product newProduct = new Product(associatedParts, id, name, price, stock, min, max);

                Inventory.updateProduct(index, newProduct);

                returnToMainMenu(event);
            }

        }
        catch(NumberFormatException e){


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
     * Sends product fields from controller to another.
     * @param productIndex product index to be sent.
     * @param product product object to be sent.
     */
    public void sendProduct(int productIndex, Product product) {

        setIndex(productIndex);

        modifyProductIdTxt.setText(String.valueOf(product.getId()));
        modifyProductNameTxt.setText(product.getName());
        modifyProductPriceTxt.setText(String.valueOf(product.getPrice()));
        modifyProductInvTxt.setText(String.valueOf(product.getStock()));
        modifyProductMaxTxt.setText(String.valueOf(product.getMax()));
        modifyProductMinTxt.setText(String.valueOf(product.getMin()));
        addPartsTableView.setItems(Inventory.getAllParts());
        modifySelectedParts.addAll(product.getAllAssociatedParts());
        selectedPartsTableView.setItems(modifySelectedParts);
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


        selectedParts_PartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        selectedParts_PartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        selectedParts_PartInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        selectedParts_PartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

    }

    /**
     * Method to set product index.
     * @param productIndex product index to be set.
     */
    private void setIndex(int productIndex){
        index = productIndex;

    }



}