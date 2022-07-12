package vn.edu.hust.samiestate.entity;

import javax.persistence.*;

@Entity
@Table(name = "landlordtransaction")
public class LandLordTransactionEntity extends BaseEntity {

    @Column(name = "note")
    private String note;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="land_lord_id", nullable=false)
    private LandLordEntity landLord;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="transaction_type_id", nullable=false)
    private LandLordTransactionTypeEntity transactionType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="staff_id", nullable=false)
    private UserEntity user;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public LandLordEntity getLandLord() {
        return landLord;
    }

    public void setLandLord(LandLordEntity landLord) {
        this.landLord = landLord;
    }

    public LandLordTransactionTypeEntity getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(LandLordTransactionTypeEntity transactionType) {
        this.transactionType = transactionType;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }


}
