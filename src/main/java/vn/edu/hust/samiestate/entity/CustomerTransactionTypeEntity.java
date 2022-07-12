package vn.edu.hust.samiestate.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customertransactiontype")
public class CustomerTransactionTypeEntity extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @OneToMany(mappedBy="transactionType", fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE})
    private List<CustomerTransactionEntity> customerTransactionEntities = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<CustomerTransactionEntity> getCustomerTransactionEntities() {
        return customerTransactionEntities;
    }

    public void setCustomerTransactionEntities(List<CustomerTransactionEntity> customerTransactionEntities) {
        this.customerTransactionEntities = customerTransactionEntities;
    }
}
