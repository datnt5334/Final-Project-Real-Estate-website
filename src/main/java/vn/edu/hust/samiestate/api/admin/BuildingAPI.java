package vn.edu.hust.samiestate.api.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hust.samiestate.dto.BuildingDTO;
import vn.edu.hust.samiestate.dto.request.AssignmentBuildingRequest;
import vn.edu.hust.samiestate.dto.response.StaffAssignResponse;
import vn.edu.hust.samiestate.service.IBuildingService;

import java.util.List;

@RestController
@RequestMapping("/api/building")
public class BuildingAPI {

    @Autowired
    private IBuildingService buildingService;

    @PostMapping
    public BuildingDTO createBuilding(@RequestBody BuildingDTO buildingDTO) {
        try {
            return buildingService.save(buildingDTO);
        } catch (Exception e) {
            throw e;
        }
    }

    @PutMapping("/{id}")
    public BuildingDTO updateBuilding(@RequestBody BuildingDTO buildingDTO) {
        try {
            return buildingService.save(buildingDTO);
        } catch (Exception e) {
            throw e;
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteBuilding(@RequestBody long[] idList) {
        if (idList.length > 0) {
            buildingService.delete(idList);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{buildingId}/staffs")
    public List<StaffAssignResponse> loadStaff(@PathVariable("buildingId") Long buildingId) {
        List<StaffAssignResponse> results = buildingService.getStaffsOfBuilding(buildingId);
        return results;
    }

    @PostMapping("/assignment")
    public void assignBuildingToStaff(@RequestBody AssignmentBuildingRequest request) {
        try {
            buildingService.assignBuildingToStaff(request);
        } catch (Exception e) {
            throw e;
        }
    }
}
