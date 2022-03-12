package vn.edu.hust.samiestate.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.hust.samiestate.converter.CustomerStatusConverter;
import vn.edu.hust.samiestate.dto.CustomerStatusDTO;
import vn.edu.hust.samiestate.entity.CustomerStatusEntity;
import vn.edu.hust.samiestate.repository.CustomerStatusRepository;
import vn.edu.hust.samiestate.service.ICustomerStatusService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerStatusService implements ICustomerStatusService {

    @Autowired
    private CustomerStatusRepository customerStatusRepository;

    @Autowired
    private CustomerStatusConverter customerStatusConverter;

    @Override
    public Map<String, String> getStatus() {
        Map<String,String> result = new HashMap<>();
        List<CustomerStatusEntity> customerStatusEntities = customerStatusRepository.findAll();
        customerStatusEntities.forEach(entity -> {
            CustomerStatusDTO customerStatusDTO = customerStatusConverter.convertToDTO(entity);
            result.put(customerStatusDTO.getCode(), customerStatusDTO.getName());
        });
        return result;
    }
}
