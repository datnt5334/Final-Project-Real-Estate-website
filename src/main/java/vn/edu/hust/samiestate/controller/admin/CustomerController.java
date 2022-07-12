package vn.edu.hust.samiestate.controller.admin;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.hust.samiestate.constant.SystemConstant;
import vn.edu.hust.samiestate.dto.CustomerDTO;
import vn.edu.hust.samiestate.dto.request.CustomerSearchRequest;
import vn.edu.hust.samiestate.dto.response.CustomerSearchResponse;
import vn.edu.hust.samiestate.service.impl.CustomerService;
import vn.edu.hust.samiestate.service.impl.CustomerTransactionTypeService;
import vn.edu.hust.samiestate.service.impl.UserService;
import vn.edu.hust.samiestate.utils.ResponseUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller(value = "customerControllerOfAdmin")
public class CustomerController {

    private final CustomerService customerService;
    private final UserService userService;
    private final CustomerTransactionTypeService customerTransactionTypeService;

    public CustomerController(CustomerService customerService,
                              UserService userService,
                              CustomerTransactionTypeService customerTransactionTypeService) {
        this.customerService = customerService;
        this.userService = userService;
        this.customerTransactionTypeService = customerTransactionTypeService;
    }

    @RequestMapping(value = "/admin/customer/list", method = RequestMethod.GET)
    public ModelAndView getCustomer(@ModelAttribute(SystemConstant.MODEL) CustomerSearchRequest model,
                                    HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/customer/list");
        List<CustomerSearchResponse> responseList = customerService.getCustomers(model,
                PageRequest.of(model.getPage() - 1, model.getMaxPageItems()));
        model.setListResult(responseList);
        model.setTotalItems(customerService.getTotalItems(model));
        mav.addObject(SystemConstant.STAFFS_MAP, userService.getStaffMaps());
        mav.addObject(SystemConstant.CUSTOMER_STATUS_MAP, customerService.getCustomerStatus());
        ResponseUtils.initMessageResponse(mav, request);
        return mav;
    }

    @RequestMapping(value = "/admin/customer/edit", method = RequestMethod.GET)
    public ModelAndView addCustomer(@ModelAttribute(SystemConstant.MODEL) CustomerDTO model, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/customer/edit");
        mav.addObject(SystemConstant.CUSTOMER_STATUS_MAP, customerService.getCustomerStatus());
        ResponseUtils.initMessageResponse(mav, request);
        return mav;
    }

    @RequestMapping(value = "/admin/customer/edit-{id}", method = RequestMethod.GET)
    public ModelAndView updateCustomer(@PathVariable("id") Long id, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/customer/edit");
        CustomerDTO model = customerService.findCustomerById(id);
        mav.addObject(SystemConstant.CUSTOMER_STATUS_MAP, customerService.getCustomerStatus());
        mav.addObject(SystemConstant.STAFFS_ASSIGN_CUSTOMER, customerService.getStaffsOfCustomer(id));
        mav.addObject(SystemConstant.TRANSACTION_TYPES_MAP, customerTransactionTypeService.getTransactionType());
        mav.addObject(SystemConstant.TRANSACTION_OF_CUSTOMER, customerService.getTransactionsOfCustomer(id));
        mav.addObject(SystemConstant.MODEL, model);
        ResponseUtils.initMessageResponse(mav, request);
        return mav;
    }
}
