package ru.nstu.ui;


import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import ru.nstu.entity.Employer;
import ru.nstu.entity.Offer;
import ru.nstu.repository.OfferRepo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OfferViewDialog extends Stage {
    private final OfferRepo offerRepo = OfferRepo.getInstance();
    private final List<Offer> offersList;

    private final Employer selectedEmployer;

    @FXML
    private TableView<Offer> offerTableView;

    public OfferViewDialog(Window owner, Employer selectedClient) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("offer-view-dialog.fxml"));
        fxmlLoader.setController(this);

        Scene scene = new Scene(fxmlLoader.load());

        initOwner(owner);
        initModality(Modality.APPLICATION_MODAL);

        this.selectedEmployer = selectedClient;

        setTitle("Просмотр счетов");
        setResizable(false);
        setScene(scene);


        var rootAccounts = this.selectedEmployer != null ? this.selectedEmployer.getOffers() : null;

        this.offersList = rootAccounts != null ? rootAccounts : new ArrayList<>();
        onOfferListChange();

        show();
    }

    private void onOfferListChange() {
        if (offersList == null) {
            offerTableView.setItems(FXCollections.observableList(Collections.emptyList()));
            return;
        }

        offerTableView.setItems(FXCollections.observableList(offersList));
        offerTableView.refresh();
    }

    @FXML
    protected void initialize() {
        TableInitializer.InitializeOfferTable(offerTableView);
    }

    @FXML
    public void onCreateOffer() throws IOException {
        var requestModify = new OfferModifyDialog(getOwner(), selectedEmployer);

        requestModify.showAndWait().ifPresent(request -> {
            offerRepo.save(request);
            offersList.add(request);
        });

        onOfferListChange();
    }

    @FXML
    public void onEditOffer() throws IOException {
        var selectedOffer = offerTableView.getSelectionModel().getSelectedItem();

        var requestModify = new OfferModifyDialog(getOwner(), selectedEmployer, selectedOffer);

        requestModify.showAndWait().ifPresent(offer -> {
            var selectedIndex = offersList.indexOf(selectedOffer);

            offerRepo.edit(offer);
            offersList.set(selectedIndex, offer);
        });

        offerRepo.edit(selectedOffer);

        onOfferListChange();
    }

    @FXML
    public void onDeleteOffer() {
        var selectedOffer = offerTableView.getSelectionModel().getSelectedItem();
        offerRepo.delete(selectedOffer);
        offersList.remove(selectedOffer);
        onOfferListChange();
    }
}
