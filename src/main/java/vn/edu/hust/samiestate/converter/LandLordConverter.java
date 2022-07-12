package vn.edu.hust.samiestate.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import vn.edu.hust.samiestate.dto.LandLordDTO;
import vn.edu.hust.samiestate.dto.response.LandLordSearchResponse;
import vn.edu.hust.samiestate.entity.LandLordEntity;
import vn.edu.hust.samiestate.entity.UserEntity;
import vn.edu.hust.samiestate.exception.NotFoundException;
import vn.edu.hust.samiestate.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class LandLordConverter {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public LandLordConverter(ModelMapper modelMapper, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    public LandLordEntity convertToEntity(LandLordDTO dto) {
        LandLordEntity result = modelMapper.map(dto, LandLordEntity.class);

        if (!CollectionUtils.isEmpty(dto.getStaffIds())) {
            List<Long> staffIds = dto.getStaffIds();
            List<UserEntity> staffsAssignLandLord = staffIds.stream().map(item -> {
                Optional<UserEntity> userFoundOptional = Optional.ofNullable(userRepository.findById(item))
                        .orElseThrow(() -> new NotFoundException("User not found"));
                return userFoundOptional.get();
            }).collect(Collectors.toList());

            result.setUsers(staffsAssignLandLord);
        }

        return result;
    }

    public LandLordDTO convertToDTO(LandLordEntity entity) {
        LandLordDTO result = modelMapper.map(entity, LandLordDTO.class);

        return result;
    }

    public LandLordSearchResponse convertToSearchResponse(LandLordEntity entity) {
        LandLordSearchResponse result = modelMapper.map(entity, LandLordSearchResponse.class);

        // status
        result.setStatus(entity.getStatusCode().getLandLordStatusValue());

        return result;
    }
}
