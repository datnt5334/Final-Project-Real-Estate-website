package vn.edu.hust.samiestate.entity;

import javax.persistence.*;

@Entity
@Table(name = "transaction")
public class TransactionEntity extends BaseEntity {

    @Column(name = "note")
    private String note;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="customer_id", nullable=false)
    private CustomerEntity customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="transaction_type_id", nullable=false)
    private TransactionTypeEntity transactionType;

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

    public TransactionTypeEntity getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionTypeEntity transactionType) {
        this.transactionType = transactionType;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
