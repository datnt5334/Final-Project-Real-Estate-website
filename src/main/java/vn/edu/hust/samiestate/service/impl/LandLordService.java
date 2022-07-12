package vn.edu.hust.samiestate.service.impl;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.hust.samiestate.builder.LandLordSearchBuilder;
import vn.edu.hust.samiestate.constant.SystemConstant;
import vn.edu.hust.samiestate.converter.LandLordConverter;
import vn.edu.hust.samiestate.converter.TransactionConverter;
import vn.edu.hust.samiestate.converter.UserConverter;
import vn.edu.hust.samiestate.dto.LandLordDTO;
import vn.edu.hust.samiestate.dto.request.LandLordSearchRequest;
import vn.edu.hust.samiestate.dto.request.TransactionRequest;
import vn.edu.hust.samiestate.dto.response.LandLordSearchResponse;
import vn.edu.hust.samiestate.dto.response.StaffAssignResponse;
import vn.edu.hust.samiestate.dto.response.TransactionResponse;
import vn.edu.hust.samiestate.entity.*;
import vn.edu.hust.samiestate.enums.LandLordStatus;
import vn.edu.hust.samiestate.exception.NotFoundException;
import vn.edu.hust.samiestate.repository.LandLordRepository;
import vn.edu.hust.samiestate.repository.LandLordTransactionRepository;
import vn.edu.hust.samiestate.repository.LandLordTransactionTypeRepository;
import vn.edu.hust.samiestate.repository.UserRepository;
import vn.edu.hust.samiestate.security.utils.SecurityUtils;
import vn.edu.hust.samiestate.service.ILandLordService;
import vn.edu.hust.samiestate.utils.ValidateUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class LandLordService implements ILandLordService {

    private final LandLordRepository landLordRepository;
    private final LandLordConverter landLordConverter;
    private final UserConverter userConverter;
    private final UserRepository userRepository;
    private final TransactionConverter transactionConverter;
    private final LandLordTransactionTypeRepository landLordTransactionTypeRepository;
    private final LandLordTransactionRepository landLordTransactionRepository;

    public LandLordService(LandLordRepository landLordRepository,
                           LandLordConverter landLordConverter, UserConverter userConverter,
                           UserRepository userRepository, TransactionConverter transactionConverter,
                           LandLordTransactionTypeRepository landLordTransactionTypeRepository,
                           LandLordTransactionRepository landLordTransactionRepository) {
        this.landLordRepository = landLordRepository;
        this.landLordConverter = landLordConverter;
        this.userConverter = userConverter;
        this.userRepository = userRepository;
        this.transactionConverter = transactionConverter;
        this.landLordTransactionTypeRepository = landLordTransactionTypeRepository;
        this.landLordTransactionRepository = landLordTransactionRepository;
    }

    @Override
    @Transactional
    public LandLordDTO save(LandLordDTO landLordDTO) {

        if (Objects.nonNull(landLordDTO)) {
            LandLordEntity landLordEntity = landLordConverter.convertToEntity(landLordDTO);

            return landLordConverter.convertToDTO(landLordRepository.save(landLordEntity));
        }

        return null;
    }

    @Override
    public LandLordDTO findLandLordById(Long id) {
        Optional<LandLordEntity> landLordFoundOptional = Optional.ofNullable(landLordRepository.findById(id))
                .orElseThrow(() -> new NotFoundException("Land lord not found!"));
        return landLordConverter.convertToDTO(landLordFoundOptional.get());
    }

    @Override
    public Map<String, String> getLandLordStatus() {
        Map<String, String> results = new HashMap<>();
        for (LandLordStatus item : LandLordStatus.values()) {
            results.put(item.toString(), item.getLandLordStatusValue());
        }

        return results;
    }

    @Override
    public List<StaffAssignResponse> getStaffsOfLandLord(Long landLordId) {
        List<UserEntity> allStaffs = userRepository.findByStatusAndRoles_Code(1, SystemConstant.STAFF_ROLE);
        List<UserEntity> staffsOfLandLord = userRepository.findByLandLords_Id(landLordId);
        List<StaffAssignResponse> results = allStaffs.stream().map(item ->
                userConverter.convertToStaffAssignResponse(item, staffsOfLandLord)).collect(Collectors.toList());

        return results;
    }

    @Override
    public List<LandLordSearchResponse> getLandlords(LandLordSearchRequest request, Pageable pageable) {
        if (SecurityUtils.getAuthorities().contains(SystemConstant.STAFF_ROLE)) {
            request.setStaffId(SecurityUtils.getPrincipal().getId());
        }

        LandLordSearchBuilder builder = initLandLordSearchBuilder(request);

        List<LandLordEntity> landLordEntities = landLordRepository.findByCondition(builder, pageable);

        List<LandLordSearchResponse> results = landLordEntities.stream().map(item ->
                landLordConverter.convertToSearchResponse(item)).collect(Collectors.toList());

        return results;
    }

    @Override
    public int getTotalItems(LandLordSearchRequest request) {
        int result = 0;
        if (Objects.nonNull(request)) {
            LandLordSearchBuilder builder = initLandLordSearchBuilder(request);
            return (int) landLordRepository.countByCondition(builder);
        }

        return result;
    }

    @Override
    @Transactional
    public void delete(long[] ids) {
        landLordRepository.deleteByIdIn(ids);
    }

    @Override
    public List<TransactionResponse> getTransactionsOfLandLord(Long landLordId) {
        Optional<LandLordEntity> landLordFound = Optional.ofNullable(landLordRepository.findById(landLordId))
                .orElseThrow(() -> new NotFoundException("Land lord not found!"));

        List<TransactionResponse> results = landLordFound.get().getTransactionEntities().stream()
                .map(item -> transactionConverter.convertToLandLordResponse(item)).collect(Collectors.toList());

        return results;
    }

    @Override
    @Transactional
    public void createTransaction(Long landLordId, TransactionRequest request) {
        if (ValidateUtils.isValidProperty(landLordId) && Objects.nonNull(request)) {

            Optional<LandLordEntity> landLordFoundOptional = Optional.ofNullable(landLordRepository.findById(landLordId))
                    .orElseThrow(() -> new NotFoundException("Land lord not found!"));

            Optional<UserEntity> userFoundOptional = Optional.ofNullable(userRepository.findById(SecurityUtils.getPrincipal().getId()))
                    .orElseThrow(() -> new NotFoundException("User not found!"));

            LandLordTransactionTypeEntity typeFound = Optional.ofNullable(
                            landLordTransactionTypeRepository.findByCode(request.getTransactionCode()))
                    .orElseThrow(() -> new NotFoundException("Transaction type not found!"));

            LandLordTransactionEntity landLordTransactionEntity = transactionConverter.convertToLandLordEntity(request);
            landLordTransactionEntity.setLandLord(landLordFoundOptional.get());
            landLordTransactionEntity.setUser(userFoundOptional.get());
            landLordTransactionEntity.setTransactionType(typeFound);

            landLordTransactionRepository.save(landLordTransactionEntity);
        }
    }

    private LandLordSearchBuilder initLandLordSearchBuilder(LandLordSearchRequest request) {
        LandLordSearchBuilder results = new LandLordSearchBuilder.Builder().setFullName(request.getFullName())
                .setEmail(request.getEmail()).setPhone(request.getPhone())
                .setStaffId(request.getStaffId()).setStatusCode(request.getStatusCode()).build();

        return results;
    }
}
