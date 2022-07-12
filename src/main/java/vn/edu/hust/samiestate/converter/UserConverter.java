package vn.edu.hust.samiestate.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import vn.edu.hust.samiestate.dto.UserDTO;
import vn.edu.hust.samiestate.dto.response.StaffAssignResponse;
import vn.edu.hust.samiestate.dto.response.StaffAssignXYZResponse;
import vn.edu.hust.samiestate.dto.response.UserSearchResponse;
import vn.edu.hust.samiestate.entity.UserEntity;
import vn.edu.hust.samiestate.utils.ValidateUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class UserConverter {

    private final ModelMapper modelMapper;

    public UserConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UserDTO convertToDto (UserEntity entity){
        UserDTO result = modelMapper.map(entity, UserDTO.class);

        // dayOfBirth
        if (Objects.nonNull(entity.getDayOfBirth())) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String dayOfBirth = entity.getDayOfBirth().format(formatter);
            result.setDayOfBirth(dayOfBirth);
        }

        return result;
    }

    public UserEntity convertToEntity (UserDTO dto){
        UserEntity result = modelMapper.map(dto, UserEntity.class);

        // dayOfBirth
        if (ValidateUtils.isValidProperty(dto.getDayOfBirth())) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
            LocalDate dayOfBirth = LocalDate.parse(dto.getDayOfBirth(), formatter);
            result.setDayOfBirth(dayOfBirth);
        }

        return result;
    }

    public UserSearchResponse convertToSearchResponse (UserEntity entity){
        UserSearchResponse result = modelMapper.map(entity, UserSearchResponse.class);
        result.setRoleName(entity.getRoles().get(0).getName());
        return result;
    }

    public StaffAssignXYZResponse convertToStaffAssignXYZResponse (UserEntity staff, List<UserEntity> staffsOfBuilding){
        StaffAssignXYZResponse result = modelMapper.map(staff, StaffAssignXYZResponse.class);
        List<Long> staffIds = staffsOfBuilding.stream().map(item -> item.getId()).collect(Collectors.toList());
        if (staffIds.contains(staff.getId())) {
            result.setChecked("checked");
        }
        return result;
    }

    public StaffAssignResponse convertToStaffAssignResponse (UserEntity staff, List<UserEntity> staffs){
        StaffAssignResponse result = modelMapper.map(staff, StaffAssignResponse.class);
        List<Long> staffIds = staffs.stream().map(item -> item.getId()).collect(Collectors.toList());
        if (staffIds.contains(staff.getId())) {
            result.setSelected("selected");
        }
        return result;
    }
}

