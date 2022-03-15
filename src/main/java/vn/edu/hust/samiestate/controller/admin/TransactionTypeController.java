package vn.edu.hust.samiestate.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.hust.samiestate.service.impl.TransactionTypeService;
import vn.edu.hust.samiestate.utils.ResponseUtils;

import javax.servlet.http.HttpServletRequest;

@Controller(value = "transactionTypeControllerOfAdmin")
public class TransactionTypeController {

    @Autowired
    private TransactionTypeService transactionTypeService;

    @RequestMapping(value = "/admin/transaction/list", method = RequestMethod.GET)
    public ModelAndView getTransactionType(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/transaction/list");
        mav.addObject("transactionTypeList", transactionTypeService.getTransactionTypeList());
        ResponseUtils.initMessageResponse(mav, request);
        return mav;
    }
}
