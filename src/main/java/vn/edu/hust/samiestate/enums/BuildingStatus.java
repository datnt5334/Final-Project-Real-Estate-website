package vn.edu.hust.samiestate.enums;

public enum BuildingStatus {

    CHUA_XU_LY("Chưa xử lý"),
    DANG_CH0_THUE("Đang cho thuê"),
    NGUNG_CHO_THUE("Ngưng cho thuê");

    private final String buildingStatusValue;

    BuildingStatus(String buildingStatusValue) {
        this.buildingStatusValue = buildingStatusValue;
    }

    public String getBuildingStatusValue() {
        return buildingStatusValue;
    }
}
