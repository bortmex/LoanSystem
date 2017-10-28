package ru.javaproject.util;

import ru.javaproject.model.Partner;

import java.util.Arrays;
import java.util.List;

public class PartnerUtil {
    public static final List<Partner> PARTNERS = Arrays.asList(
                new Partner(1,"Партнер1","email1","password1"),
                new Partner(2,"Партнер2","email2","password2")
    );
}
