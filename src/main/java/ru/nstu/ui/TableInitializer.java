package ru.nstu.ui;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ru.nstu.entity.Client;
import ru.nstu.entity.Employer;
import ru.nstu.entity.Offer;
import ru.nstu.entity.Request;
import ru.nstu.util.Gender;

public class TableInitializer {
    public static void InitializeClientTable(TableView<Client> tableView) {
        TableColumn<Client, String> nameColumn = new TableColumn<>("Имя");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableView.getColumns().add(nameColumn);

        TableColumn<Client, String> surnameColumn = new TableColumn<>("Фамилия");
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        tableView.getColumns().add(surnameColumn);

        TableColumn<Client, String> patronymicColumn = new TableColumn<>("Отчество");
        patronymicColumn.setCellValueFactory(new PropertyValueFactory<>("patronymic"));
        tableView.getColumns().add(patronymicColumn);

        TableColumn<Client, String> addressColumn = new TableColumn<>("Адрес");
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        tableView.getColumns().add(addressColumn);

        TableColumn<Client, String> phoneColumn = new TableColumn<>("Телефон");
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        tableView.getColumns().add(phoneColumn);

        TableColumn<Client, Gender> genderColumn = new TableColumn<>("Гендер");
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        tableView.getColumns().add(genderColumn);

        TableColumn<Client, Integer> requestColumn = new TableColumn<>("Кол-во заявок");
        requestColumn.setCellValueFactory(cellDataFeatures -> new SimpleIntegerProperty(cellDataFeatures.getValue().getRequests().size()).asObject());
        tableView.getColumns().add(requestColumn);

    }

    public static void InitializeEmployerTable(TableView<Employer> tableView) {
        TableColumn<Employer, String> nameColumn = new TableColumn<>("Имя");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableView.getColumns().add(nameColumn);

        TableColumn<Employer, String> ownershipColumn = new TableColumn<>("Форма собственности");
        ownershipColumn.setCellValueFactory(new PropertyValueFactory<>("ownershipType"));
        tableView.getColumns().add(ownershipColumn);

        TableColumn<Employer, String> addressColumn = new TableColumn<>("Адрес");
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        tableView.getColumns().add(addressColumn);

        TableColumn<Employer, String> phoneColumn = new TableColumn<>("Телефон");
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        tableView.getColumns().add(phoneColumn);

        TableColumn<Employer, Gender> registryColumn = new TableColumn<>("Регистрационный номер");
        registryColumn.setCellValueFactory(new PropertyValueFactory<>("registryNumber"));
        tableView.getColumns().add(registryColumn);


        TableColumn<Employer, Integer> offerColumn = new TableColumn<>("Кол-во предложений");
        offerColumn.setCellValueFactory(cellDataFeatures -> new SimpleIntegerProperty(cellDataFeatures.getValue().getOffers().size()).asObject());
        tableView.getColumns().add(offerColumn);

    }

    public static void InitializeRequestTable(TableView<Request> requestTableView) {
        TableColumn<Request, String> positionColumn = new TableColumn<>("Название позиции");
        positionColumn.setCellValueFactory(new PropertyValueFactory<>("positionName"));
        requestTableView.getColumns().add(positionColumn);

        TableColumn<Request, Integer> salaryColumn = new TableColumn<>("Зарплатные ожидания");
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));
        requestTableView.getColumns().add(salaryColumn);
    }

    public static void InitializeOfferTable(TableView<Offer> requestTableView) {
        TableColumn<Offer, String> positionColumn = new TableColumn<>("Название позиции");
        positionColumn.setCellValueFactory(new PropertyValueFactory<>("positionName"));
        requestTableView.getColumns().add(positionColumn);

        TableColumn<Offer, Integer> salaryColumn = new TableColumn<>("Зарплатные ожидания");
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));
        requestTableView.getColumns().add(salaryColumn);

        TableColumn<Offer, Gender> genderColumn = new TableColumn<>("Гендер");
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        requestTableView.getColumns().add(genderColumn);
    }
}
