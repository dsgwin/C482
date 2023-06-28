module dsgwin.IMS_Application {
    requires javafx.controls;
    requires javafx.fxml;


    opens IMS_Application to javafx.fxml;
    exports IMS_Application;
    exports controller;
    opens controller to javafx.fxml;
}