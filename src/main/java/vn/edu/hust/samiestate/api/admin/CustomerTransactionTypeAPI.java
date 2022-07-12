package vn.edu.hust.samiestate.api.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hust.samiestate.dto.CustomerTransactionTypeDTO;
import vn.edu.hust.samiestate.service.impl.CustomerTransactionTypeService;

@RestController
@RequestMapping("/api/customer-transactionType")
public class CustomerTransactionTypeAPI {

    private final CustomerTransactionTypeService customerTransactionTypeService;

    public CustomerTransactionTypeAPI(CustomerTransactionTypeService customerTransactionTypeService) {
        this.customerTransactionTypeService = customerTransactionTypeService;
    }

    @PostMapping
    public void createTransactionType(@RequestBody CustomerTransactionTypeDTO customerTransactionTypeDTO) {
        try {
            customerTransactionTypeService.save(customerTransactionTypeDTO);
        } catch (Exception e) {
            throw e;
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteTransactionType(@RequestBody long[] idList) {
        if (idList.length > 0) {
            customerTransactionTypeService.delete(idList);
        }
        return ResponseEntity.noContent().build();
    }
}
