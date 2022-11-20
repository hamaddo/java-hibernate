package ru.nstu.repository;

import ru.nstu.entity.Offer;


public class OfferRepo extends BaseRepo<Offer, Long> {

    @Override
    protected Class<Offer> getType() {
        return Offer.class;
    }

    final static OfferRepo instance = new OfferRepo();

    @Override
    protected String getTableName() {
        return "Offer";
    }

    public static OfferRepo getInstance() {
        return instance;
    }
}
