module pl.gornik.kontrolki {
    requires javafx.controls;
    requires javafx.fxml;


    opens pl.gornik.kontrolki to javafx.fxml;
    exports pl.gornik.kontrolki;
}