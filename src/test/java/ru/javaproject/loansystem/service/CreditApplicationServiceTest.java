package ru.javaproject.loansystem.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javaproject.loansystem.model.CreditApplication;
import ru.javaproject.loansystem.util.exception.NotFoundException;

import javax.validation.ConstraintViolationException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

import static ru.javaproject.loansystem.CreditApplicationTestData.*;
import static ru.javaproject.loansystem.UserTestData.USER1_ID;
import static ru.javaproject.loansystem.UserTestData.USER_ID;

public class CreditApplicationServiceTest extends AbstractServiceTest{

    @Autowired
    private CreditApplicationService credappservice;

    @Test
    public void testDeleteCreditApplication() throws Exception {
        credappservice.delete(CREDIT_APPLICATION1_ID, USER_ID);
        MATCHERCREDITAPPLICATION.assertCollectionEquals(Arrays.asList(CREDIT_APPLICATION2), credappservice.getAllForUsersId(USER_ID));
    }

    @Test
    public void testDeleteNotFoundCreditApplication() throws Exception {
        thrown.expect(NotFoundException.class);
        credappservice.delete(CREDIT_APPLICATION1_ID, 1);
    }

    @Test
    public void testSaveCreditApplication() throws Exception {
        CreditApplication created = getCreatedCA();
        credappservice.save(created, USER_ID);
        MATCHERCREDITAPPLICATION.assertCollectionEquals(Arrays.asList(created, CREDIT_APPLICATION2), credappservice.getAllForUsersId(USER_ID));
    }

    @Test
    public void testGetCreditApplication() throws Exception {
        CreditApplication actual = credappservice.get(CREDIT_APPLICATION1_ID, USER_ID);
        MATCHERCREDITAPPLICATION.assertEquals(CREDIT_APPLICATION1, actual);
    }

    @Test
    public void testGetNotFoundCreditApplication() throws Exception {
        thrown.expect(NotFoundException.class);
        credappservice.get(CREDIT_APPLICATION1_ID, USER1_ID);
    }

    @Test
    public void testUpdateCreditApplication() throws Exception {
        CreditApplication updated = getUpdatedCA();
        credappservice.update(updated, USER_ID);
        MATCHERCREDITAPPLICATION.assertEquals(updated, credappservice.get(CREDIT_APPLICATION2_ID, USER_ID));
    }

    @Test
    public void testUpdateNotFoundCreditApplication() throws Exception {
        thrown.expect(NotFoundException.class);
        credappservice.update(CREDIT_APPLICATION3, USER_ID);
    }

    @Test
    public void testGetAllCreditApplication() throws Exception {
        MATCHERCREDITAPPLICATION.assertCollectionEquals(CREDIT_APPLICATION_LIST_USER, credappservice.getAllForUsersId(USER_ID));
    }

    @Test
    public void testValidation() throws Exception {
        validateRootCause(() -> credappservice.save(new CreditApplication(" ", LocalDate.now(), LocalDateTime.now(), "891032132212", 11), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> credappservice.save(new CreditApplication("FIO", null, LocalDateTime.now(), "891032132212", 11), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> credappservice.save(new CreditApplication("FIO", LocalDate.now(), null, "891032132212", 11), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> credappservice.save(new CreditApplication("FIO", LocalDate.now(), LocalDateTime.now(), " ", 11), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> credappservice.save(new CreditApplication("FIO", LocalDate.now(), LocalDateTime.now(), "891032132212", 9), USER_ID), ConstraintViolationException.class);
        validateRootCause(() -> credappservice.save(new CreditApplication("FIO", LocalDate.now(), LocalDateTime.now(), "891032132212", 10000001), USER_ID), ConstraintViolationException.class);
    }
}
