package model;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    private static  ObservableList<Part> filteredParts = FXCollections.observableArrayList();
    private static  ObservableList<Product> filteredProducts = FXCollections.observableArrayList();

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

    }


}
