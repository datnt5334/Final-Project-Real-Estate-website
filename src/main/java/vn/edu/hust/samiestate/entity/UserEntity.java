package vn.edu.hust.samiestate.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

    private static final long serialVersionUID = -4988455421375043688L;

    @Column(name = "username", nullable = false, unique = true)
    private String userName;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "employeecode", nullable = false)
    private String employeeCode;

    @Column(name = "status", nullable = false)
    private Integer status;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "fullname")
    private String fullName;

    @Column(name = "dayofbirth")
    private LocalDate dayOfBirth;

    @Lob
    @Column(name = "profilepicture")
    private String profilepicture;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "role_id", nullable = false))
    private List<RoleEntity> roles = new ArrayList<>();

    @ManyToMany(mappedBy = "users", fetch = FetchType.LAZY)
    private List<BuildingEntity> buildings = new ArrayList<>();

    @ManyToMany(mappedBy = "users", fetch = FetchType.LAZY)
    private List<CustomerEntity> customers = new ArrayList<>();

    @ManyToMany(mappedBy = "users", fetch = FetchType.LAZY)
    private List<LandLordEntity> landLords = new ArrayList<>();

    @OneToMany(mappedBy="user", fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE})
    private List<CustomerTransactionEntity> transactionEntities = new ArrayList<>();

    @OneToMany(mappedBy="user", fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE})
    private List<LandLordTransactionEntity> landLordTransactionEntities = new ArrayList<>();

    @OneToMany(mappedBy="user", fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE})
    private List<NewsEntity> newsEntities = new ArrayList<>();

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getProfilepicture() {
        return profilepicture;
    }

    public void setProfilepicture(String profilepicture) {
        this.profilepicture = profilepicture;
    }

    public List<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleEntity> roles) {
        this.roles = roles;
    }

    public List<BuildingEntity> getBuildings() {
        return buildings;
    }

    public void setBuildings(List<BuildingEntity> buildings) {
        this.buildings = buildings;
    }

    public List<CustomerEntity> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerEntity> customers) {
        this.customers = customers;
    }

    public List<CustomerTransactionEntity> getTransactionEntities() {
        return transactionEntities;
    }

    public void setTransactionEntities(List<CustomerTransactionEntity> transactionEntities) {
        this.transactionEntities = transactionEntities;
    }

    public List<NewsEntity> getNewsEntities() {
        return newsEntities;
    }

    public void setNewsEntities(List<NewsEntity> newsEntities) {
        this.newsEntities = newsEntities;
    }

    public LocalDate getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(LocalDate dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public List<LandLordEntity> getLandLords() {
        return landLords;
    }

    public void setLandLords(List<LandLordEntity> landLords) {
        this.landLords = landLords;
    }

    public List<LandLordTransactionEntity> getLandLordTransactionEntities() {
        return landLordTransactionEntities;
    }

    public void setLandLordTransactionEntities(List<LandLordTransactionEntity> landLordTransactionEntities) {
        this.landLordTransactionEntities = landLordTransactionEntities;
    }
}
