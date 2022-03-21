package vn.edu.hust.samiestate.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
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
import vn.edu.hust.samiestate.service.impl.CustomerStatusService;
import vn.edu.hust.samiestate.service.impl.TransactionTypeService;
import vn.edu.hust.samiestate.service.impl.UserService;
import vn.edu.hust.samiestate.utils.ResponseUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller(value = "customerControllerOfAdmin")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private UserService userService;

    @Autowired
    private CustomerStatusService customerStatusService;

    @Autowired
    private TransactionTypeService transactionTypeService;

    @RequestMapping(value = "/admin/customer/list", method = RequestMethod.GET)
    public ModelAndView getCustomer(@ModelAttribute(SystemConstant.MODEL) CustomerSearchRequest model,
                                    HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/customer/list");
        List<CustomerSearchResponse> responseList = customerService.getCustomers(model,
                PageRequest.of(model.getPage() - 1, model.getMaxPageItems()));
        model.setListResult(responseList);
        model.setTotalItems(customerService.getTotalItems(model));
        mav.addObject(SystemConstant.STAFFS_MAP, userService.getStaffMaps());
        mav.addObject(SystemConstant.CUSTOMER_STATUS_MAP, customerStatusService.getStatus());
        ResponseUtils.initMessageResponse(mav, request);
        return mav;
    }

    @RequestMapping(value = "/admin/customer/edit", method = RequestMethod.GET)
    public ModelAndView addCustomer(@ModelAttribute(SystemConstant.MODEL) CustomerDTO model, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/customer/edit");
        mav.addObject(SystemConstant.CUSTOMER_STATUS_MAP, customerStatusService.getStatus());
        ResponseUtils.initMessageResponse(mav, request);
        return mav;
    }

    @RequestMapping(value = "/admin/customer/edit-{id}", method = RequestMethod.GET)
    public ModelAndView updateCustomer(@PathVariable("id") Long id, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/customer/edit");
        CustomerDTO model = customerService.findCustomerById(id);
        mav.addObject(SystemConstant.CUSTOMER_STATUS_MAP, customerStatusService.getStatus());
        mav.addObject("transactionTypesMap", transactionTypeService.getTransactionType());
        mav.addObject("transactionOfCustomer", customerService.getTransactionsOfCustomer(id));
        mav.addObject(SystemConstant.MODEL, model);
        ResponseUtils.initMessageResponse(mav, request);
        return mav;
    }
}
