package ru.nstu.ui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import ru.nstu.entity.Client;
import ru.nstu.repository.ClientRepo;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class ClientTab implements Initializable {
    private final ClientRepo clientRepo = ClientRepo.getInstance();

    private List<Client> clientList;
    @FXML
    public TableView<Client> clientTableView;

    @FXML
    public TextField clientSearchbarInput;


    private void onClientListChanged() {
        if (clientList == null) {
            clientTableView.setItems(FXCollections.observableList(Collections.emptyList()));
            return;
        }

        clientTableView.setItems(FXCollections.observableList(clientList));
        clientTableView.refresh();
    }

    public void onClientSearchbarChange() {
        if (clientSearchbarInput.getText().isEmpty()) {
            clientList = clientRepo.findAll();
            onClientListChanged();
        }
    }

    public void onFindClientByFio() {
        var fio = clientSearchbarInput.getText();

        clientList = clientRepo.findAllByName(fio);
        onClientListChanged();
    }

    public void onFindClientByGender() {
        var gender = clientSearchbarInput.getText();

        clientList = clientRepo.findAllByGender(gender);
        onClientListChanged();
    }

    public void onFindClientByPhone() {
        var gender = clientSearchbarInput.getText();

        clientList = clientRepo.findAllByPhone(gender);
        onClientListChanged();
    }

    @FXML
    protected void onCreateClientButtonClick() throws IOException {
        var modal = new ClientDialog(MainController.stage);

        modal.showAndWait().ifPresent(client -> {
            clientRepo.save(client);
            clientList.add(client);
            onClientListChanged();

            modal.close();
        });
    }

    @FXML
    protected void onChangeClientButtonClick() throws IOException {
        var selectedClient = clientTableView.getSelectionModel().getSelectedItem();

        var modal = new ClientDialog(MainController.stage, selectedClient);

        modal.showAndWait().ifPresent(client -> {
            var selectedIndex = clientList.indexOf(selectedClient);

            clientRepo.edit(client);
            clientList.set(selectedIndex, client);
            onClientListChanged();

            modal.close();
        });
    }

    @FXML
    protected void onDeleteClientButtonClick() {
        var selectedClient = clientTableView.getSelectionModel().getSelectedItem();

        clientTableView.getSelectionModel().selectNext();

        clientRepo.delete(selectedClient);
        clientList.remove(selectedClient);
        onClientListChanged();
    }

    public void onViewClientRequests() throws IOException {
        var selectedClient = clientTableView.getSelectionModel().getSelectedItem();

        var requestViewDialog = new RequestViewDialog(MainController.stage, selectedClient);
        requestViewDialog.setOnHiding((WindowEvent windowEvent) -> {
            this.clientList = this.clientRepo.findAll();
            onClientListChanged();
        });
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TableInitializer.InitializeClientTable(clientTableView);
        clientList = clientRepo.findAll();
        onClientListChanged();
    }
}
