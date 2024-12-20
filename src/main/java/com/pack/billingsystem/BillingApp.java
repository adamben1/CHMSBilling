package com.pack.billingsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

public class BillingApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("billing.fxml"));
        ScrollPane root = loader.load();
        Scene scene = new Scene(root, 1100, 700);

        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Billing System");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}