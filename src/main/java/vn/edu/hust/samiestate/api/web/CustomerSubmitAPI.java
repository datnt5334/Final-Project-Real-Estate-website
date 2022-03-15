package vn.edu.hust.samiestate.api.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.hust.samiestate.dto.CustomerDTO;
import vn.edu.hust.samiestate.service.impl.CustomerService;

@RestController
@RequestMapping("/api/customer-form")
public class CustomerSubmitAPI {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public CustomerDTO createCustomerFromSubmit(@RequestBody CustomerDTO customerDTO) {
        try {
            return customerService.save(customerDTO);
        } catch (Exception e) {
            throw e;
        }
    }

}
