package vn.edu.hust.samiestate.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.edu.hust.samiestate.dto.UserDTO;
import vn.edu.hust.samiestate.dto.response.StaffAssignResponse;
import vn.edu.hust.samiestate.dto.response.UserSearchResponse;
import vn.edu.hust.samiestate.entity.UserEntity;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserConverter {

    @Autowired
    private ModelMapper modelMapper;

    public UserDTO convertToDto (UserEntity entity){
        UserDTO result = modelMapper.map(entity, UserDTO.class);
        return result;
    }

    public UserEntity convertToEntity (UserDTO dto){
        UserEntity result = modelMapper.map(dto, UserEntity.class);
        return result;
    }

    public UserSearchResponse convertToSearchResponse (UserEntity entity){
        UserSearchResponse result = modelMapper.map(entity, UserSearchResponse.class);
        result.setRoleName(entity.getRoles().get(0).getName());
        return result;
    }

    public StaffAssignResponse convertToStaffAssignResponse (UserEntity staff, List<UserEntity> staffsOfBuilding){
        StaffAssignResponse result = modelMapper.map(staff, StaffAssignResponse.class);
        List<Long> staffIds = staffsOfBuilding.stream().map(item -> item.getId()).collect(Collectors.toList());
        if (staffIds.contains(staff.getId())) {
            result.setChecked("checked");
        }
        return result;
    }
}

