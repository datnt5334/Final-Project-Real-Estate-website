package vn.edu.hust.samiestate.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "district")
public class DistrictEntity extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @OneToMany(mappedBy="district", fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE})
    private List<BuildingEntity> buildingEntities = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<BuildingEntity> getBuildingEntities() {
        return buildingEntities;
    }

    public void setBuildingEntities(List<BuildingEntity> buildingEntities) {
        this.buildingEntities = buildingEntities;
    }
}
