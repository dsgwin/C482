package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author
 * Duncan Gwin
 * dgwin4@wgu.edu
 * 008698673
 */

/**
 * The Product class contains methods and constructors for putting products into inventory.
 */
public class Product {

    public ObservableList<Part> associatedParts;
    public int id;
    public String name;
    public double price;
    public int stock;
    public int min;
    public int max;

    /**
     * Product constructor with blank associated parts lists.
     * @param id Sets product ID.
     * @param name Sets product name.
     * @param price Sets product price.
     * @param stock Sets product inventory level.
     * @param min Sets product minimum inventory.
     * @param max Sets product maximum inventory.
     */
    public Product(int id, String name, double price, int stock, int min, int max) {
        this.associatedParts = FXCollections.observableArrayList();
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**
     * Overloaded Product constructor that accepts a list of associated parts.
     * @param associatedParts Associated parts of product
     * @param id Sets product ID.
     * @param name Sets product name.
     * @param price Sets product price.
     * @param stock Sets product inventory level.
     * @param min Sets product minimum inventory.
     * @param max Sets product maximum inventory.
     */
    public Product(ObservableList associatedParts, int id, String name, double price, int stock, int min, int max) {
        this.associatedParts = associatedParts;
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**
     * Method to get product's associated parts.
     * @return returns list of associated parts.
     */
    public ObservableList<Part> getAssociatedParts() {
        return associatedParts;
    }

    /**
     * Method to set the associated parts of a product.
     * @param associatedParts List of associated parts to set.
     */
    public void setAssociatedParts(ObservableList<Part> associatedParts) {
        this.associatedParts = associatedParts;
    }

    /**
     * Method to get the product ID.
     * @return Returns product ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Method to set the product ID.
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Method to get the product name.
     * @return Returns product name.
     */
    public String getName() {
        return name;
    }

    /**
     * Method to set the product name.
     * @param name String of name to be set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * method to get the product price.
     * @return Returns double of the product price.
     */
    public double getPrice() {
        return price;
    }

    /**
     * method to set the product price.
     * @param price Price to set.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Method to get the product inventory level.
     * @return Returns the current stock level of the product.
     */
    public int getStock() {
        return stock;
    }

    /**
     * Method to set the product stock level.
     * @param stock Stock level to set.
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Method to get minimum inventory level.
     * @return Returns the minimum inventory level.
     */
    public int getMin() {
        return min;
    }

    /**
     * Method to set the minimum inventory level.
     * @param min Minimum inventory level to set.
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * Method to get maximum inventory level.
     * @return Returns the maximum inventory level.
     */
    public int getMax() {
        return max;
    }

    /**
     * Method to set the maximum inventory level.
     * @param max Maximum inventory level to set.
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * Method to add associated part to the associated parts list
     * @param part Part to be added.
     */
    public void addAssociatedPart(Part part){
        associatedParts.add(part);
    }

    /**
     * Method to remove an associated part from the list.
     * @param selectedAssociatedPart The associated part to be removed.
     * @return returns true if product removed.
     */
    public boolean removeAssociatedPart(Part selectedAssociatedPart){
        associatedParts.remove(selectedAssociatedPart);
        return true;
    }

    /**
     * Method to get all associated parts.
     * @return Returns list of associated parts.
     */
    public ObservableList<Part> getAllAssociatedParts(){
        return associatedParts;
    }

    /**
     * Method to update an associated part in the list.
     * @param index Index of part to be updated.
     * @param updatedPart New part to replace the old part.
     */
    public void updateAssociatedPart(int index, Part updatedPart){
        associatedParts.set(index, updatedPart);
    }

    /**
     * Method to get the associated part index.
     * @param part Part to find the index of.
     * @return Returns the index of the part being searched.
     */
    public int getAssociatePartIndex(Part part){

        int index = associatedParts.indexOf(part);
        return index;
    }
}
