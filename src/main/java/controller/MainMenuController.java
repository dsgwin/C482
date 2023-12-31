package controller;

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

/**
 * @author
 * Duncan Gwin
 * dgwin4@wgu.edu
 * 008698673
 */

/**
 * The controller for the Main Menu screen.
 */
public class MainMenuController implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    private TableView<Part> partsTableView;

    @FXML
    private TableColumn<Part, Integer> partIdCol;

    @FXML
    private TableColumn<Part, Integer> partInvLevelCol;

    @FXML
    private TableColumn<Part, String> partNameCol;

    @FXML
    private TableColumn<Part, Double> partPriceCol;

    @FXML
    private TextField partSearchTxt;

    @FXML
    private TableView<Product> productsTableView;

    @FXML
    private TableColumn<Product, Double> productCostCol;

    @FXML
    private TableColumn<Product, Integer> productIdCol;

    @FXML
    private TableColumn<Product, Integer> productInvLevelCol;

    @FXML
    private TableColumn<Product, String> productNameCol;

    @FXML
    private TextField productSearchTxt;

    /**
     * Loads Add Part controller.
     * @param event
     * @throws IOException
     */
    @FXML
    void onActionAddPart(ActionEvent event) throws IOException {

        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/IMS_Application/AddPart.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /**
     * Loads add product controller.
     * @param event
     * @throws IOException
     */
    @FXML
    void onActionAddProduct(ActionEvent event) throws IOException {

        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/IMS_Application/AddProduct.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /**
     * Deletes selected part. Confirmation prompt is loaded.
     * FUTURE ENHANCEMENT: Add ability to update a product that has the associated part to be deleted with a new replacement part.
     * @param event
     */
    @FXML
    void onActionDeletePart(ActionEvent event) {


        try {

            Part part = partsTableView.getSelectionModel().getSelectedItem();
            String partName = part.getName();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you would like to delete " + partName + " from the list?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                Inventory.deletePart(part);
                partsTableView.refresh();
            }
        }
        catch (Exception e){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("No part selected");
                alert.setContentText("Please select a part from the list to delete");
                alert.showAndWait();
        }
    }

    /**
     * Deletes selected product. Confirmation prompt is loaded.
     * @param event
     */
    @FXML
    void onActionDeleteProduct(ActionEvent event) {

        Product product = productsTableView.getSelectionModel().getSelectedItem();
        String productName = product.getName();

        if(product.getAllAssociatedParts().size() == 0) {
            try {

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you would like to delete " + productName + " from the list?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    Inventory.deleteProduct(product);
                    productsTableView.refresh();
                }
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("No product selected");
                alert.setContentText("Please select a product from the list to delete");
                alert.showAndWait();
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Removing Product");
            alert.setContentText("Product has associated parts. Remove associated parts before deleting products." );
            alert.showAndWait();
        }

    }

    /**
     * Loads modify part controller.
     * @param event
     * @throws IOException
     */
    @FXML
    void onActionModifyPart(ActionEvent event) throws IOException {

        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/IMS_Application/ModifyPart.fxml"));
            loader.load();

            ModifyPartController partController = loader.getController();

            int index = partsTableView.getSelectionModel().getSelectedIndex();

            partController.sendPart(index, partsTableView.getSelectionModel().getSelectedItem());

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        }
        catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No part selected");
            alert.setContentText("Please select a part from the list to modify");
            alert.showAndWait();

        }





    }

    /**
     * Loads Modify Product controller.
     * @param event
     * @throws IOException
     */
    @FXML
    void onActionModifyProduct(ActionEvent event) throws IOException {

        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/IMS_Application/ModifyProduct.fxml"));
            loader.load();

            ModifyProductController MPCController = loader.getController();

            int index = productsTableView.getSelectionModel().getSelectedIndex();

            MPCController.sendProduct(index, productsTableView.getSelectionModel().getSelectedItem());


            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        }
        catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No product selected");
            alert.setContentText("Please select a product from the list to modify");
            alert.showAndWait();
        }



    }


    /**
     * Exits application
     * @param event
     */
    @FXML
    void onActionExitBtn(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you would like to exit the application?");

        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            System.exit(0);
        }



    }

    /**
     * Facilitates searching parts.
     * @param event
     */
    @FXML
    void onInputPartSearchTxtChanged(KeyEvent event) {

        String partName = partSearchTxt.getText();
        if(partName != null) {
            try{
                int partId = Integer.parseInt(partName);
                partsTableView.getSelectionModel().select(Inventory.lookupPart(partId));
            }
            catch (Exception e){
                partsTableView.setItems(Inventory.lookupPart(partName));
            }
        }
        else{
            partsTableView.setItems(Inventory.getAllParts());
        }

    }

    /**
     * Facilitates searching products.
     * @param event
     */
    @FXML
    void onInputProductSearchTxtChanged(KeyEvent event) {
        String productName = productSearchTxt.getText();
        if(productName != null) {
            try {
                int productId = Integer.parseInt(productName);
                productsTableView.getSelectionModel().select(Inventory.lookupProduct(productId));
            } catch (Exception e) {
                productsTableView.setItems(Inventory.lookupProduct(productName));
            }
        }
        else{
            productsTableView.setItems(Inventory.getAllProducts());
        }

    }

    /**
     * Loads initial table data for products and parts.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        partsTableView.setItems(Inventory.getAllParts());
        partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInvLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        productsTableView.setItems(Inventory.getAllProducts());
        productIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInvLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productCostCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }


}
