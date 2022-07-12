package vn.edu.hust.samiestate.entity;

import vn.edu.hust.samiestate.enums.CustomerStatus;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer")
public class CustomerEntity extends BaseEntity{

    @Column(name = "fullname")
    private String fullName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "company")
    private String company;

    @Column(name = "note", columnDefinition="TEXT")
    private String note;

    @Column(name = "demand")
    private String demand;

    @Column(name = "statuscode", nullable = false)
    @Enumerated(EnumType.STRING)
    private CustomerStatus statusCode;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "assignmentcustomer",
            joinColumns = @JoinColumn(name = "customer_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "staff_id", nullable = false))
    private List<UserEntity> users = new ArrayList<>();

    @OneToMany(mappedBy="customer", fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE})
    private List<CustomerTransactionEntity> transactionEntities = new ArrayList<>();

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDemand() {
        return demand;
    }

    public void setDemand(String demand) {
        this.demand = demand;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }

    public List<CustomerTransactionEntity> getTransactionEntities() {
        return transactionEntities;
    }

    public void setTransactionEntities(List<CustomerTransactionEntity> transactionEntities) {
        this.transactionEntities = transactionEntities;
    }

    public CustomerStatus getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(CustomerStatus statusCode) {
        this.statusCode = statusCode;
    }
}
