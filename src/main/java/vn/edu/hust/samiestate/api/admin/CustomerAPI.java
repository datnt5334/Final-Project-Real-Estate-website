package vn.edu.hust.samiestate.api.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hust.samiestate.dto.CustomerDTO;
import vn.edu.hust.samiestate.dto.request.TransactionRequest;
import vn.edu.hust.samiestate.service.impl.CustomerService;

@RestController
@RequestMapping("/api/customer")
public class CustomerAPI {

    private final CustomerService customerService;

    public CustomerAPI(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public CustomerDTO createCustomer(@RequestBody CustomerDTO customerDTO) {
        try {
            return customerService.save(customerDTO);
        } catch (Exception e) {
            throw e;
        }
    }

    @PutMapping("/{id}")
    public CustomerDTO updateCustomer(@RequestBody CustomerDTO customerDTO) {
        try {
            return customerService.save(customerDTO);
        } catch (Exception e) {
            throw e;
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteCustomer(@RequestBody long[] idList) {
        if (idList.length > 0) {
            customerService.delete(idList);
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{customerId}/transaction")
    public void createTransaction(@PathVariable("customerId") Long customerId,
                                  @RequestBody TransactionRequest request) {
        try {
            customerService.createTransaction(customerId, request);
        } catch (Exception e) {
            throw e;
        }
    }
}
