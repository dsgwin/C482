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

    public static void deletePart(Part part)
    {
        allParts.remove(part);
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

    public static void deleteProduct(Product product)
    {
        allParts.remove(product);
    }


}
