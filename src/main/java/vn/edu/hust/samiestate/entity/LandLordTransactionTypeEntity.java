package vn.edu.hust.samiestate.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "landlordtransactiontype")
public class LandLordTransactionTypeEntity extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @OneToMany(mappedBy="transactionType", fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE})
    private List<LandLordTransactionEntity> landLordTransactionEntities = new ArrayList<>();

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

    public List<LandLordTransactionEntity> getLandLordTransactionEntities() {
        return landLordTransactionEntities;
    }

    public void setLandLordTransactionEntities(List<LandLordTransactionEntity> landLordTransactionEntities) {
        this.landLordTransactionEntities = landLordTransactionEntities;
    }
}
