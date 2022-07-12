package vn.edu.hust.samiestate.service.impl;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.hust.samiestate.builder.CustomerSearchBuilder;
import vn.edu.hust.samiestate.constant.SystemConstant;
import vn.edu.hust.samiestate.converter.CustomerConverter;
import vn.edu.hust.samiestate.converter.TransactionConverter;
import vn.edu.hust.samiestate.converter.UserConverter;
import vn.edu.hust.samiestate.dto.CustomerDTO;
import vn.edu.hust.samiestate.dto.request.CustomerSearchRequest;
import vn.edu.hust.samiestate.dto.request.TransactionRequest;
import vn.edu.hust.samiestate.dto.response.CustomerSearchResponse;
import vn.edu.hust.samiestate.dto.response.StaffAssignResponse;
import vn.edu.hust.samiestate.dto.response.TransactionResponse;
import vn.edu.hust.samiestate.entity.CustomerEntity;
import vn.edu.hust.samiestate.entity.CustomerTransactionEntity;
import vn.edu.hust.samiestate.entity.CustomerTransactionTypeEntity;
import vn.edu.hust.samiestate.entity.UserEntity;
import vn.edu.hust.samiestate.enums.CustomerStatus;
import vn.edu.hust.samiestate.exception.NotFoundException;
import vn.edu.hust.samiestate.repository.CustomerRepository;
import vn.edu.hust.samiestate.repository.CustomerTransactionRepository;
import vn.edu.hust.samiestate.repository.CustomerTransactionTypeRepository;
import vn.edu.hust.samiestate.repository.UserRepository;
import vn.edu.hust.samiestate.security.utils.SecurityUtils;
import vn.edu.hust.samiestate.service.ICustomerService;
import vn.edu.hust.samiestate.utils.ValidateUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CustomerService implements ICustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerConverter customerConverter;
    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final CustomerTransactionTypeRepository customerTransactionTypeRepository;
    private final TransactionConverter transactionConverter;
    private final CustomerTransactionRepository customerTransactionRepository;

    public CustomerService(CustomerRepository customerRepository, CustomerConverter customerConverter,
                           UserRepository userRepository, UserConverter userConverter,
                           CustomerTransactionTypeRepository customerTransactionTypeRepository,
                           TransactionConverter transactionConverter,
                           CustomerTransactionRepository customerTransactionRepository) {
        this.customerRepository = customerRepository;
        this.customerConverter = customerConverter;
        this.userRepository = userRepository;
        this.userConverter = userConverter;
        this.customerTransactionTypeRepository = customerTransactionTypeRepository;
        this.transactionConverter = transactionConverter;
        this.customerTransactionRepository = customerTransactionRepository;
    }

    @Override
    @Transactional
    public CustomerDTO save(CustomerDTO customerDTO) {

        if (Objects.nonNull(customerDTO)) {
            CustomerEntity customerEntity = customerConverter.convertToEntity(customerDTO);

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
                .map(item -> transactionConverter.convertToCustomerResponse(item)).collect(Collectors.toList());

        return results;
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

            CustomerTransactionTypeEntity typeFound = Optional.ofNullable(
                    customerTransactionTypeRepository.findByCode(request.getTransactionCode()))
                    .orElseThrow(() -> new NotFoundException("Transaction type not found!"));

            CustomerTransactionEntity customerTransactionEntity = transactionConverter.convertToCustomerEntity(request);
            customerTransactionEntity.setCustomer(customerFoundOptional.get());
            customerTransactionEntity.setUser(userFoundOptional.get());
            customerTransactionEntity.setTransactionType(typeFound);

            customerTransactionRepository.save(customerTransactionEntity);
        }
    }

    @Override
    public Map<String, String> getCustomerStatus() {
        Map<String, String> results = new HashMap<>();
        for (CustomerStatus item : CustomerStatus.values()) {
            results.put(item.toString(), item.getCustomerStatusValue());
        }

        return results;
    }


    private CustomerSearchBuilder initCustomerSearchBuilder(CustomerSearchRequest request) {
        CustomerSearchBuilder results = new CustomerSearchBuilder.Builder().setFullName(request.getFullName())
                .setEmail(request.getEmail()).setPhone(request.getPhone())
                .setStaffId(request.getStaffId()).setStatusCode(request.getStatusCode()).build();

        return results;
    }
}
