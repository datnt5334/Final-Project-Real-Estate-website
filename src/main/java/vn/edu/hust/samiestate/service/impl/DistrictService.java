package vn.edu.hust.samiestate.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.hust.samiestate.converter.DistrictConverter;
import vn.edu.hust.samiestate.dto.DistrictDTO;
import vn.edu.hust.samiestate.entity.DistrictEntity;
import vn.edu.hust.samiestate.exception.FieldNullOrEmptyException;
import vn.edu.hust.samiestate.repository.DistrictRepository;
import vn.edu.hust.samiestate.service.IDistrictService;
import vn.edu.hust.samiestate.utils.ValidateUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class DistrictService implements IDistrictService {

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private DistrictConverter districtConverter;

    @Override
    public Map<String, String> getDistrict() {
        Map<String, String> results = new HashMap<>();

        List<DistrictEntity> districtEntityList = districtRepository.findAll();

        for (DistrictEntity item: districtEntityList) {
            results.put(item.getCode(), item.getName());
        }

        return results;
    }

    @Override
    public List<DistrictDTO> getDistrictList() {

        List<DistrictDTO> results = districtRepository.findAll().stream().
                map(item -> districtConverter.convertToDTO(item)).collect(Collectors.toList());

        return results;
    }

    @Override
    @Transactional
    public void save(DistrictDTO districtDTO) {
        if (Objects.nonNull(districtDTO)) {

            if (!ValidateUtils.isValidProperty(districtDTO.getName())) {
                throw new FieldNullOrEmptyException("Field name is null or empty");
            }

            if (!ValidateUtils.isValidProperty(districtDTO.getCode())) {
                throw new FieldNullOrEmptyException("Field code is null or empty");
            }

            DistrictEntity districtEntity = districtConverter.convertToEntity(districtDTO);

            districtRepository.save(districtEntity);
        }
    }

    @Override
    @Transactional
    public void delete(long[] ids) {
        districtRepository.deleteByIdIn(ids);
    }
}
