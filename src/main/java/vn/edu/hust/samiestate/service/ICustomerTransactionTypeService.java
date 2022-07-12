package vn.edu.hust.samiestate.service;

import vn.edu.hust.samiestate.dto.CustomerTransactionTypeDTO;

import java.util.List;
import java.util.Map;

public interface ICustomerTransactionTypeService {
    Map<String, String> getTransactionType();
    List<CustomerTransactionTypeDTO> getTransactionTypeList();
    void save(CustomerTransactionTypeDTO customerTransactionTypeDTO);
    void delete(long[] ids);
}
