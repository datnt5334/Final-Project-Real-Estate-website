package vn.edu.hust.samiestate.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.hust.samiestate.converter.TransactionConverter;
import vn.edu.hust.samiestate.converter.TransactionTypeConverter;
import vn.edu.hust.samiestate.dto.DistrictDTO;
import vn.edu.hust.samiestate.dto.TransactionTypeDTO;
import vn.edu.hust.samiestate.entity.DistrictEntity;
import vn.edu.hust.samiestate.entity.TransactionTypeEntity;
import vn.edu.hust.samiestate.exception.FieldNullOrEmptyException;
import vn.edu.hust.samiestate.repository.TransactionTypeRepository;
import vn.edu.hust.samiestate.service.ITransactionTypeService;
import vn.edu.hust.samiestate.utils.ValidateUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class TransactionTypeService implements ITransactionTypeService {

    @Autowired
    private TransactionTypeRepository transactionTypeRepository;

    @Autowired
    private TransactionTypeConverter transactionTypeConverter;

    @Override
    public Map<String, String> getTransactionType() {
        Map<String, String> results = new HashMap<>();

        List<TransactionTypeEntity> transactionTypeEntities = transactionTypeRepository.findAll();

        for (TransactionTypeEntity item: transactionTypeEntities) {
            results.put(item.getCode(), item.getName());
        }

        return results;
    }

    @Override
    public List<TransactionTypeDTO> getTransactionTypeList() {
        List<TransactionTypeDTO> results = transactionTypeRepository.findAll().stream().
                map(item -> transactionTypeConverter.convertToDTO(item)).collect(Collectors.toList());

        return results;
    }

    @Override
    @Transactional
    public void save(TransactionTypeDTO transactionTypeDTO) {
        if (Objects.nonNull(transactionTypeDTO)) {

            if (!ValidateUtils.isValidProperty(transactionTypeDTO.getName())) {
                throw new FieldNullOrEmptyException("Field name is null or empty");
            }

            if (!ValidateUtils.isValidProperty(transactionTypeDTO.getCode())) {
                throw new FieldNullOrEmptyException("Field code is null or empty");
            }

            TransactionTypeEntity transactionTypeEntity = transactionTypeConverter.convertToEntity(transactionTypeDTO);

            transactionTypeRepository.save(transactionTypeEntity);
        }
    }

    @Override
    @Transactional
    public void delete(long[] ids) {
        transactionTypeRepository.deleteByIdIn(ids);
    }
}
