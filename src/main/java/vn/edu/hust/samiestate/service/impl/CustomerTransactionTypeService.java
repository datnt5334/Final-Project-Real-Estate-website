package vn.edu.hust.samiestate.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.hust.samiestate.converter.CustomerTransactionTypeConverter;
import vn.edu.hust.samiestate.dto.CustomerTransactionTypeDTO;
import vn.edu.hust.samiestate.entity.CustomerTransactionTypeEntity;
import vn.edu.hust.samiestate.exception.FieldNullOrEmptyException;
import vn.edu.hust.samiestate.repository.CustomerTransactionTypeRepository;
import vn.edu.hust.samiestate.service.ICustomerTransactionTypeService;
import vn.edu.hust.samiestate.utils.ValidateUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CustomerTransactionTypeService implements ICustomerTransactionTypeService {

    private final CustomerTransactionTypeRepository customerTransactionTypeRepository;
    private final CustomerTransactionTypeConverter customerTransactionTypeConverter;

    public CustomerTransactionTypeService(CustomerTransactionTypeRepository customerTransactionTypeRepository,
                                          CustomerTransactionTypeConverter customerTransactionTypeConverter) {
        this.customerTransactionTypeRepository = customerTransactionTypeRepository;
        this.customerTransactionTypeConverter = customerTransactionTypeConverter;
    }

    @Override
    public Map<String, String> getTransactionType() {
        Map<String, String> results = new HashMap<>();

        List<CustomerTransactionTypeEntity> transactionTypeEntities = customerTransactionTypeRepository.findAll();

        for (CustomerTransactionTypeEntity item: transactionTypeEntities) {
            results.put(item.getCode(), item.getName());
        }

        return results;
    }

    @Override
    public List<CustomerTransactionTypeDTO> getTransactionTypeList() {
        List<CustomerTransactionTypeDTO> results = customerTransactionTypeRepository.findAll().stream().
                map(item -> customerTransactionTypeConverter.convertToDTO(item)).collect(Collectors.toList());

        return results;
    }

    @Override
    @Transactional
    public void save(CustomerTransactionTypeDTO customerTransactionTypeDTO) {
        if (Objects.nonNull(customerTransactionTypeDTO)) {

            if (!ValidateUtils.isValidProperty(customerTransactionTypeDTO.getName())) {
                throw new FieldNullOrEmptyException("Field name is null or empty");
            }

            if (!ValidateUtils.isValidProperty(customerTransactionTypeDTO.getCode())) {
                throw new FieldNullOrEmptyException("Field code is null or empty");
            }

            CustomerTransactionTypeEntity transactionTypeEntity =
                    customerTransactionTypeConverter.convertToEntity(customerTransactionTypeDTO);

            customerTransactionTypeRepository.save(transactionTypeEntity);
        }
    }

    @Override
    @Transactional
    public void delete(long[] ids) {
        customerTransactionTypeRepository.deleteByIdIn(ids);
    }
}
