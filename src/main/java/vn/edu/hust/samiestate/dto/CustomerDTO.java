package vn.edu.hust.samiestate.dto;

import vn.edu.hust.samiestate.enums.CustomerStatus;

import java.util.ArrayList;
import java.util.List;

public class CustomerDTO extends AbstractDTO {

    private String fullName;
    private String phone;
    private String email;
    private String company;
    private String note;
    private String demand;
    private CustomerStatus statusCode;
    private List<Long> staffIds = new ArrayList<>();

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

    public CustomerStatus getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(CustomerStatus statusCode) {
        this.statusCode = statusCode;
    }

    public List<Long> getStaffIds() {
        return staffIds;
    }

    public void setStaffIds(List<Long> staffIds) {
        this.staffIds = staffIds;
    }
}
