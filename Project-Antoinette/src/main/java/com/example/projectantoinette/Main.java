package com.example.projectantoinette;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ServerBoard.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setResizable(false);
        stage.setTitle("Server");
        stage.setScene(scene);
        URL imageUrl = getClass().getResource("/com/example/projectantoinette/cat.png");
        stage.getIcons().add(new Image(imageUrl.toString()));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}