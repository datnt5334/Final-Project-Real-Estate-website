package vn.edu.hust.samiestate.enums;

public enum CustomerStatus {

    CHUA_XU_LY("Chưa xử lý"),
    DANG_XU_LY("Đang xử lý"),
    KHACH_HANG_CU("Khách hàng cũ");

    private final String customerStatusValue;

    CustomerStatus(String customerStatusValue) {
        this.customerStatusValue = customerStatusValue;
    }

    public String getCustomerStatusValue() {
        return customerStatusValue;
    }
}
