package vn.edu.hust.samiestate.entity;

import javax.persistence.*;

@Entity
@Table(name = "rentarea")
public class RentAreaEntity extends BaseEntity {

    @Column(name = "value")
    private Integer value;

    @Column(name = "description")
    private String description;

    @Column(name = "rentdayfrom")
    private String rentDayFrom;

    @Column(name = "rentdayto")
    private String rentDayTo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="building_id", nullable=false)
    private BuildingEntity building;

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

    public BuildingEntity getBuilding() {
        return building;
    }

    public void setBuilding(BuildingEntity building) {
        this.building = building;
    }
}
