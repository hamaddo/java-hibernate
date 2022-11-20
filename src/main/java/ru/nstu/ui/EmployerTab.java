package ru.nstu.ui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.nstu.entity.Employer;
import ru.nstu.repository.EmployerRepo;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class EmployerTab implements Initializable {
    private final EmployerRepo employerRepo = EmployerRepo.getInstance();

    final static EmployerTab instance = new EmployerTab();

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

    public static EmployerTab getInstance() {
        return instance;
    }

    private void onEmployerListChanged() {
        if (employerList == null) {
            employerTableView.setItems(FXCollections.observableList(Collections.emptyList()));
            return;
        }

        employerTableView.setItems(FXCollections.observableList(employerList));
        employerTableView.refresh();
    }

    public void onEmployerSearchbarChange() {
        if (employerSearchbarInput.getText().isEmpty()) {
            employerList = employerRepo.findAll();
            onEmployerListChanged();
        }
    }


    public void onFindEmployerByName() {
        var fio = employerSearchbarInput.getText();

        employerList = employerRepo.findAllByName(fio);
        onEmployerListChanged();
    }


    public void onFindEmployerByOwnershipType() {
        var ownerShipType = employerSearchbarInput.getText();

        employerList = employerRepo.findAllByOLF(ownerShipType);
        onEmployerListChanged();
    }


    public void onFindEmployerByPhone() {
        var gender = employerSearchbarInput.getText();

        employerList = employerRepo.findAllByPhone(gender);
        onEmployerListChanged();
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
    protected void onDeleteEmployerButtonClick() {
        var selectedEmployer = employerTableView.getSelectionModel().getSelectedItem();

        employerTableView.getSelectionModel().selectNext();

        employerRepo.delete(selectedEmployer);
        employerList.remove(selectedEmployer);
        onEmployerListChanged();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TableInitializer.InitializeEmployerTable(employerTableView);
        employerList = employerRepo.findAll();
        onEmployerListChanged();
    }

}
