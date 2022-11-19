package ru.nstu.util;

public enum OwnershipType {
    OOO("ООО"),
    IP("ИП"),
    ZAO("ЗАО");
    private String name;

    private OwnershipType(String theType) {
        this.name = theType;
    }
}