package com.example.projectantoinette;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class ClientController implements Initializable {

    @FXML
    private TextArea messageArea;

    @FXML
    private TextField inputField;

    private Socket socket;
    private InputStream inputStream;
    private OutputStream outputStream;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        startClient();
    }

    private void startClient() {
        final String SERVER_ADDRESS = "localhost";
        final int SERVER_PORT = 12345;

        try {
            socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();

            new Thread(this::listenForMessages).start();
        } catch (IOException e) {
            messageArea.appendText("Client error: " + e.getMessage() + "\n");
        }
    }

    private void listenForMessages() {
        byte[] buffer = new byte[1024];
        int bytesRead;
        try {
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                String response = new String(buffer, 0, bytesRead);
                messageArea.appendText("\nServer response: " + response + "\n");
            }
        } catch (IOException e) {
            messageArea.appendText("Connection closed.\n");
        }
    }

    @FXML
    private void handleSendMessage() {
        String message = inputField.getText();
        if (message.isEmpty()) {
            return;
        }

        try {
            outputStream.write(message.getBytes());
            appendToMessageArea(message);
            inputField.clear();
        } catch (IOException e) {
            messageArea.appendText("Failed to send message: " + e.getMessage() + "\n");
        }
    }
    private void appendToMessageArea(String message) {
        Platform.runLater(() -> messageArea.appendText("You: " + message + "\n" + "---" + LocalDateTime.now().getHour() + ":"+ LocalDateTime.now().getMinute()+ "---")) ;
    }
}
