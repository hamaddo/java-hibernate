package ru.nstu.repository;

import ru.nstu.entity.Request;


public class RequestRepo extends BaseRepo<Request, Long> {

    @Override
    protected Class<Request> getType() {
        return Request.class;
    }

    final static RequestRepo instance = new RequestRepo();

    @Override
    protected String getTableName() {
        return "Request";
    }

    public static RequestRepo getInstance() {
        return instance;
    }
}
