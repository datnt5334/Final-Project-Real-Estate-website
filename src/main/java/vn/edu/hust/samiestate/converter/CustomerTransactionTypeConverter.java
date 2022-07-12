package vn.edu.hust.samiestate.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import vn.edu.hust.samiestate.dto.CustomerTransactionTypeDTO;
import vn.edu.hust.samiestate.entity.CustomerTransactionTypeEntity;

@Component
public class CustomerTransactionTypeConverter {

    private final ModelMapper modelMapper;

    public CustomerTransactionTypeConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CustomerTransactionTypeDTO convertToDTO(CustomerTransactionTypeEntity entity) {
        CustomerTransactionTypeDTO result = modelMapper.map(entity, CustomerTransactionTypeDTO.class);

        return result;
    }

    public CustomerTransactionTypeEntity convertToEntity(CustomerTransactionTypeDTO dto) {
        CustomerTransactionTypeEntity result = modelMapper.map(dto, CustomerTransactionTypeEntity.class);

        return result;
    }
}
