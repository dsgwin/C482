package model;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /* Begin part methods */
    public static void addPart(Part part)
    {
         allParts.add(part);
    }

    public static ObservableList<Part> getAllParts()
    {
        return  allParts;
    }

    public static boolean deletePart(Part part)
    {
        allParts.remove(part);
        return true;
    }

    public static  Part lookupPart(int partId) {

        for (Part p:allParts){
            if (p.getId() == partId){
                return p;
            }
        }

        return null;
    }
    public static Part lookupPart(String partName) {
        return null;
    }


    /* Begin product methods */

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
        allParts.remove(product);
        return true;
    }

    public static Product lookupProduct(int productId) {
        return null;
    }
    public static Product lookupProduct(String productName) {
        return null;
    }

    public static void updateProduct(int index, Product newProduct){

    }


}
