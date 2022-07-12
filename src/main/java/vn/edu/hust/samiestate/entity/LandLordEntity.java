package vn.edu.hust.samiestate.entity;

import vn.edu.hust.samiestate.enums.LandLordStatus;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "landlord")
public class LandLordEntity extends BaseEntity {

    @Column(name = "fullname", nullable = false)
    private String fullName;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "company", nullable = false)
    private String company;

    @Column(name = "note", columnDefinition="TEXT")
    private String note;

    @Column(name = "statuscode", nullable = false)
    @Enumerated(EnumType.STRING)
    private LandLordStatus statusCode;

    @OneToMany(mappedBy="landLord", fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE})
    private List<BuildingEntity> buildingEntities = new ArrayList<>();

    @OneToMany(mappedBy="landLord", fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE})
    private List<LandLordTransactionEntity> transactionEntities = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "assignmentlandlord",
            joinColumns = @JoinColumn(name = "landlord_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "staff_id", nullable = false))
    private List<UserEntity> users = new ArrayList<>();

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

    public List<BuildingEntity> getBuildingEntities() {
        return buildingEntities;
    }

    public void setBuildingEntities(List<BuildingEntity> buildingEntities) {
        this.buildingEntities = buildingEntities;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public LandLordStatus getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(LandLordStatus statusCode) {
        this.statusCode = statusCode;
    }

    public List<LandLordTransactionEntity> getTransactionEntities() {
        return transactionEntities;
    }

    public void setTransactionEntities(List<LandLordTransactionEntity> transactionEntities) {
        this.transactionEntities = transactionEntities;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }
}
