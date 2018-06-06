package ru.javaproject.loansystem.to;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.LocalDate;

public class CreditApplicationTo extends BaseTo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Length.List({
            @Length(min = 10, message = "The field must be at least 10 characters"),
            @Length(max = 30, message = "The field must be less than 18 characters")
    })
    @NotBlank
    private String fio;
    @NotNull
    private @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateBirth;
    @NotBlank
    @Pattern(regexp="(^[89]+[0-9]{9}$)")
    private String phoneNumber;
    @NotNull
    @Range(min = 10, max = 10000000)
    private Integer anInitialFee;
    @NotBlank
    private String listproductid;

    public CreditApplicationTo() {
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public LocalDate getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(LocalDate dateBirth) {
        this.dateBirth = dateBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getAnInitialFee() {
        return anInitialFee;
    }

    public void setAnInitialFee(Integer anInitialFee) {
        this.anInitialFee = anInitialFee;
    }

    public String getListproductid() {
        return listproductid;
    }

    public void setListproductid(String listproductid) {
        this.listproductid = listproductid;
    }

    public boolean isNew() {
        return fio == null;
    }

    @Override
    public String toString() {
        return "CreditApplicationTo{" +
                "fio='" + fio + '\'' +
                ", date=" + dateBirth +
                ", phonenumber='" + phoneNumber + '\'' +
                ", aninitialfee=" + anInitialFee +
                ", listproductid='" + listproductid + '\'' +
                '}';
    }
}
