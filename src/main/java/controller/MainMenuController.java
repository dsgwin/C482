package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class MainMenuController implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    private Button addPartBtn;

    @FXML
    private Button addProductBtn;

    @FXML
    private Button deletePartBtn;

    @FXML
    private Button deleteProductBtn;

    @FXML
    private Button modifyPartBtn;

    @FXML
    private Button modifyProductBtn;

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

    @FXML
    void onActionAddPart(ActionEvent event) throws IOException {

        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/IMS_Application/AddPart.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onActionAddProduct(ActionEvent event) throws IOException {

        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/IMS_Application/AddProduct.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onActionDeletePart(ActionEvent event) {

        System.out.println("Delete Part Button Clicked");

    }

    @FXML
    void onActionDeleteProduct(ActionEvent event) {

        System.out.println("Delete Product Button Clicked");

    }

    @FXML
    void onActionModifyPart(ActionEvent event) throws IOException {

        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/IMS_Application/ModifyPart.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onActionModifyProduct(ActionEvent event) throws IOException {

        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/IMS_Application/ModifyProduct.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onActionExitBtn(ActionEvent event) {

        System.exit(0);

    }

    @FXML
    void onInputPartSearchTxtChanged(KeyEvent event) {
        String partName = partSearchTxt.getText();
        if(partName != null) {
            partsTableView.setItems(Inventory.lookupPart(partName));
        }
        else{
            partsTableView.setItems(Inventory.getAllParts());
        }

    }

    @FXML
    void onInputProductSearchTxtChanged(KeyEvent event) {
        String productName = productSearchTxt.getText();
        if(productName != null) {
            productsTableView.setItems(Inventory.lookupProduct(productName));
        }
        else{
            productsTableView.setItems(Inventory.getAllProducts());
        }

    }

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

    public boolean search(int id){
        for(Part p : Inventory.getAllParts()){
            if (p.getId() == id)
                return true;

        }
        return false;
    }

    public boolean modifyPart(int id, Part part){
        int index = -1;
        for(Part p : Inventory.getAllParts()){
            index++;

            if(p.getId() == id)
            {
                Inventory.getAllParts().set(index, part);
                return true;
            }
        }
        return false;
    }

}
