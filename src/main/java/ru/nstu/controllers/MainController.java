package ru.nstu.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import ru.nstu.entity.Client;
import ru.nstu.entity.Employer;
import ru.nstu.repository.ClientRepo;
import ru.nstu.repository.EmployerRepo;
import ru.nstu.ui.ClientDialog;
import ru.nstu.ui.EmployerDialog;
import ru.nstu.ui.TableInitializer;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class MainController {
    private final ClientRepo clientRepo = ClientRepo.getInstance();

    private final EmployerRepo employerRepo = EmployerRepo.getInstance();


    private List<Client> clientList;
    @FXML
    public TableView<Client> clientTableView;

    @FXML
    public TextField clientSearchbarInput;

    @FXML
    public TextField employerSearchbarInput;

    @FXML
    private List<Employer> employerList;

    @FXML
    public TableView<Employer> employerTableView;


    Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    private void onClientListChanged() {
        if (clientList == null) {
            clientTableView.setItems(FXCollections.observableList(Collections.emptyList()));
            return;
        }

        clientTableView.setItems(FXCollections.observableList(clientList));
        clientTableView.refresh();
    }

    private void onEmployerListChanged() {
        if (employerList == null) {
            employerTableView.setItems(FXCollections.observableList(Collections.emptyList()));
            return;
        }

        employerTableView.setItems(FXCollections.observableList(employerList));
        employerTableView.refresh();
    }


    @FXML
    private void initialize() {
        TableInitializer.InitializeClientTable(clientTableView);
        TableInitializer.InitializeEmployerTable(employerTableView);

        employerList = employerRepo.findAll();
        clientList = clientRepo.findAll();
        onClientListChanged();
        onEmployerListChanged();
    }

    // @section Clients
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
        var modal = new ClientDialog(stage);

        modal.showAndWait().ifPresent(client -> {
            clientRepo.save(client);
            clientList.add(client);
            onClientListChanged();

            modal.close();
        });
    }

    @FXML
    protected void onCreateEmployerButtonClick() throws IOException {
        var modal = new EmployerDialog(stage);

        modal.showAndWait().ifPresent(employer -> {
            employerRepo.save(employer);
            employerList.add(employer);
            onEmployerListChanged();

            modal.close();
        });
    }


    @FXML
    protected void onChangeClientButtonClick() throws IOException {
        var selectedClient = clientTableView.getSelectionModel().getSelectedItem();

        var modal = new ClientDialog(stage, selectedClient);

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
}