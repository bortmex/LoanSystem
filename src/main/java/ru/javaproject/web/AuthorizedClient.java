package ru.javaproject.web;

public class AuthorizedClient {

    private static int id = 1;

    public static int id() {
        return id;
    }

    public static void setId(int id){
        AuthorizedClient.id = id;
    }
}
