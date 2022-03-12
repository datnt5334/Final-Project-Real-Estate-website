package vn.edu.hust.samiestate.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "transaction_type")
public class TransactionTypeEntity extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @OneToMany(mappedBy="transactionType", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REMOVE}, orphanRemoval = true)
    private List<TransactionEntity> transactionEntities = new ArrayList<>();

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

    public List<TransactionEntity> getTransactionEntities() {
        return transactionEntities;
    }

    public void setTransactionEntities(List<TransactionEntity> transactionEntities) {
        this.transactionEntities = transactionEntities;
    }
}
