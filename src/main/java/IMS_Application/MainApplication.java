package IMS_Application;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;

/**
 * @author
 * Duncan Gwin
 * dgwin4@wgu.edu
 * 008698673
 */

/**
 * This class runs the main application and launches the main menu screen.
 */
public class MainApplication extends Application {

    /**
     * Initializes the Main Menu screen.
     * @param stage The stage to be loaded.
     * @throws IOException If an I/O error occurs.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("MainMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 400);
        stage.setTitle("C482 - Inventory Management System - Duncan Gwin");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main method loads sample data and launches the application.
     * @param args
     */
    public static void main(String[] args) {

        Outsourced part1 = new Outsourced(Inventory.getNextPartId(), "Tire", 10.0, 10, 5, 25, "Schwinn");
        InHouse part2 = new InHouse(Inventory.getNextPartId(), "Frame", 70.0, 7, 5, 15, 125);
        InHouse part3 = new InHouse(Inventory.getNextPartId(), "Freewheel", 15.0, 7, 5, 15, 123);
        InHouse part4 = new InHouse(Inventory.getNextPartId(), "Handlebars", 15.0, 9, 5, 20, 500);
        Inventory.addPart(part1);
        Inventory.addPart(part2);
        Inventory.addPart(part3);
        Inventory.addPart(part4);

        Product product1 =new Product(Inventory.getNextProductId(),"Bicycle", 500.00, 3, 2, 10);
        product1.addAssociatedPart(part1);
        product1.addAssociatedPart(part2);
        Inventory.addProduct(product1);


        Product product2 =new Product(Inventory.getNextProductId(),"Tricycle", 600.00, 2, 2, 10);
        product2.addAssociatedPart(part1);
        product2.addAssociatedPart(part2);
        Inventory.addProduct(product2);


        launch(args);


    }
}