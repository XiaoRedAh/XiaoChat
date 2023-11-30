module org.itstack.naive.chat.ui {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens org.itstack.naive.chat.ui to javafx.fxml;
    exports org.itstack.naive.chat.ui;
}
