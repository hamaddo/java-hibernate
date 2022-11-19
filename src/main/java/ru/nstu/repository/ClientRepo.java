package ru.nstu.repository;

import ru.nstu.entity.Client;
import ru.nstu.util.Gender;

import java.util.List;

public class ClientRepo extends BaseRepo<Client, Long> {

    @Override
    protected Class<Client> getType() {
        return Client.class;
    }

    final static ClientRepo instance = new ClientRepo();

    @Override
    protected String getTableName() {
        return "Client";
    }

    public static ClientRepo getInstance() {
        return instance;
    }

    public List<Client> findAllByName(String fullName) {
        return this.findByStringField("fullName", fullName);
    }

    public List<Client> findAllByPhone(String phone) {
        return this.findByStringField("phone", phone);
    }

    public List<Client> findAllByGender(String gender) {
        int numericGender = Gender.valueOf(gender.toUpperCase()).ordinal();
        String stringOrdinal = Integer.toString(numericGender);

        return this.findByStringField("gender", stringOrdinal);
    }
}
