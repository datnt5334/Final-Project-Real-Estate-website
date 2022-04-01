package vn.edu.hust.samiestate.service;

import org.springframework.data.domain.Pageable;
import vn.edu.hust.samiestate.dto.BuildingDTO;
import vn.edu.hust.samiestate.dto.UserDTO;
import vn.edu.hust.samiestate.dto.request.AssignmentBuildingRequest;
import vn.edu.hust.samiestate.dto.request.BuildingSearchRequest;
import vn.edu.hust.samiestate.dto.response.BuildingSearchResponse;
import vn.edu.hust.samiestate.dto.response.StaffAssignResponse;

import java.util.List;

public interface IBuildingService {

    BuildingDTO save(BuildingDTO buildingDTO);
    BuildingDTO findBuildingById(Long id);
    List<BuildingSearchResponse> getSearchBuildings(BuildingSearchRequest request, Pageable pageable);
    List<BuildingDTO> getLatestBuildings(Pageable pageable);
    List<BuildingDTO> getBuildingByLevel(String level, Pageable pageable);
    int getTotalItems(BuildingSearchRequest request);
    List<StaffAssignResponse> getStaffsOfBuilding(Long buildingId);
    List<UserDTO> getAssignStaffsOfBuilding(Long buildingId);
    void assignBuildingToStaff(AssignmentBuildingRequest request);
    void delete(long[] ids);

}
