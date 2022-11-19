package ru.nstu.ui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Window;
import ru.nstu.entity.Employer;
import ru.nstu.util.OwnershipType;

import java.io.IOException;

public class EmployerDialog extends Dialog<Employer> {
    private Employer modifyingEmployer;

    @FXML
    public TextField nameTextField;
    @FXML
    public TextField registryTextField;

    @FXML
    public TextField addressTextField;

    @FXML
    public TextField phoneTextField;

    @FXML
    public ComboBox<OwnershipType> ownershipTypeComboBox;

    public EmployerDialog(Window owner) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("employer.fxml"));
        fxmlLoader.setController(this);

        DialogPane dialogPane = fxmlLoader.load();

        initOwner(owner);
        initModality(Modality.APPLICATION_MODAL);

        setTitle("Создать работодателя");
        setResizable(false);
        setDialogPane(dialogPane);

        setResultConverter(buttonType -> {
            if (buttonType != null && buttonType.getButtonData() == ButtonBar.ButtonData.APPLY) {
                var name = nameTextField.getText();
                var ownershipType = ownershipTypeComboBox.getValue();
                var registryNumber = Integer.parseInt(registryTextField.getText());
                var address = addressTextField.getText();
                var phone = phoneTextField.getText();

                if (modifyingEmployer != null) {
                    modifyingEmployer.setName(name);
                    modifyingEmployer.setOwnershipType(ownershipType);
                    modifyingEmployer.setRegistryNumber(registryNumber);
                    modifyingEmployer.setAddress(address);
                    return modifyingEmployer;
                }

                return new Employer(name, ownershipType, address, phone, registryNumber);
            }

            return null;
        });

        setOnShowing(dialogEvent -> Platform.runLater(() -> nameTextField.requestFocus()));
    }

    public EmployerDialog(Window owner, Employer employer) throws IOException {
        this(owner);

        modifyingEmployer = employer;

        nameTextField.setText(employer.getName());
        ownershipTypeComboBox.setValue(employer.getOwnershipType());
        addressTextField.setText(employer.getAddress());
        phoneTextField.setText(employer.getPhone());
        registryTextField.setText(employer.getRegistryNumber().toString());
    }
}
