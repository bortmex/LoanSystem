package ru.javaproject.loansystem.model;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("JpaQlInspection")
@NamedQueries({
        @NamedQuery(name = CreditApplication.ALL_WHERE_USER_ID, query = "SELECT ca FROM CreditApplication ca where ca.user.id = ?1 ORDER BY ca.id"),
        @NamedQuery(name = CreditApplication.ALL, query = "SELECT ca FROM CreditApplication ca ORDER BY ca.id"),
        @NamedQuery(name = CreditApplication.UPDATEWITHUSERID, query = "UPDATE CreditApplication ca SET ca.fio=:fio, ca.dateBirth=:dateBirth, " +
                "ca.dateTimeCreate=:dateTimeCreate, ca.phoneNumber=:phoneNumber, ca.anInitialFee=:anInitialFee WHERE ca.id=:id and ca.user.id=?1"),
        @NamedQuery(name = CreditApplication.UPDATE, query = "UPDATE CreditApplication ca SET ca.fio=:fio, ca.dateBirth=:dateBirth, " +
                "ca.dateTimeCreate=:dateTimeCreate, ca.phoneNumber=:phoneNumber, ca.anInitialFee=:anInitialFee WHERE ca.id=:id"),
        @NamedQuery(name = CreditApplication.DELETEWITHOUTUSERID, query = "DELETE FROM CreditApplication ca WHERE ca.id=:id"),
        @NamedQuery(name = CreditApplication.DELETE, query = "DELETE FROM CreditApplication ca WHERE ca.id=:id and ca.user.id=?1"),

})

@Entity
@Access(AccessType.FIELD)
@Table(name = "credit_application")
public class CreditApplication extends BaseEntity {

    public static final String ALL_WHERE_USER_ID = "CreditApplication.getAllWhereUserId";
    public static final String ALL = "CreditApplication.getAll";
    public static final String DELETEWITHOUTUSERID = "CreditApplication.delete.with.out.userid";
    public static final String DELETE = "CreditApplication.delete";
    public static final String UPDATEWITHUSERID = "CreditApplication.select.with.user.id";
    public static final String UPDATE = "CreditApplication.select";

    public CreditApplication(){

    }

    public CreditApplication(String fio, LocalDate dateBirth,
                             LocalDateTime dateTimeCreate, String phoneNumber, Integer anInitialFee){
        this(null, fio, dateBirth, dateTimeCreate, phoneNumber, anInitialFee);
    }

    public CreditApplication(Integer id, String fio, LocalDate dateBirth,
                             LocalDateTime dateTimeCreate, String phoneNumber, Integer anInitialFee) {
        super(id);
        this.fio = fio;
        this.dateBirth = dateBirth;
        this.dateTimeCreate = dateTimeCreate;
        this.phoneNumber = phoneNumber;
        this.productList = new ArrayList<>();
        this.anInitialFee = anInitialFee;
        this.statusOfApplicationParner = null;
        this.statusOfApplicationRepresentative = null;
    }

    @Column(name = "fio")
    @NotBlank
    private String fio;

    @Column(name = "date_birth")
    @NotNull
    private LocalDate dateBirth;

    @Column(name = "date_time_create")
    @NotNull
    private LocalDateTime dateTimeCreate; //дата создания анкеты

    @Column(name = "phone_number")
    @NotBlank
    private String phoneNumber;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "credit_application_list_product",
            joinColumns = { @JoinColumn(name = "cred_app_id" , nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "productl_id" , nullable = false, updatable = false) }
    )
    private List<Product> productList = new ArrayList<>();

    @Column(name = "an_initial_fee")
    @Range(min = 10, max = 10000000)
    private Integer anInitialFee; //первоначальный взнос

    @Column(name = "status_of_application_parner")
    private Boolean statusOfApplicationParner;

    @Column(name = "status_of_application_representative")
    private Boolean statusOfApplicationRepresentative;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="userid")
    private User user;

    public void addProduct(Product product){
        this.productList.add(product);
    }

    public void removeProduct(Product product){
        this.productList.add(product);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
        return productList;
    }

    public void setProduct(List<Product> productList) {
        this.productList = productList;
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
                ", fio='" + fio + '\'' +
                ", dateBirth=" + dateBirth +
                ", dateTimeCreate=" + dateTimeCreate +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", product=" + productList +
                ", anInitialFee=" + anInitialFee +
                ", statusOfApplicationParner=" + statusOfApplicationParner +
                ", statusOfApplicationRepresentative=" + statusOfApplicationRepresentative +
                ", user=" + user +
                '}';
    }
}
