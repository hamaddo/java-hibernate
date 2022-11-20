package ru.nstu.repository;
import ru.nstu.entity.Request;

import java.util.List;


public class RequestRepo extends BaseRepo<Request, Long> {

    @Override
    protected Class<Request> getType() {
        return Request.class;
    }

    final static RequestRepo instance = new RequestRepo();

    @Override
    protected String getTableName() {
        return "Client";
    }

    public static RequestRepo getInstance() {
        return instance;
    }

    public List<Request> findAllByName(String fullName) {
        return this.findByStringField("fullName", fullName);
    }

    public List<Request> findAllByPhone(String phone) {
        return this.findByStringField("phone", phone);
    }
}
