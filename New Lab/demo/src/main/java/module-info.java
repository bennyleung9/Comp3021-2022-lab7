module com.example {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.base to javafx.fxml;
    exports com.example.base;
}
