package vn.edu.hust.samiestate.service;

import org.springframework.data.domain.Pageable;
import vn.edu.hust.samiestate.dto.CustomerDTO;
import vn.edu.hust.samiestate.dto.request.CustomerSearchRequest;
import vn.edu.hust.samiestate.dto.request.TransactionRequest;
import vn.edu.hust.samiestate.dto.response.CustomerSearchResponse;
import vn.edu.hust.samiestate.dto.response.StaffAssignResponse;
import vn.edu.hust.samiestate.dto.response.TransactionResponse;

import java.util.List;
import java.util.Map;

public interface ICustomerService {

    CustomerDTO save(CustomerDTO customerDTO);
    CustomerDTO findCustomerById(Long id);
    List<CustomerSearchResponse> getCustomers(CustomerSearchRequest request, Pageable pageable);
    int getTotalItems(CustomerSearchRequest request);
    List<StaffAssignResponse> getStaffsOfCustomer(Long customerId);
    List<TransactionResponse> getTransactionsOfCustomer(Long customerId);
    void delete(long[] ids);
    void createTransaction(Long customerId, TransactionRequest request);
    Map<String, String> getCustomerStatus();
}
