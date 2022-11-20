package ru.nstu.ui;


import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import ru.nstu.entity.Client;
import ru.nstu.entity.Request;
import ru.nstu.repository.RequestRepo;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RequestViewDialog extends Stage {
    private final RequestRepo requestRepo = RequestRepo.getInstance();
    private final List<Request> requestList;

    private final Client selectedClient;

    @FXML
    private TableView<Request> requestTableView;

    public RequestViewDialog(Window owner, Client selectedClient) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("request-dialog.fxml"));
        fxmlLoader.setController(this);

        Scene scene = new Scene(fxmlLoader.load());

        initOwner(owner);
        initModality(Modality.APPLICATION_MODAL);

        this.selectedClient = selectedClient;

        setTitle("Просмотр счетов");
        setResizable(false);
        setScene(scene);


        var rootAccounts = this.selectedClient != null ? this.selectedClient.getRequests() : null;

        this.requestList = rootAccounts != null ? rootAccounts : new ArrayList<>();
        onRequestListChange();

        show();
    }

    private void onRequestListChange() {
        if (requestList == null) {
            requestTableView.setItems(FXCollections.observableList(Collections.emptyList()));
            return;
        }

        requestTableView.setItems(FXCollections.observableList(requestList));
        requestTableView.refresh();
    }

    @FXML
    protected void initialize() {
        TableInitializer.InitializeRequestTable(requestTableView);
    }

    @FXML
    public void onCreateRequest() throws IOException {
        onRequestListChange();
    }

    @FXML
    public void onEditAccount() throws IOException {
        var selectedAccount = requestTableView.getSelectionModel().getSelectedItem();

        requestRepo.edit(selectedAccount);

        onRequestListChange();
    }

    @FXML
    public void onDeleteAccount() {
        var selectedAccount = requestTableView.getSelectionModel().getSelectedItem();
        requestRepo.delete(selectedAccount);
        requestList.remove(selectedAccount);
        onRequestListChange();
    }
}
