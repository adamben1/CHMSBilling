module com.pack.billingsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires kernel;
    requires layout;
    requires io;
    requires java.sql;


    opens com.pack.billingsystem to javafx.fxml;
    exports com.pack.billingsystem;
    exports com.pack.billingsystem.controllers;
    opens com.pack.billingsystem.controllers to javafx.fxml;
}