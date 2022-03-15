package vn.edu.hust.samiestate.api.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hust.samiestate.dto.TransactionTypeDTO;
import vn.edu.hust.samiestate.service.impl.TransactionTypeService;

@RestController
@RequestMapping("/api/transaction-type")
public class TransactionTypeAPI {

    @Autowired
    private TransactionTypeService transactionTypeService;

    @PostMapping
    public void createTransactionType(@RequestBody TransactionTypeDTO transactionTypeDTO) {
        try {
            transactionTypeService.save(transactionTypeDTO);
        } catch (Exception e) {
            throw e;
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteDistrict(@RequestBody long[] idList) {
        if (idList.length > 0) {
            transactionTypeService.delete(idList);
        }
        return ResponseEntity.noContent().build();
    }
}
