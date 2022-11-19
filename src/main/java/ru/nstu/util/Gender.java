package ru.nstu.util;

public enum Gender {
    MALE("male"),
    FEMALE("female");

    private String name;

    private Gender(String theType) {
        this.name = theType;
    }
}