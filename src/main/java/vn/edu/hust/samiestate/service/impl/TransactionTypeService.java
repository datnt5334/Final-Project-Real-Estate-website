package vn.edu.hust.samiestate.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.hust.samiestate.entity.TransactionTypeEntity;
import vn.edu.hust.samiestate.repository.TransactionTypeRepository;
import vn.edu.hust.samiestate.service.ITransactionTypeService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TransactionTypeService implements ITransactionTypeService {

    @Autowired
    private TransactionTypeRepository transactionTypeRepository;

    @Override
    public Map<String, String> getTransactionType() {
        Map<String, String> results = new HashMap<>();

        List<TransactionTypeEntity> transactionTypeEntities = transactionTypeRepository.findAll();

        for (TransactionTypeEntity item: transactionTypeEntities) {
            results.put(item.getCode(), item.getName());
        }

        return results;
    }
}
