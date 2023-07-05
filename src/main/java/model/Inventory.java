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
    public static int getNextPartId(){
        nextPartId++;
        return nextPartId;
    }

    public static void addPart(Part part)
    {
        allParts.add(part);
    }

    public static ObservableList<Part> getAllParts()
    {
        return  allParts;
    }

    public static boolean deletePart(Part part) {
        try{
        allParts.remove(part);
        return true;}
        catch (Exception e){
            System.out.println("Error: Unable to delete selected part");
        }
        return false;
    }

    public static Part lookupPart(int partId) {

        for (Part p:allParts){
            if (p.getId() == partId){
                return p;
            }
        }
        return null;

    }
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
    public static void updatePart(int index, Part newPart){

        allParts.set(index, newPart);

    }


    // Begin product methods

    public static int getNextProductId(){
        nextProductId++;
        return nextProductId;
    }

    public static void addProduct(Product product)
    {
        allProducts.add(product);
    }

    public static ObservableList<Product> getAllProducts()
    {
        return  allProducts;
    }


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

    public static Product lookupProduct(int productId) {

        for (Product p:allProducts){
            if (p.getId() == productId){
                return p;
            }
        }
        return null;
    }
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

    public static void updateProduct(int index, Product newProduct){

        allProducts.set(index, newProduct);

    }
    // Added minMaxCheck to prevent logical errors when entering a larger minimum value than maximum.
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

    /* Added inventoryCheck to prevent logical errors when entering a larger inventory value than maximum
    or a smaller inventory value than minimum.
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
