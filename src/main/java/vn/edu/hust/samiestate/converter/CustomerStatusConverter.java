package vn.edu.hust.samiestate.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.edu.hust.samiestate.dto.CustomerDTO;
import vn.edu.hust.samiestate.dto.CustomerStatusDTO;
import vn.edu.hust.samiestate.entity.CustomerEntity;
import vn.edu.hust.samiestate.entity.CustomerStatusEntity;

@Component
public class CustomerStatusConverter {

    @Autowired
    private ModelMapper modelMapper;

    public CustomerStatusEntity convertToEntity(CustomerStatusDTO dto) {
        CustomerStatusEntity result = modelMapper.map(dto, CustomerStatusEntity.class);
        return result;
    }

    public CustomerStatusDTO convertToDTO(CustomerStatusEntity entity) {
        CustomerStatusDTO result = modelMapper.map(entity, CustomerStatusDTO.class);
        return result;
    }
}
