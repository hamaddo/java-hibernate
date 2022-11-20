package ru.nstu.ui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Window;
import ru.nstu.entity.Employer;
import ru.nstu.entity.Offer;
import ru.nstu.entity.Request;
import ru.nstu.util.Gender;

import java.io.IOException;

public class OfferModifyDialog extends Dialog<Offer> {
    private Offer modifyingOffer;

    private final Employer relatedEmployer;

    @FXML
    public TextField positionNameTextField;
    @FXML
    public TextField salaryTextField;

    @FXML
    public ComboBox<Gender> genderComboBox;

    public OfferModifyDialog(Window owner, Employer employer) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("offer-modify-dialog.fxml"));
        fxmlLoader.setController(this);

        DialogPane dialogPane = fxmlLoader.load();

        initOwner(owner);
        initModality(Modality.APPLICATION_MODAL);

        setTitle("Создать клиента");
        setResizable(false);
        setDialogPane(dialogPane);

        relatedEmployer = employer;

        setResultConverter(buttonType -> {
            if (buttonType != null && buttonType.getButtonData() == ButtonBar.ButtonData.APPLY && relatedEmployer != null) {
                var positionName = positionNameTextField.getText();
                var salary = Integer.parseInt(salaryTextField.getText());
                var gender = genderComboBox.getValue();

                if (modifyingOffer != null) {
                    modifyingOffer.setPositionName(positionName);
                    modifyingOffer.setSalary(salary);
                    return modifyingOffer;
                }

                return new Offer(positionName, salary, gender, relatedEmployer);
            }

            return null;
        });

        setOnShowing(dialogEvent -> Platform.runLater(() -> positionNameTextField.requestFocus()));
    }

    public OfferModifyDialog(Window owner, Employer employer, Offer offer) throws IOException {
        this(owner, employer);

        modifyingOffer = offer;
        positionNameTextField.setText(offer.getPositionName());
        salaryTextField.setText(offer.getSalary().toString());
        genderComboBox.setValue(offer.getGender());
    }
}
