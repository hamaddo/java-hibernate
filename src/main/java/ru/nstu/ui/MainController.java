package ru.nstu.ui;

import javafx.stage.Stage;

public class MainController {

    private final ClientTab clientTab = ClientTab.getInstance();

    private final EmployerTab employerTab = EmployerTab.getInstance();

    Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
        clientTab.setStage(stage);
        employerTab.setStage(stage);
    }
}