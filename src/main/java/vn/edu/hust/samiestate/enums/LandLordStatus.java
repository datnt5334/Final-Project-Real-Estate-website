package vn.edu.hust.samiestate.enums;

public enum LandLordStatus {

    CHUA_XU_LY("Chưa xử lý"),
    DANG_XU_LY("Đang xử lý"),
    KHACH_HANG_CU("Khách hàng cũ");

    private final String landLordStatusValue;

    LandLordStatus(String landLordStatusValue) {
        this.landLordStatusValue = landLordStatusValue;
    }

    public String getLandLordStatusValue() {
        return landLordStatusValue;
    }
}
