package vn.edu.hust.samiestate.dto;

public class RentAreaDTO extends AbstractDTO {

    private Integer value;
    private String description;
    private String rentDayFrom;
    private String rentDayTo;
    private Long buildingId;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRentDayFrom() {
        return rentDayFrom;
    }

    public void setRentDayFrom(String rentDayFrom) {
        this.rentDayFrom = rentDayFrom;
    }

    public String getRentDayTo() {
        return rentDayTo;
    }

    public void setRentDayTo(String rentDayTo) {
        this.rentDayTo = rentDayTo;
    }

    public Long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }
}
