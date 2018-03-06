package ru.javaproject.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NamedQueries({
        @NamedQuery(name = CreditApplication.ALL_SORTED, query = "SELECT ca FROM CreditApplication ca where ca.userId = ?1")
})

@Entity
@Table(name = "credit_application")
public class CreditApplication extends BaseEntity{

    public static final String ALL_SORTED = "CreditApplication.getAllSorted";

    public CreditApplication(){

    }

    public CreditApplication(Integer id, int userId, String fio, LocalDateTime dateBirth,
                             LocalDateTime dateTimeCreate, String phoneNumber, Integer anInitialFee) {
        super(id);
        this.userId = userId;
        this.fio = fio;
        this.dateBirth = dateBirth;
        this.dateTimeCreate = dateTimeCreate;
        this.phoneNumber = phoneNumber;
        this.product = new ArrayList<>();
        this.anInitialFee = anInitialFee;
        this.statusOfApplicationParner = null;
        this.statusOfApplicationRepresentative = null;
    }

    @Column(name = "userid")
    private int userId;

    @Column(name = "fio")
    private String fio;

    @Column(name = "date_birth")
    private LocalDateTime dateBirth;

    @Column(name = "date_time_create")
    private LocalDateTime dateTimeCreate; //дата создания анкеты

    @Column(name = "phone_number")
    private String phoneNumber;

    /*@Column(name = "product_list")
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Product> product;*/

    @CollectionTable(name = "products", joinColumns = @JoinColumn(name = "id"))
    @ElementCollection
    private List<Product> product;

    @Column(name = "an_initial_fee")
    private Integer anInitialFee; //первоначальный взнос

    @Column(name = "status_of_application_parner")
    private Boolean statusOfApplicationParner;

    @Column(name = "status_of_application_representative")
    private Boolean statusOfApplicationRepresentative;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public LocalDateTime getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(LocalDateTime dateBirth) {
        this.dateBirth = dateBirth;
    }

    public LocalDateTime getDateTimeCreate() {
        return dateTimeCreate;
    }

    public void setDateTimeCreate(LocalDateTime dateTimeCreate) {
        this.dateTimeCreate = dateTimeCreate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }

    public Integer getAnInitialFee() {
        return anInitialFee;
    }

    public void setAnInitialFee(Integer anInitialFee) {
        this.anInitialFee = anInitialFee;
    }

    public Boolean getStatusOfApplicationParner() {
        return statusOfApplicationParner;
    }

    public void setStatusOfApplicationParner(Boolean statusOfApplicationParner) {
        this.statusOfApplicationParner = statusOfApplicationParner;
    }

    public Boolean getStatusOfApplicationRepresentative() {
        return statusOfApplicationRepresentative;
    }

    public void setStatusOfApplicationRepresentative(Boolean statusOfApplicationRepresentative) {
        this.statusOfApplicationRepresentative = statusOfApplicationRepresentative;
    }

    @Override
    public String toString() {
        return "CreditApplication{" +
                "userId=" + userId +
                ", fio='" + fio + '\'' +
                ", dateBirth=" + dateBirth +
                ", dateTimeCreate=" + dateTimeCreate +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", product=" + product +
                ", anInitialFee=" + anInitialFee +
                ", statusOfApplicationParner=" + statusOfApplicationParner +
                ", statusOfApplicationRepresentative=" + statusOfApplicationRepresentative +
                '}';
    }
}
