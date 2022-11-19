package ru.nstu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.nstu.controllers.MainController;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);

        stage.setTitle("Бюро ПТУ");
        stage.setResizable(false);
        stage.setScene(scene);

        MainController mainController = fxmlLoader.getController();
        mainController.setStage(stage);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
