module com.neverbdneverw.focalors {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.prefs;
    requires java.desktop;

    opens com.neverbdneverw.focalors to javafx.fxml;
    exports com.neverbdneverw.focalors;
}
