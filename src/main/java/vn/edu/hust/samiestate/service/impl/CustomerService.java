package vn.edu.hust.samiestate.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.hust.samiestate.builder.CustomerSearchBuilder;
import vn.edu.hust.samiestate.constant.SystemConstant;
import vn.edu.hust.samiestate.converter.CustomerConverter;
import vn.edu.hust.samiestate.converter.TransactionConverter;
import vn.edu.hust.samiestate.converter.UserConverter;
import vn.edu.hust.samiestate.dto.CustomerDTO;
import vn.edu.hust.samiestate.dto.request.AssignmentCustomerRequest;
import vn.edu.hust.samiestate.dto.request.CustomerSearchRequest;
import vn.edu.hust.samiestate.dto.request.TransactionRequest;
import vn.edu.hust.samiestate.dto.response.CustomerSearchResponse;
import vn.edu.hust.samiestate.dto.response.StaffAssignResponse;
import vn.edu.hust.samiestate.dto.response.TransactionResponse;
import vn.edu.hust.samiestate.entity.CustomerEntity;
import vn.edu.hust.samiestate.entity.TransactionEntity;
import vn.edu.hust.samiestate.entity.TransactionTypeEntity;
import vn.edu.hust.samiestate.entity.UserEntity;
import vn.edu.hust.samiestate.exception.FieldNullOrEmptyException;
import vn.edu.hust.samiestate.exception.NotFoundException;
import vn.edu.hust.samiestate.repository.CustomerRepository;
import vn.edu.hust.samiestate.repository.TransactionTypeRepository;
import vn.edu.hust.samiestate.repository.UserRepository;
import vn.edu.hust.samiestate.security.utils.SecurityUtils;
import vn.edu.hust.samiestate.service.ICustomerService;
import vn.edu.hust.samiestate.utils.ValidateUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerConverter customerConverter;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private TransactionTypeRepository transactionTypeRepository;

    @Autowired
    private TransactionConverter transactionConverter;

    @Override
    @Transactional
    public CustomerDTO save(CustomerDTO customerDTO) {
        if (!ValidateUtils.isValidProperty(customerDTO.getName())) {
            throw new FieldNullOrEmptyException("Field name is null or empty");
        }

        if (Objects.nonNull(customerDTO)) {
            Long customerId = customerDTO.getId();

            CustomerEntity customerEntity = customerConverter.convertToEntity(customerDTO);

            if (customerId != null) {
                Optional<CustomerEntity> customerFoundOptional = Optional.ofNullable(customerRepository.findById(customerId))
                        .orElseThrow(() -> new NotFoundException("Customer not found!"));
                customerEntity.setUsers(customerFoundOptional.get().getUsers());
            }

            return customerConverter.convertToDTO(customerRepository.save(customerEntity));

        }
        return null;
    }

    @Override
    public CustomerDTO findCustomerById(Long id) {
        return customerConverter.convertToDTO(customerRepository.findById(id).get());
    }

    @Override
    public List<CustomerSearchResponse> getCustomers(CustomerSearchRequest request, Pageable pageable) {

        if (SecurityUtils.getAuthorities().contains(SystemConstant.STAFF_ROLE)) {
            request.setStaffId(SecurityUtils.getPrincipal().getId());
        }

        CustomerSearchBuilder builder = initCustomerSearchBuilder(request);

        List<CustomerEntity> customerEntities = customerRepository.findByCondition(builder, pageable);

        List<CustomerSearchResponse> results = customerEntities.stream().map(item ->
                customerConverter.convertToCustomerSearchResponse(item)).collect(Collectors.toList());

        return results;

    }

    @Override
    public int getTotalItems(CustomerSearchRequest request) {
        int result = 0;
        if (Objects.nonNull(request)) {
            CustomerSearchBuilder builder = initCustomerSearchBuilder(request);;
            return (int) customerRepository.countByCondition(builder);
        }
        return result;
    }

    @Override
    public List<StaffAssignResponse> getStaffsOfCustomer(Long customerId) {

        List<UserEntity> allStaffs = userRepository.findByStatusAndRoles_Code(1, SystemConstant.STAFF_ROLE);
        List<UserEntity> staffOfCustomer = userRepository.findByCustomers_Id(customerId);

        List<StaffAssignResponse> results = allStaffs.stream().map(item ->
                userConverter.convertToStaffAssignResponse(item, staffOfCustomer)).collect(Collectors.toList());

        return results;
    }

    @Override
    public List<TransactionResponse> getTransactionsOfCustomer(Long customerId) {

        Optional<CustomerEntity> customerFound = Optional.ofNullable(customerRepository.findById(customerId))
                .orElseThrow(() -> new NotFoundException("Customer not found!"));

        List<TransactionResponse> results = customerFound.get().getTransactionEntities().stream()
                .map(item -> transactionConverter.convertToResponse(item)).collect(Collectors.toList());

        return results;
    }

    @Override
    @Transactional
    public void assignCustomerToStaff(AssignmentCustomerRequest request) {

        if (Objects.nonNull(request)) {

            List<Long> staffIds = new ArrayList<>(Arrays.asList(request.getStaffIds()));
            Long customerId = request.getCustomerId();

            if (ValidateUtils.isValidProperty(customerId)) {
                Optional<CustomerEntity> customerFoundOptional = Optional.ofNullable(customerRepository.findById(customerId))
                        .orElseThrow(() -> new NotFoundException("Customer not found"));

                List<UserEntity> newStaffs = staffIds.stream().map(item -> {
                    Optional<UserEntity> userFoundOptional = Optional.ofNullable(userRepository.findById(item))
                            .orElseThrow(() -> new NotFoundException("User not found"));
                    return userFoundOptional.get();
                }).collect(Collectors.toList());

                customerFoundOptional.get().setUsers(newStaffs);

                customerRepository.save(customerFoundOptional.get());
            }

        }
    }

    @Override
    @Transactional
    public void delete(long[] ids) {
        customerRepository.deleteByIdIn(ids);
    }

    @Override
    @Transactional
    public void createTransaction(Long customerId, TransactionRequest request) {

        if (ValidateUtils.isValidProperty(customerId) && Objects.nonNull(request)) {

            Optional<CustomerEntity> customerFoundOptional = Optional.ofNullable(customerRepository.findById(customerId))
                    .orElseThrow(() -> new NotFoundException("Customer not found!"));

            Optional<UserEntity> userFoundOptional = Optional.ofNullable(userRepository.findById(SecurityUtils.getPrincipal().getId()))
                    .orElseThrow(() -> new NotFoundException("User not found!"));

            TransactionTypeEntity typeFound = Optional.ofNullable(transactionTypeRepository.findByCode(request.getTransactionCode()))
                    .orElseThrow(() -> new NotFoundException("Transaction type not found!"));

            List<TransactionEntity> transactionList = new ArrayList<>();

            TransactionEntity transactionEntity = transactionConverter.convertToEntity(request);
            transactionEntity.setCustomer(customerFoundOptional.get());
            transactionEntity.setUser(userFoundOptional.get());
            transactionEntity.setTransactionType(typeFound);
            transactionList.add(transactionEntity);

            customerFoundOptional.get().setTransactionEntities(transactionList);

            customerRepository.save(customerFoundOptional.get());
        }
    }


    private CustomerSearchBuilder initCustomerSearchBuilder(CustomerSearchRequest request) {
        CustomerSearchBuilder results = new CustomerSearchBuilder.Builder().setName(request.getName())
                .setEmail(request.getEmail()).setPhone(request.getPhone())
                .setStaffId(request.getStaffId()).setStatusCode(request.getStatusCode()).build();

        return results;
    }
}
