package vn.edu.hust.samiestate.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.edu.hust.samiestate.dto.BuildingDTO;
import vn.edu.hust.samiestate.dto.response.BuildingSearchResponse;
import vn.edu.hust.samiestate.entity.BuildingEntity;
import vn.edu.hust.samiestate.entity.DistrictEntity;
import vn.edu.hust.samiestate.exception.NotFoundException;
import vn.edu.hust.samiestate.repository.DistrictRepository;
import vn.edu.hust.samiestate.utils.DateUtils;
import vn.edu.hust.samiestate.utils.ValidateUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class BuildingConverter {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private DistrictRepository districtRepository;

    public BuildingEntity convertToEntity(BuildingDTO dto) {
        BuildingEntity result = modelMapper.map(dto, BuildingEntity.class);

        // convert district
        if (ValidateUtils.isValidProperty(dto.getDistrictCode())) {
            DistrictEntity districtFound = Optional.ofNullable(districtRepository.findByCode(dto.getDistrictCode()))
                    .orElseThrow(() -> new NotFoundException("District not found"));
            result.setDistrict(districtFound);
        }
        return result;
    }

    public BuildingDTO convertToDTO(BuildingEntity entity) {
        BuildingDTO result = modelMapper.map(entity, BuildingDTO.class);

        //address
        StringBuilder address = new StringBuilder();
        String street = entity.getStreet();
        String ward = entity.getWard();
        String districtCode = entity.getDistrict().getCode();
        String districtName = entity.getDistrict().getName();
        if (ValidateUtils.isValidProperty(street)) {
            address.append(street).append(", ");
        }
        if (ValidateUtils.isValidProperty(ward)) {
            address.append(ward).append(", ");
        }
        if (ValidateUtils.isValidProperty(districtCode)) {
            address.append(districtName);
        }
        result.setAddress(address.toString());

        //rentArea String
        List<Integer> rentAreaValues = entity.getRentAreas().stream().map(item -> item.getValue())
                .distinct().collect(Collectors.toList());
        String rentAreaString = rentAreaValues.stream().map(item -> ""+item+"").
                collect(Collectors.joining(", "));
        result.setRentArea(rentAreaString);

        return result;
    }

    public BuildingSearchResponse convertToBuildingSearchResponse(BuildingEntity entity) {

        BuildingSearchResponse buildingSearchResponse = modelMapper.map(entity, BuildingSearchResponse.class);

        //createdDate
        buildingSearchResponse.setCreatedDate(DateUtils.convertDateToString(entity.getCreatedDate()));

        //address
        StringBuilder address = new StringBuilder();
        String street = entity.getStreet();
        String ward = entity.getWard();
        String districtCode = entity.getDistrict().getCode();
        String districtName = entity.getDistrict().getName();
        if (ValidateUtils.isValidProperty(street)) {
            address.append(street).append(", ");
        }
        if (ValidateUtils.isValidProperty(ward)) {
            address.append(ward).append(", ");
        }
        if (ValidateUtils.isValidProperty(districtCode)) {
            address.append(districtName);
        }
        buildingSearchResponse.setAddress(address.toString());

        //rentArea String
        List<Integer> rentAreaValues = entity.getRentAreas().stream().map(item -> item.getValue())
                .distinct().collect(Collectors.toList());
        String rentAreaString = rentAreaValues.stream().map(item -> ""+item+"").
                collect(Collectors.joining(", "));
        buildingSearchResponse.setRentArea(rentAreaString);

        return buildingSearchResponse;

    }
}
