module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
    exports com.example.demo.gameloop;
    opens com.example.demo.gameloop to javafx.fxml;
    exports com.example.demo.TicTacToe;
    opens com.example.demo.TicTacToe to javafx.fxml;
    exports com.example.demo.Othello;
    opens com.example.demo.Othello to javafx.fxml;
    exports com.example.demo.connection;
    opens com.example.demo.connection to javafx.fxml;
    exports com.example.demo.board;
    opens com.example.demo.board to javafx.fxml;
    exports com.example.demo.data;
    opens com.example.demo.data to javafx.fxml;
}
