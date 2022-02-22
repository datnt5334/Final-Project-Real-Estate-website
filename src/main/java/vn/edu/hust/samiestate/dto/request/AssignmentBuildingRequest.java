package vn.edu.hust.samiestate.dto.request;

public class AssignmentBuildingRequest {

    private Long buildingId;
    private Long[] staffIds = new Long[] {};

    public Long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }

    public Long[] getStaffIds() {
        return staffIds;
    }

    public void setStaffIds(Long[] staffIds) {
        this.staffIds = staffIds;
    }
}
