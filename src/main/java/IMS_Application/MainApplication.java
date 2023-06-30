package IMS_Application;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("MainMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 400);
        stage.setTitle("C482 - Inventory Management System - Duncan Gwin");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        Outsourced part1 = new Outsourced(1, "Tire", 50.0, 10, 5, 25, "Schwinn");
        InHouse part2 = new InHouse(2, "Frame", 70.0, 7, 5, 15, 125);
        InHouse part3 = new InHouse(3, "Freewheel", 15.0, 7, 5, 15, 10);
        InHouse part4 = new InHouse(4, "Handlebars", 30.0, 9, 5, 20, 10);
        Inventory.addPart(part1);
        Inventory.addPart(part2);
        Inventory.addPart(part3);
        Inventory.addPart(part4);

        Product product1 =new Product(1,"Bicycle", 500.00, 3, 2, 10);
        product1.addAssociatedPart(part1);
        product1.addAssociatedPart(part2);
        Inventory.addProduct(product1);

        Product product2 =new Product(2,"Tricycle", 600.00, 2, 2, 10);
        product2.addAssociatedPart(part1);
        product2.addAssociatedPart(part2);
        Inventory.addProduct(product2);


        launch(args);


    }
}