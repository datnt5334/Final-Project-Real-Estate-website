package vn.edu.hust.samiestate.service;

import vn.edu.hust.samiestate.dto.TransactionTypeDTO;

import java.util.List;
import java.util.Map;

public interface ITransactionTypeService {
    Map<String, String> getTransactionType();
    List<TransactionTypeDTO> getTransactionTypeList();
    void save(TransactionTypeDTO transactionTypeDTO);
    void delete(long[] ids);
}
