package vn.edu.hust.samiestate.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.edu.hust.samiestate.dto.CustomerDTO;
import vn.edu.hust.samiestate.dto.response.CustomerSearchResponse;
import vn.edu.hust.samiestate.entity.CustomerEntity;
import vn.edu.hust.samiestate.entity.CustomerStatusEntity;
import vn.edu.hust.samiestate.exception.NotFoundException;
import vn.edu.hust.samiestate.repository.CustomerStatusRepository;
import vn.edu.hust.samiestate.utils.ValidateUtils;

import java.util.Optional;

@Component
public class CustomerConverter {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CustomerStatusRepository customerStatusRepository;

    public CustomerEntity convertToEntity(CustomerDTO dto) {
        CustomerEntity result = modelMapper.map(dto, CustomerEntity.class);
        if (ValidateUtils.isValidProperty(dto.getStatus())) {
            CustomerStatusEntity statusFound = Optional.ofNullable(customerStatusRepository.findByCode(dto.getStatus()))
                    .orElseThrow(() -> new NotFoundException("Customer status not found"));
            result.setCustomerStatus(statusFound);
        }
        return result;
    }

    public CustomerDTO convertToDTO(CustomerEntity entity) {
        CustomerDTO result = modelMapper.map(entity, CustomerDTO.class);
        result.setStatus(entity.getCustomerStatus().getCode());
        return result;
    }

    public CustomerSearchResponse convertToCustomerSearchResponse(CustomerEntity entity) {

        CustomerSearchResponse result = modelMapper.map(entity, CustomerSearchResponse.class);

        //status
        result.setStatus(entity.getCustomerStatus().getName());

        return result;
    }
}
