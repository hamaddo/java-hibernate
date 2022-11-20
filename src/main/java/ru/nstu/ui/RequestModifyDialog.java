package ru.nstu.ui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Window;
import ru.nstu.entity.Client;
import ru.nstu.entity.Request;

import java.io.IOException;

public class RequestModifyDialog extends Dialog<Request> {
    private Request modifyingRequest;

    private final Client relatedClient;

    @FXML
    public TextField positionNameTextField;
    @FXML
    public TextField salaryTextField;


    public RequestModifyDialog(Window owner, Client client) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("request-modify-dialog.fxml"));
        fxmlLoader.setController(this);

        DialogPane dialogPane = fxmlLoader.load();

        initOwner(owner);
        initModality(Modality.APPLICATION_MODAL);

        setTitle("Создать клиента");
        setResizable(false);
        setDialogPane(dialogPane);

        relatedClient = client;

        setResultConverter(buttonType -> {
            if (buttonType != null && buttonType.getButtonData() == ButtonBar.ButtonData.APPLY && relatedClient != null) {
                var positionName = positionNameTextField.getText();
                var salary = Integer.parseInt(salaryTextField.getText());

                if (modifyingRequest != null) {
                    modifyingRequest.setPositionName(positionName);
                    modifyingRequest.setSalary(salary);
                    return modifyingRequest;
                }

                return new Request(positionName, salary, relatedClient);
            }

            return null;
        });

        setOnShowing(dialogEvent -> Platform.runLater(() -> positionNameTextField.requestFocus()));
    }

    public RequestModifyDialog(Window owner, Client client, Request request) throws IOException {
        this(owner, client);

        modifyingRequest = request;

        positionNameTextField.setText(request.getPositionName());
        salaryTextField.setText(request.getSalary().toString());
    }
}
