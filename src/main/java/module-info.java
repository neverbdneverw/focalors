module com.neverbdneverw.focalors {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.neverbdneverw.focalors to javafx.fxml;
    exports com.neverbdneverw.focalors;
}
