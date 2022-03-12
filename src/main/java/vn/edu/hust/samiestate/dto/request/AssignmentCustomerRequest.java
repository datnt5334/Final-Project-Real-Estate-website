package vn.edu.hust.samiestate.dto.request;

public class AssignmentCustomerRequest {

    private Long customerId;
    private Long[] staffIds = new Long[] {};

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long[] getStaffIds() {
        return staffIds;
    }

    public void setStaffIds(Long[] staffIds) {
        this.staffIds = staffIds;
    }
}
