package ru.javaproject.loansystem.model;

import javax.persistence.*;

@SuppressWarnings("JpaQlInspection")
@NamedQueries({
        @NamedQuery(name = CreditApplicationListProduct.ALL_SORTED_WITH_CREDAPPID, query = "SELECT ca FROM CreditApplicationListProduct ca where ca.credit_application_id = ?1"),
        @NamedQuery(name = CreditApplicationListProduct.ALL, query = "SELECT ca FROM CreditApplicationListProduct ca"),
        @NamedQuery(name = CreditApplicationListProduct.DELETE, query = "DELETE FROM CreditApplicationListProduct ca WHERE ca.credit_application_id=:id")
})


@Entity
@Table(name = "credit_application_list_product")
public class CreditApplicationListProduct extends BaseEntity {

    public static final String ALL_SORTED_WITH_CREDAPPID = "CreditApplicationListProduct.getAllSortedWithCredAppId_CREDAPPID";
    public static final String ALL = "CreditApplicationListProduct.getAllSorted";
    public static final String DELETE = "CreditApplicationListProduct.delete";

    public CreditApplicationListProduct() {
    }

    @Column(name = "cred_app_id")
    private int credit_application_id;

    @Column(name = "productl_id")
    private int productl_id;

    public CreditApplicationListProduct(int credit_application_id, int productl_id) {
        this(null, credit_application_id, productl_id);
    }

    public CreditApplicationListProduct(Integer id, int credit_application_id, int productl_id) {
        super(id);
        this.credit_application_id = credit_application_id;
        this.productl_id = productl_id;
    }

    public int getCredit_application_id() {
        return credit_application_id;
    }

    public void setCredit_application_id(int credit_application_id) {
        this.credit_application_id = credit_application_id;
    }

    public int getProductl_id() {
        return productl_id;
    }

    public void setProductl_id(int productl_id) {
        this.productl_id = productl_id;
    }

    @Override
    public String toString() {
        return "CreditApplicationListProduct{" +
                ", credit_application_id=" + credit_application_id +
                ", productl_id=" + productl_id +
                '}';
    }
}
