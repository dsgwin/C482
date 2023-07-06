package model;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

/**
 * @author
 * Duncan Gwin
 * dgwin4@wgu.edu
 * 008698673
 */

/**
 * The Inventory class contains static objects and functions that will be used to display and manipulate inventory information to the main screen.
 */

public class Inventory {

    public static int nextPartId = 0;
    public static int nextProductId = 0;

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    private static  ObservableList<Part> filteredParts = FXCollections.observableArrayList();
    private static  ObservableList<Product> filteredProducts = FXCollections.observableArrayList();

    // Begin part methods

    /**
     * Method to get the next available part ID.
     * @return The Next Part ID.
     */
    public static int getNextPartId(){
        nextPartId++;
        return nextPartId;
    }

    /**
     * Method to add part to the inventory allParts observable list.
     * @param part Part to be added.
     */
    public static void addPart(Part part)
    {
        allParts.add(part);
    }

    /**
     * Method to get the list of all parts in inventory.
     * @return Returns list of part objects in inventory.
     */
    public static ObservableList<Part> getAllParts()
    {
        return  allParts;
    }

    /**
     * Method to remove part from inventory.
     * @param part Part object to be removed from inventory.
     * @return Returns true if part removed, false if not deleted.
     */
    public static boolean deletePart(Part part) {
        try{
        allParts.remove(part);
        return true;}
        catch (Exception e){
            System.out.println("Error: Unable to delete selected part");
        }
        return false;
    }

    /**
     * Method to look up a part in inventory with part ID as input parameter.
     * @param partId Part ID to look up in inventory.
     * @return Returns part object if found, null if not found.
     */
    public static Part lookupPart(int partId) {

        for (Part p:allParts){
            if (p.getId() == partId){
                return p;
            }
        }
        return null;

    }

    /**
     * Method to look up a part in inventory with part name as input parameter.
     * @param partName
     * @return returns an observable list of parts that match the input string.
     */
    public static ObservableList<Part> lookupPart(String partName) {

        if(!(filteredParts.isEmpty())){
            filteredParts.clear();
        }

        for (Part p:allParts){
            if (p.getName().toLowerCase().contains(partName.toLowerCase())){
                filteredParts.add(p);
            }
        }
        return filteredParts;

    }

    /**
     * Method to update an existing part in the inventory with a new part object.
     * @param index Index of part in list to be updated.
     * @param newPart New part object to replace the old part.
     */
    public static void updatePart(int index, Part newPart){

        allParts.set(index, newPart);

    }


    // Begin product methods

    /**
     * Method to get the next available product ID.
     * @return Returns the next product ID.
     */
    public static int getNextProductId(){
        nextProductId++;
        return nextProductId;
    }

    /**
     * Method to add product to the inventory.
     * @param product The product to be added to inventory.
     */
    public static void addProduct(Product product)
    {
        allProducts.add(product);
    }

    /**
     * Method to get all products in inventory.
     * @return Returns observable list of all products in inventory.
     */
    public static ObservableList<Product> getAllProducts()
    {
        return  allProducts;
    }

    /**
     * Method to remove product from inventory.
     * @param product Product to remove from inventory.
     * @return Returns true if product removed, false if not deleted.
     */
    public static boolean deleteProduct(Product product)
    {
        try{
            allProducts.remove(product);
            return true;}
        catch (Exception e){
            System.out.println("Error: Unable to delete selected product");
        }
        return false;
    }

    /**
     * Method to look up a product in inventory with product ID as input parameter
     * @param productId Product ID to lookup.
     * @return Returns product if found in inventory.
     */
    public static Product lookupProduct(int productId) {

        for (Product p:allProducts){
            if (p.getId() == productId){
                return p;
            }
        }
        return null;
    }

    /**
     * Method to look up a product in inventory with product name as input parameter
     * @param productName Product Name string to look for.
     * @return Returns list of products containing search string.
     */
    public static ObservableList<Product> lookupProduct(String productName) {
        if(!(filteredProducts.isEmpty())){
            filteredProducts.clear();
        }

        for (Product p:allProducts){
            if (p.getName().toLowerCase().contains(productName.toLowerCase())){
                filteredProducts.add(p);
            }
        }
        return filteredProducts;
    }

    /**
     * Method to update product in inventory with new values.
     * @param index Index of product in inventory to update.
     * @param newProduct New Product object to replace the old product.
     */
    public static void updateProduct(int index, Product newProduct){

        allProducts.set(index, newProduct);

    }

    /**
     * RUNTIME ERROR Added minMaxCheck to prevent logical errors when entering a larger minimum value than maximum.
     * @param min Minimum value to compare.
     * @param max Maximum value to compare.
     * @return Returns true if check passes, false if fails.
     */
    public static boolean minMaxCheck(int min, int max){
        boolean minMaxValid = true;
        if(!(min <= max && max >= min)){
            minMaxValid = false;
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Min/Max Values");
            alert.setContentText("Please enter valid values for minimum and maximum. Min should not exceed max.");
            alert.showAndWait();;
        }
        return minMaxValid;
    }

    /**
     * RUNTIME ERROR Added inventoryCheck to prevent logical errors when entering a larger inventory value than maximum or a smaller inventory value than minimum.
     * @param min Minimum value to compare.
     * @param max Maximum value o compare.
     * @param stock Inventory value to compare.
     * @return Returns true if check passes, false if fails.
     */
    public static boolean inventoryCheck(int min, int max, int stock){
        boolean inventoryValid = true;
        if(!(stock >= min && stock <= max)) {
            inventoryValid = false;
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Inventory Value");
            alert.setContentText("Stock should not be below the minimum or above the maximum values");
            alert.showAndWait();

        }
        return inventoryValid;

        }

    /**
     * RUNTIME ERROR Added input check method for form inputs to validate integer inputs are successfully converted from their string form.
     * FUTURE ENHANCEMENT Include a check for MachineID in a future release.
     * @param inventory Inventory value to check type.
     * @param price Price value to check type.
     * @param min Minimum value to check type.
     * @param max Maximum value to check type.
     * @return Returns a string to be used in the alert to point user to the field that needs corrected.
     */
    public static String formInputCheck(String inventory, String price, String min, String max){
        String alertText = null;

        try {
            int stock = Integer.parseInt(inventory);
        }
        catch (Exception e) {
            alertText = "Inventory Field Invalid. Must be an Integer";
        }
        try {
            double priceCheck = Double.parseDouble(price);
        }
        catch (Exception e) {

            alertText = "Price Field Invalid. Must be a decimal format.\nex. 5.99";

        }
        try{
            int maxCheck = Integer.parseInt(max);
        }
        catch (Exception e) {
            alertText = "Max Field Invalid. Must be an Integer";

        }
        try {
            int minCheck = Integer.parseInt(min);
        }
        catch (Exception e) {
            alertText = "Min Field Invalid. Must be an Integer";

        }


        return alertText;
    }

}
