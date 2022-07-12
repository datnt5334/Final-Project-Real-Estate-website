package vn.edu.hust.samiestate.entity;

import javax.persistence.*;

@Entity
@Table(name = "customertransaction")
public class CustomerTransactionEntity extends BaseEntity {

    @Column(name = "note")
    private String note;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="customer_id", nullable=false)
    private CustomerEntity customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="transaction_type_id", nullable=false)
    private CustomerTransactionTypeEntity transactionType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="staff_id", nullable=false)
    private UserEntity user;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public CustomerTransactionTypeEntity getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(CustomerTransactionTypeEntity transactionType) {
        this.transactionType = transactionType;
    }
}
