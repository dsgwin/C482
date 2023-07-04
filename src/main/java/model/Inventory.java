package model;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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


}
