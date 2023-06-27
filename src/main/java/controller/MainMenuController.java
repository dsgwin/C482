package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

public class MainMenuController {

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
    private TableColumn<?, ?> partIdCol;

    @FXML
    private TableColumn<?, ?> partInvLevelCol;

    @FXML
    private TableColumn<?, ?> partNameCol;

    @FXML
    private TableColumn<?, ?> partPriceCol;

    @FXML
    private TextField partSearchTxt;

    @FXML
    private TableColumn<?, ?> productCostCol;

    @FXML
    private TableColumn<?, ?> productIdCol;

    @FXML
    private TableColumn<?, ?> productInvLevelCol;

    @FXML
    private TableColumn<?, ?> productNameCol;

    @FXML
    private TextField productSearchTxt;

    @FXML
    void onActionAddPart(ActionEvent event) {

        System.out.println("Add Part Button Clicked");

    }

    @FXML
    void onActionAddProduct(ActionEvent event) {

        System.out.println("Add Product Button Clicked");

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
    void onActionModifyPart(ActionEvent event) {

        System.out.println("Modify Part Button Clicked");

    }

    @FXML
    void onActionModifyProduct(ActionEvent event) {

        System.out.println("Modify Product Button Clicked");

    }

}
