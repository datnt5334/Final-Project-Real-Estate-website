package vn.edu.hust.samiestate.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.hust.samiestate.builder.BuildingSearchBuilder;
import vn.edu.hust.samiestate.constant.SystemConstant;
import vn.edu.hust.samiestate.converter.BuildingConverter;
import vn.edu.hust.samiestate.converter.UserConverter;
import vn.edu.hust.samiestate.dto.BuildingDTO;
import vn.edu.hust.samiestate.dto.UserDTO;
import vn.edu.hust.samiestate.dto.request.AssignmentBuildingRequest;
import vn.edu.hust.samiestate.dto.request.BuildingSearchRequest;
import vn.edu.hust.samiestate.dto.response.BuildingSearchResponse;
import vn.edu.hust.samiestate.dto.response.StaffAssignResponse;
import vn.edu.hust.samiestate.entity.BuildingEntity;
import vn.edu.hust.samiestate.entity.UserEntity;
import vn.edu.hust.samiestate.exception.FieldNullOrEmptyException;
import vn.edu.hust.samiestate.exception.NotFoundException;
import vn.edu.hust.samiestate.repository.BuildingRepository;
import vn.edu.hust.samiestate.repository.UserRepository;
import vn.edu.hust.samiestate.security.utils.SecurityUtils;
import vn.edu.hust.samiestate.service.IBuildingService;
import vn.edu.hust.samiestate.utils.ValidateUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BuildingService implements IBuildingService {

    @Autowired
    private BuildingConverter buildingConverter;

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    @Override
    @Transactional
    public BuildingDTO save(BuildingDTO buildingDTO) {

        if (Objects.nonNull(buildingDTO)) {

            if (!ValidateUtils.isValidProperty(buildingDTO.getName())) {
                throw new FieldNullOrEmptyException("Field name is null or empty");
            }

            Long buildingId = buildingDTO.getId();
            BuildingEntity buildingEntity = buildingConverter.convertToEntity(buildingDTO);

            if (buildingId != null) {
                Optional<BuildingEntity> buildingFoundOptional = Optional.ofNullable(buildingRepository.findById(buildingId))
                        .orElseThrow(() -> new NotFoundException("Building not found!"));
                buildingEntity.setUsers(buildingFoundOptional.get().getUsers());
            }

            return buildingConverter.convertToDTO(buildingRepository.save(buildingEntity));
        }
        return null;
    }

    @Override
    public BuildingDTO findBuildingById(Long id) {
        return buildingConverter.convertToDTO(buildingRepository.findById(id).get());
    }

    @Override
    public List<BuildingSearchResponse> getSearchBuildings(BuildingSearchRequest request, Pageable pageable) {

        if (SecurityUtils.getAuthorities().contains(SystemConstant.STAFF_ROLE)) {
            request.setStaffId(SecurityUtils.getPrincipal().getId());
        }

        BuildingSearchBuilder builder = initBuildingSearchBuilder(request);

        List<BuildingEntity> buildingEntities = buildingRepository.findByCondition(builder, pageable);

        List<BuildingSearchResponse> results = buildingEntities.stream().map(item ->
                        buildingConverter.convertToBuildingSearchResponse(item)).collect(Collectors.toList());

        return results;
    }

    @Override
    public List<BuildingDTO> getLatestBuildings(Pageable pageable) {

        List<BuildingEntity> buildingEntities = buildingRepository.findAllByOrderByCreatedDateDesc(pageable);

        List<BuildingDTO> results = buildingEntities.stream().map(item ->
                buildingConverter.convertToDTO(item)).collect(Collectors.toList());

        return results;
    }

    @Override
    public List<BuildingDTO> getBuildingByLevel(String level, Pageable pageable) {

        List<BuildingEntity> buildingEntities = buildingRepository.findByLevel(level, pageable);

        List<BuildingDTO> results = buildingEntities.stream().map(item ->
                buildingConverter.convertToDTO(item)).collect(Collectors.toList());

        return results;
    }

    @Override
    public int getTotalItems(BuildingSearchRequest request) {
        int result = 0;
        if (Objects.nonNull(request)) {
            BuildingSearchBuilder builder = initBuildingSearchBuilder(request);
            return (int) buildingRepository.countByCondition(builder);
        }
        return result;
    }

    @Override
    public List<StaffAssignResponse> getStaffsOfBuilding(Long buildingId) {

        List<UserEntity> allStaffs = userRepository.findByStatusAndRoles_Code(1, SystemConstant.STAFF_ROLE);
        List<UserEntity> staffOfBuilding = userRepository.findByBuildings_Id(buildingId);

        List<StaffAssignResponse> results = allStaffs.stream().map(item ->
                userConverter.convertToStaffAssignResponse(item, staffOfBuilding)).collect(Collectors.toList());

        return results;
    }

    @Override
    public List<UserDTO> getAssignStaffsOfBuilding(Long buildingId) {

        List<UserEntity> staffOfBuilding = userRepository.findByBuildings_Id(buildingId);

        List<UserDTO> results = staffOfBuilding.stream().map(item ->
                userConverter.convertToDto(item)).collect(Collectors.toList());

        return results;
    }

    @Override
    @Transactional
    public void assignBuildingToStaff(AssignmentBuildingRequest request) {

        if (Objects.nonNull(request)) {

            List<Long> staffIds = new ArrayList<>(Arrays.asList(request.getStaffIds()));
            Long buildingId = request.getBuildingId();

            if (ValidateUtils.isValidProperty(buildingId)) {
                Optional<BuildingEntity> buildingFoundOptional = Optional.ofNullable(buildingRepository.findById(buildingId))
                        .orElseThrow(() -> new NotFoundException("Building not found"));

                List<UserEntity> newStaffs = staffIds.stream().map(item -> {
                    Optional<UserEntity> userFoundOptional = Optional.ofNullable(userRepository.findById(item))
                            .orElseThrow(() -> new NotFoundException("User not found"));
                    return userFoundOptional.get();
                }).collect(Collectors.toList());

                buildingFoundOptional.get().setUsers(newStaffs);

                buildingRepository.save(buildingFoundOptional.get());
            }
        }
    }

    @Override
    @Transactional
    public void delete(long[] ids) {
        buildingRepository.deleteByIdIn(ids);
    }

    private BuildingSearchBuilder initBuildingSearchBuilder(BuildingSearchRequest request) {

        BuildingSearchBuilder results = new BuildingSearchBuilder.Builder()
                .setName(request.getName()).setFloorArea(request.getFloorArea())
                .setWard(request.getWard()).setStreet(request.getStreet())
                .setNumberOfBasement(request.getNumberOfBasement())
                .setDirection(request.getDirection()).setLevel(request.getLevel())
                .setManagerName(request.getManagerName()).setManagerPhone(request.getManagerPhone())
                .setStaffId(request.getStaffId()).setAreaRentFrom(request.getAreaRentFrom())
                .setAreaRentTo(request.getAreaRentTo()).setCostRentFrom(request.getCostRentFrom())
                .setCostRentTo(request.getCostRentTo()).setDistrictCode(request.getDistrictCode()).build();
        return results;

    }
}
