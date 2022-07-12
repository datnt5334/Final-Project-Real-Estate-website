package vn.edu.hust.samiestate.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.hust.samiestate.converter.LandLordTransactionTypeConverter;
import vn.edu.hust.samiestate.dto.LandLordTransactionTypeDTO;
import vn.edu.hust.samiestate.entity.LandLordTransactionTypeEntity;
import vn.edu.hust.samiestate.exception.FieldNullOrEmptyException;
import vn.edu.hust.samiestate.repository.LandLordTransactionTypeRepository;
import vn.edu.hust.samiestate.service.ILandLordTransactionTypeService;
import vn.edu.hust.samiestate.utils.ValidateUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class LandLordTransactionTypeService implements ILandLordTransactionTypeService {

    private final LandLordTransactionTypeRepository landLordTransactionTypeRepository;
    private final LandLordTransactionTypeConverter landLordTransactionTypeConverter;

    public LandLordTransactionTypeService(LandLordTransactionTypeRepository landLordTransactionTypeRepository,
                                          LandLordTransactionTypeConverter landLordTransactionTypeConverter) {
        this.landLordTransactionTypeRepository = landLordTransactionTypeRepository;
        this.landLordTransactionTypeConverter = landLordTransactionTypeConverter;
    }

    @Override
    public Map<String, String> getTransactionType() {
        Map<String, String> results = new HashMap<>();

        List<LandLordTransactionTypeEntity> transactionTypeEntities = landLordTransactionTypeRepository.findAll();

        for (LandLordTransactionTypeEntity item: transactionTypeEntities) {
            results.put(item.getCode(), item.getName());
        }

        return results;
    }

    @Override
    public List<LandLordTransactionTypeDTO> getTransactionTypeList() {
        List<LandLordTransactionTypeDTO> results = landLordTransactionTypeRepository.findAll().stream().
                map(item -> landLordTransactionTypeConverter.convertToDTO(item)).collect(Collectors.toList());

        return results;
    }

    @Override
    @Transactional
    public void save(LandLordTransactionTypeDTO landLordTransactionTypeDTO) {
        if (Objects.nonNull(landLordTransactionTypeDTO)) {

            if (!ValidateUtils.isValidProperty(landLordTransactionTypeDTO.getName())) {
                throw new FieldNullOrEmptyException("Field name is null or empty");
            }

            if (!ValidateUtils.isValidProperty(landLordTransactionTypeDTO.getCode())) {
                throw new FieldNullOrEmptyException("Field code is null or empty");
            }

            LandLordTransactionTypeEntity transactionTypeEntity =
                    landLordTransactionTypeConverter.convertToEntity(landLordTransactionTypeDTO);

            landLordTransactionTypeRepository.save(transactionTypeEntity);
        }
    }

    @Override
    @Transactional
    public void delete(long[] ids) {
        landLordTransactionTypeRepository.deleteByIdIn(ids);
    }
}
