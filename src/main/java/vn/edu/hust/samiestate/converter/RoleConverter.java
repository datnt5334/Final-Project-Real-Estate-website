package vn.edu.hust.samiestate.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.edu.hust.samiestate.dto.RoleDTO;
import vn.edu.hust.samiestate.entity.RoleEntity;

@Component
public class RoleConverter {

    @Autowired
    private ModelMapper modelMapper;

    public RoleDTO convertToDto(RoleEntity entity) {
        RoleDTO result = modelMapper.map(entity, RoleDTO.class);
        return result;
    }

    public RoleEntity convertToEntity(RoleDTO dto) {
        RoleEntity result = modelMapper.map(dto, RoleEntity.class);
        return result;
    }
}
