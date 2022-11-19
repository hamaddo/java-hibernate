package ru.nstu.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {
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

    @FXML
    private TabPane tabs;

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

    // @section Clients
    public void onClientSearchbarChange() {
        if (clientSearchbarInput.getText().isEmpty()) {
            clientList = clientRepo.findAll();
            onClientListChanged();
        }
    }

    public void onEmployerSearchbarChange() {
        if (employerSearchbarInput.getText().isEmpty()) {
            employerList = employerRepo.findAll();
            onEmployerListChanged();
        }
    }

    public void onFindClientByFio() {
        var fio = clientSearchbarInput.getText();

        clientList = clientRepo.findAllByName(fio);
        onClientListChanged();
    }

    public void onFindEmployerByName() {
        var fio = employerSearchbarInput.getText();

        employerList = employerRepo.findAllByName(fio);
        onEmployerListChanged();
    }

    public void onFindClientByGender() {
        var gender = clientSearchbarInput.getText();

        clientList = clientRepo.findAllByGender(gender);
        onClientListChanged();
    }
    public void onFindEmployerByOwnershipType() {
        var ownerShipType = employerSearchbarInput.getText();

        employerList = employerRepo.findAllByOLF(ownerShipType);
        onEmployerListChanged();
    }

    public void onFindClientByPhone() {
        var gender = clientSearchbarInput.getText();

        clientList = clientRepo.findAllByPhone(gender);
        onClientListChanged();
    }

    public void onFindEmployerByPhone() {
        var gender = employerSearchbarInput.getText();

        employerList = employerRepo.findAllByPhone(gender);
        onEmployerListChanged();
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
    protected void onChangeEmployerButtonClick() throws IOException {
        var selectedEmployer = employerTableView.getSelectionModel().getSelectedItem();

        var modal = new EmployerDialog(stage, selectedEmployer);

        modal.showAndWait().ifPresent(employer -> {
            var selectedIndex = employerList.indexOf(selectedEmployer);

            employerRepo.edit(employer);
            employerList.set(selectedIndex, employer);
            onEmployerListChanged();

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

    @FXML
    protected void onDeleteEmployerButtonClick() {
        var selectedEmployer = employerTableView.getSelectionModel().getSelectedItem();

        employerTableView.getSelectionModel().selectNext();

        employerRepo.delete(selectedEmployer);
        employerList.remove(selectedEmployer);
        onEmployerListChanged();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (url.toString().contains("client")) {
            TableInitializer.InitializeClientTable(clientTableView);
            clientList = clientRepo.findAll();
            onClientListChanged();

        }
        if (url.toString().contains("employer")) {
            TableInitializer.InitializeEmployerTable(employerTableView);
            employerList = employerRepo.findAll();
            onEmployerListChanged();
        }
    }
}