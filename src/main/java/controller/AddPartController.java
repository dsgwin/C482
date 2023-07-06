package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Outsourced;

import java.io.IOException;
import java.util.EventObject;
import java.util.Optional;

/**
 * @author
 * Duncan Gwin
 * dgwin4@wgu.edu
 * 008698673
 */

/**
 * This class is the controller for the "Add Part" menu of the application.
 */

public class AddPartController {

    Stage stage;
    Parent scene;

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
    private RadioButton inHouseRBtn;
    @FXML
    private RadioButton outsourcedRBtn;

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
    private ToggleGroup addPartToggle;

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
     * Creates the part from input form and returns user to the main menu.
     * @param event
     * @throws IOException if I/O error occurs.
     */
    @FXML
    void onActionSaveBtn(ActionEvent event) throws IOException {

        String alertText = null;

        try{
                alertText = Inventory.formInputCheck(partInvTxt.getText(),partPriceTxt.getText(),
                        partMinTxt.getText(),partMaxTxt.getText());

                int id = Inventory.getNextPartId();
                String name = partNameTxt.getText();
                int stock = Integer.parseInt(partInvTxt.getText());
                double price = Double.parseDouble(partPriceTxt.getText());
                int max = Integer.parseInt(partMaxTxt.getText());
                int min = Integer.parseInt(partMinTxt.getText());
                int machineId = -1;
                String companyName = null;

                try{
                    if (inHouseRBtn.isSelected()){
                        machineId = Integer.parseInt(partMachine_CompanyTxt.getText());

                    }else{
                        companyName = partMachine_CompanyTxt.getText();
                    }
                }
                catch (Exception e){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Adding Part");
                    alert.setContentText("MachineID Invalid. Must be an Integer");
                    alert.showAndWait();

                }

                if((Inventory.minMaxCheck(min, max) == true) && (Inventory.inventoryCheck(min, max, stock) == true)){

                    if (inHouseRBtn.isSelected()) {
                        InHouse part = new InHouse(id, name, price, stock, min, max, machineId);

                        Inventory.addPart(part);
                        returnToMainMenu(event);
                    }
                    else {
                        Outsourced part = new Outsourced(id, name, price, stock, min, max, companyName);
                        Inventory.addPart(part);
                        returnToMainMenu(event);
                    }

                }
                else {Inventory.nextPartId--;}


        }
        catch(NumberFormatException e){

            Inventory.nextPartId--;
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Adding Part");
            alert.setContentText(alertText);
            alert.showAndWait();


        }



    }

    /**
     * Changes text of Machine ID/ Company Name field based on Radio button selection.
     * @param event
     */
    @FXML
    void inHouseSelected(ActionEvent event) {

        machineId_companyName_Toggle.setText("Machine ID");

    }

    /**
     * Changes text of Machine ID/ Company Name field based on Radio button selection.
     * @param event
     */
    @FXML
    void outsourcedSelected(ActionEvent event) {

        machineId_companyName_Toggle.setText("Company Name");

    }

}