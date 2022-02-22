package vn.edu.hust.samiestate.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.hust.samiestate.converter.RentAreaConverter;
import vn.edu.hust.samiestate.dto.RentAreaDTO;
import vn.edu.hust.samiestate.entity.BuildingEntity;
import vn.edu.hust.samiestate.entity.RentAreaEntity;
import vn.edu.hust.samiestate.exception.FieldNullOrEmptyException;
import vn.edu.hust.samiestate.exception.NotFoundException;
import vn.edu.hust.samiestate.repository.BuildingRepository;
import vn.edu.hust.samiestate.repository.RentAreaRepository;
import vn.edu.hust.samiestate.service.IRentAreaService;
import vn.edu.hust.samiestate.utils.ValidateUtils;

import java.util.Objects;
import java.util.Optional;

@Service
public class RentAreaService implements IRentAreaService {

    @Autowired
    private RentAreaConverter rentAreaConverter;

    @Autowired
    private RentAreaRepository rentAreaRepository;

    @Autowired
    private BuildingRepository buildingRepository;

    @Override
    public RentAreaDTO getRentAreaById(Long rentAreaId) {
        Optional<RentAreaEntity> rentAreaFound = Optional.ofNullable(rentAreaRepository.findById(rentAreaId))
                .orElseThrow(() -> new NotFoundException("Rent area not found!"));

        return rentAreaConverter.convertToRentAreaDTO(rentAreaFound.get());
    }

    @Override
    @Transactional
    public void saveRentArea(RentAreaDTO rentAreaDTO) {
        if (Objects.nonNull(rentAreaDTO)) {

            if (!ValidateUtils.isValidProperty(rentAreaDTO.getValue())) {
                throw new FieldNullOrEmptyException("Field value is null or empty");
            }

            Long buildingId = rentAreaDTO.getBuildingId();
            RentAreaEntity rentAreaEntity = rentAreaConverter.convertToRentAreaEntity(rentAreaDTO);

            if (buildingId != null) {
                Optional<BuildingEntity> buildingFoundOptional = Optional.ofNullable(buildingRepository.findById(buildingId))
                        .orElseThrow(() -> new NotFoundException("Building not found!"));
                rentAreaEntity.setBuilding(buildingFoundOptional.get());
                rentAreaRepository.save(rentAreaEntity);
            } else {
                return;
            }
        }
    }

    @Override
    @Transactional
    public void delete(long[] ids) {
        rentAreaRepository.deleteByIdIn(ids);
    }

}
