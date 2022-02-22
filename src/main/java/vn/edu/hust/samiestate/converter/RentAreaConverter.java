package vn.edu.hust.samiestate.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.edu.hust.samiestate.dto.RentAreaDTO;
import vn.edu.hust.samiestate.entity.RentAreaEntity;

@Component
public class RentAreaConverter {

    @Autowired
    private ModelMapper modelMapper;

    public RentAreaEntity convertToRentAreaEntity(RentAreaDTO dto) {
        RentAreaEntity result = modelMapper.map(dto, RentAreaEntity.class);

        return result;
    }

    public RentAreaDTO convertToRentAreaDTO(RentAreaEntity entity) {
        RentAreaDTO result = modelMapper.map(entity, RentAreaDTO.class);

        return result;
    }
}
