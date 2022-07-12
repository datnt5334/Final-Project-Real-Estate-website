package vn.edu.hust.samiestate.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.hust.samiestate.constant.SystemConstant;
import vn.edu.hust.samiestate.service.impl.CustomerTransactionTypeService;
import vn.edu.hust.samiestate.service.impl.LandLordTransactionTypeService;
import vn.edu.hust.samiestate.utils.ResponseUtils;

import javax.servlet.http.HttpServletRequest;

@Controller(value = "transactionTypeControllerOfAdmin")
public class TransactionTypeController {

    private final CustomerTransactionTypeService customerTransactionTypeService;
    private final LandLordTransactionTypeService landLordTransactionTypeService;

    public TransactionTypeController(CustomerTransactionTypeService customerTransactionTypeService,
                                     LandLordTransactionTypeService landLordTransactionTypeService) {
        this.customerTransactionTypeService = customerTransactionTypeService;
        this.landLordTransactionTypeService = landLordTransactionTypeService;
    }

    @RequestMapping(value = "/admin/customer-transaction/list", method = RequestMethod.GET)
    public ModelAndView getCustomerTransactionType(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/customertransaction/list");
        mav.addObject(SystemConstant.TRANSACTION_TYPES_LIST, customerTransactionTypeService.getTransactionTypeList());
        ResponseUtils.initMessageResponse(mav, request);
        return mav;
    }

    @RequestMapping(value = "/admin/landlord-transaction/list", method = RequestMethod.GET)
    public ModelAndView getLandLordTransactionType(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/landlordtransaction/list");
        mav.addObject(SystemConstant.TRANSACTION_TYPES_LIST, landLordTransactionTypeService.getTransactionTypeList());
        ResponseUtils.initMessageResponse(mav, request);
        return mav;
    }
}
