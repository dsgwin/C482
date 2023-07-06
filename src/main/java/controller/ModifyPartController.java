package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.util.Optional;

public class ModifyPartController {

    Stage stage;
    Parent scene;

    private int index;

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
    private RadioButton inHouseRBtn;

    @FXML
    private RadioButton outsourceRBtn;

    @FXML
    private ToggleGroup addPartToggle;

    /**
     * Returns user to the main menu if the cancel button is pressed.
     * Confirmation prompt will appear on screen.
     * @param event
     * @throws IOException if I/O error occurs.
     */
    @FXML
    void onActionModifyPartCancelBtn(ActionEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to cancel?\nAll values will be discarded.");

        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            returnToMainMenu(event);
        }

    }

    /**
     * Saves changes to the part in input form and returns user to the main menu.
     * @param event
     * @throws IOException if I/O error occurs.
     */
    @FXML
    void onActionModifyPartSaveBtn(ActionEvent event) throws IOException {

        // Alert Text currently does not display text when an error on MachineID takes place. This could

        String alertText = null;

        try{
            alertText = Inventory.formInputCheck(partInvTxt.getText(),partPriceTxt.getText(),
                    partMinTxt.getText(),partMaxTxt.getText());

            int id = Integer.parseInt(partIdTxt.getText());
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

            if((Inventory.minMaxCheck(min, max)) && (Inventory.inventoryCheck(min, max, stock))) {

                if (inHouseRBtn.isSelected()) {
                    InHouse updatedPart = new InHouse(id, name, price, stock, min, max, machineId);

                    Inventory.updatePart(index, updatedPart);
                    for (Product p : Inventory.getAllProducts()) {
                        for (Part part : p.getAllAssociatedParts()) {
                            if (updatedPart.getId() == part.getId()) {
                                int index = p.getAssociatePartIndex(part);
                                p.updateAssociatedPart(index, updatedPart);
                            }
                        }
                    }
                    returnToMainMenu(event);
                } else {
                    Outsourced updatedPart = new Outsourced(id, name, price, stock, min, max, companyName);

                    Inventory.updatePart(index, updatedPart);
                    for (Product p : Inventory.getAllProducts()) {
                        for (Part part : p.getAllAssociatedParts()) {
                            if (updatedPart.getId() == part.getId()) {
                                int index = p.getAssociatePartIndex(part);
                                p.updateAssociatedPart(index, updatedPart);
                            }
                        }
                    }
                    returnToMainMenu(event);
                }
            }

        }


        catch(NumberFormatException e){

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

    /**
     * Sends part fields from controller to another.
     * @param partIndex product index to be sent.
     * @param part product object to be sent.
     */
    public void sendPart(int partIndex, Part part){
        setIndex(partIndex);
        partIdTxt.setText(String.valueOf(part.getId()));
        partNameTxt.setText(part.getName());
        partPriceTxt.setText(String.valueOf(part.getPrice()));
        partInvTxt.setText(String.valueOf(part.getStock()));
        partMaxTxt.setText(String.valueOf(part.getMax()));
        partMinTxt.setText(String.valueOf(part.getMin()));

        if(part instanceof InHouse){
            inHouseRBtn.setSelected(true);
            machineId_companyName_Toggle.setText("Machine ID");
            partMachine_CompanyTxt.setText(String.valueOf(((InHouse) part).getMachineId()));
        }
        else {
            outsourceRBtn.setSelected(true);
            machineId_companyName_Toggle.setText("Company Name");
            partMachine_CompanyTxt.setText(((Outsourced) part).getCompanyName());
        }



    }

    private void setIndex(int partIndex ){
        index = partIndex;
    }



}