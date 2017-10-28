package ru.javaproject.util;

import ru.javaproject.model.Product;

import java.util.Arrays;
import java.util.List;

public class ProductUtil {
    public static final List<Product> PRODUCTS = Arrays.asList(new Product("ferrari 458", 1000000, 1),
    new Product("Носки", 1000, 1),
    new Product("Компьютер MicroXperts [G120-04]", 47480, 1),
    new Product("Горные лыжи Vist Carbon Classic Black", 174000, 1),new Product("MAZDA 458", 100000, 2),
    new Product("Стул", 5000, 2),
    new Product("Кофемашина MicroXperts [G120-04]", 1480, 2));
}
