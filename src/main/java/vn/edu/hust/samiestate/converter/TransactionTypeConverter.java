package vn.edu.hust.samiestate.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.edu.hust.samiestate.dto.DistrictDTO;
import vn.edu.hust.samiestate.dto.TransactionTypeDTO;
import vn.edu.hust.samiestate.entity.DistrictEntity;
import vn.edu.hust.samiestate.entity.TransactionTypeEntity;

@Component
public class TransactionTypeConverter {

    @Autowired
    private ModelMapper modelMapper;

    public TransactionTypeDTO convertToDTO(TransactionTypeEntity entity) {
        TransactionTypeDTO result = modelMapper.map(entity, TransactionTypeDTO.class);

        return result;
    }

    public TransactionTypeEntity convertToEntity(TransactionTypeDTO dto) {
        TransactionTypeEntity result = modelMapper.map(dto, TransactionTypeEntity.class);

        return result;
    }

}
