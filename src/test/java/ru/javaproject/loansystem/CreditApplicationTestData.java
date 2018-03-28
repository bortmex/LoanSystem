package ru.javaproject.loansystem;

import ru.javaproject.loansystem.matcher.ModelMatcher;
import ru.javaproject.loansystem.model.CreditApplication;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static ru.javaproject.loansystem.model.BaseEntity.START_SEQ;

public class CreditApplicationTestData {

    public static final int CREDIT_APPLICATION1_ID = START_SEQ + 14;
    public static final int CREDIT_APPLICATION2_ID = START_SEQ + 15;
    public static final int CREDIT_APPLICATION3_ID = START_SEQ + 16;

    public static final ModelMatcher<CreditApplication> MATCHERCREDITAPPLICATION = new ModelMatcher<>(
            (expected, actual) -> expected == actual ||
            (Objects.equals(expected.getId(), actual.getId())
            && Objects.equals(expected.getFio(), actual.getFio())
            && Objects.equals(expected.getDateBirth(), actual.getDateBirth())
            && Objects.equals(expected.getDateTimeCreate(), actual.getDateTimeCreate())
                    && Objects.equals(expected.getPhoneNumber(), actual.getPhoneNumber())
                    && Objects.equals(expected.getAnInitialFee(), actual.getAnInitialFee())
            )
            );

    public static final CreditApplication CREDIT_APPLICATION1 = new CreditApplication(CREDIT_APPLICATION1_ID,
                                                                                      "Вася Громов", LocalDate.of(2015, 5, 31),
                                                                                      LocalDateTime.of(LocalDate.of(2010, 7, 11),
                                                                                      LocalTime.of(15, 4 , 0, 0)),
                                                                                      "89104567896", 10000);
    public static final CreditApplication CREDIT_APPLICATION2 = new CreditApplication(CREDIT_APPLICATION2_ID,
                                                                                      "Вася Громов", LocalDate.of(1975, 6, 1),
                                                                                      LocalDateTime.of(LocalDate.of(2010, 7, 11),
                                                                                      LocalTime.of(15, 4 , 0, 0)),
                                                                                      "89112312316", 10012);
    public static final CreditApplication CREDIT_APPLICATION3 = new CreditApplication(CREDIT_APPLICATION3_ID,
                                                                                      "Вася1 Громов1", LocalDate.of(2000, 6, 1),
                                                                                      LocalDateTime.of(LocalDate.of(2010, 1, 11),
                                                                                      LocalTime.of(15, 4 , 0, 0)),
                                                                                      "'89112312326'", 10012);

    public static final List<CreditApplication> CREDIT_APPLICATION_LIST_USER = Arrays.asList(CREDIT_APPLICATION1, CREDIT_APPLICATION2);

    public static CreditApplication getCreatedCA() {
        return CREDIT_APPLICATION1;
    }

    public static CreditApplication getUpdatedCA() {
        return new CreditApplication(CREDIT_APPLICATION2_ID, "Новые ФИО", CREDIT_APPLICATION2.getDateBirth(),
                                     CREDIT_APPLICATION2.getDateTimeCreate(), CREDIT_APPLICATION2.getPhoneNumber(), CREDIT_APPLICATION2.getAnInitialFee());
    }

}
