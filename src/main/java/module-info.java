module com.assignment_02 {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jetbrains.annotations;


    opens com.assignment_02 to javafx.fxml;
    exports com.assignment_02;
}