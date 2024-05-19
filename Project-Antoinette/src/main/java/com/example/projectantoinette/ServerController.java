package com.example.projectantoinette;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ServerController implements Initializable {

    @FXML
    private ScrollPane messageBoard;

    private Thread serverThread;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        startServer();
    }

    private void startServer() {
        EchoServer echoServer = new EchoServer();
        serverThread = new Thread(echoServer);
        serverThread.setDaemon(true); // This ensures the server thread will not prevent the application from exiting
        serverThread.start();
    }

    public void handleNewMessage(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ClientView.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Echo Client");
            stage.setScene(new Scene(fxmlLoader.load()));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}