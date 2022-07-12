package vn.edu.hust.samiestate.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import vn.edu.hust.samiestate.dto.LandLordTransactionTypeDTO;
import vn.edu.hust.samiestate.entity.LandLordTransactionTypeEntity;

@Component
public class LandLordTransactionTypeConverter {

    private final ModelMapper modelMapper;

    public LandLordTransactionTypeConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public LandLordTransactionTypeDTO convertToDTO(LandLordTransactionTypeEntity entity) {
        LandLordTransactionTypeDTO result = modelMapper.map(entity, LandLordTransactionTypeDTO.class);

        return result;
    }

    public LandLordTransactionTypeEntity convertToEntity(LandLordTransactionTypeDTO dto) {
        LandLordTransactionTypeEntity result = modelMapper.map(dto, LandLordTransactionTypeEntity.class);

        return result;
    }
}
