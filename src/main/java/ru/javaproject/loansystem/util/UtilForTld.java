package ru.javaproject.loansystem.util;

import java.util.Collection;

public class UtilForTld {
    public static boolean contains(Collection<?> coll, Object o) {
        if (coll == null) return false;
        return coll.contains(o);
    }
}
