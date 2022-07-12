package vn.edu.hust.samiestate.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import vn.edu.hust.samiestate.dto.CustomerDTO;
import vn.edu.hust.samiestate.dto.response.CustomerSearchResponse;
import vn.edu.hust.samiestate.entity.CustomerEntity;
import vn.edu.hust.samiestate.entity.UserEntity;
import vn.edu.hust.samiestate.exception.NotFoundException;
import vn.edu.hust.samiestate.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CustomerConverter {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public CustomerConverter(ModelMapper modelMapper, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    public CustomerEntity convertToEntity(CustomerDTO dto) {
        CustomerEntity result = modelMapper.map(dto, CustomerEntity.class);

        if (!CollectionUtils.isEmpty(dto.getStaffIds())) {
            List<Long> staffIds = dto.getStaffIds();
            List<UserEntity> staffsAssignCustomer = staffIds.stream().map(item -> {
                Optional<UserEntity> userFoundOptional = Optional.ofNullable(userRepository.findById(item))
                        .orElseThrow(() -> new NotFoundException("User not found"));
                return userFoundOptional.get();
            }).collect(Collectors.toList());

            result.setUsers(staffsAssignCustomer);
        }

        return result;
    }

    public CustomerDTO convertToDTO(CustomerEntity entity) {
        CustomerDTO result = modelMapper.map(entity, CustomerDTO.class);
        return result;
    }

    public CustomerSearchResponse convertToCustomerSearchResponse(CustomerEntity entity) {

        CustomerSearchResponse result = modelMapper.map(entity, CustomerSearchResponse.class);

        //status
        result.setStatus(entity.getStatusCode().getCustomerStatusValue());

        return result;
    }
}
