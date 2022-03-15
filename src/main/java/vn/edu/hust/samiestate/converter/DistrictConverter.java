package vn.edu.hust.samiestate.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.edu.hust.samiestate.dto.DistrictDTO;
import vn.edu.hust.samiestate.entity.DistrictEntity;

@Component
public class DistrictConverter {

    @Autowired
    private ModelMapper modelMapper;

    public DistrictDTO convertToDTO(DistrictEntity entity) {
        DistrictDTO result = modelMapper.map(entity, DistrictDTO.class);

        return result;
    }

    public DistrictEntity convertToEntity(DistrictDTO dto) {
        DistrictEntity result = modelMapper.map(dto, DistrictEntity.class);

        return result;
    }

}
