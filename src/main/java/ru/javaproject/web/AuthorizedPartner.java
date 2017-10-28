package ru.javaproject.web;

public class AuthorizedPartner {
    private static int id = 1;

    public static int id() {
        return id;
    }

    public static void setId(int id){
        AuthorizedPartner.id = id;
    }
}
