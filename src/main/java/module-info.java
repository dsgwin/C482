module dsgwin.c482 {
    requires javafx.controls;
    requires javafx.fxml;


    opens dsgwin.c482 to javafx.fxml;
    exports dsgwin.c482;
    exports controller;
    opens controller to javafx.fxml;
}