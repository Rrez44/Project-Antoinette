module com.example.projectantoinette {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.projectantoinette to javafx.fxml;
    exports com.example.projectantoinette;
}