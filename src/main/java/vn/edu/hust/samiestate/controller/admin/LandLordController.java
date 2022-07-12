package vn.edu.hust.samiestate.controller.admin;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.hust.samiestate.constant.SystemConstant;
import vn.edu.hust.samiestate.dto.LandLordDTO;
import vn.edu.hust.samiestate.dto.request.LandLordSearchRequest;
import vn.edu.hust.samiestate.dto.response.LandLordSearchResponse;
import vn.edu.hust.samiestate.service.ILandLordService;
import vn.edu.hust.samiestate.service.impl.CustomerTransactionTypeService;
import vn.edu.hust.samiestate.service.impl.LandLordTransactionTypeService;
import vn.edu.hust.samiestate.service.impl.UserService;
import vn.edu.hust.samiestate.utils.ResponseUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller(value = "landLordControllerOfAdmin")
public class LandLordController {

    private final UserService userService;
    private final ILandLordService landLordService;
    private final LandLordTransactionTypeService landLordTransactionTypeService;

    public LandLordController(UserService userService, ILandLordService landLordService,
                              CustomerTransactionTypeService customerTransactionTypeService,
                              LandLordTransactionTypeService landLordTransactionTypeService) {
        this.userService = userService;
        this.landLordService = landLordService;
        this.landLordTransactionTypeService = landLordTransactionTypeService;
    }

    @RequestMapping(value = "/admin/landlord/list", method = RequestMethod.GET)
    public ModelAndView getLandLord(@ModelAttribute(SystemConstant.MODEL) LandLordSearchRequest model,
                                    HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/landlord/list");
        List<LandLordSearchResponse> responseList = landLordService.getLandlords(model,
                PageRequest.of(model.getPage() - 1, model.getMaxPageItems()));
        model.setListResult(responseList);
        model.setTotalItems(landLordService.getTotalItems(model));
        mav.addObject(SystemConstant.STAFFS_MAP, userService.getStaffMaps());
        mav.addObject(SystemConstant.LANDLORD_STATUS_MAP, landLordService.getLandLordStatus());
        ResponseUtils.initMessageResponse(mav, request);
        return mav;
    }

    @RequestMapping(value = "/admin/landlord/edit", method = RequestMethod.GET)
    public ModelAndView addLandLord(@ModelAttribute(SystemConstant.MODEL) LandLordDTO model, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/landlord/edit");
        mav.addObject(SystemConstant.LANDLORD_STATUS_MAP, landLordService.getLandLordStatus());
        ResponseUtils.initMessageResponse(mav, request);
        return mav;
    }

    @RequestMapping(value = "/admin/landlord/edit-{id}", method = RequestMethod.GET)
    public ModelAndView updateLandLord(@PathVariable("id") Long id, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/landlord/edit");
        LandLordDTO model = landLordService.findLandLordById(id);
        mav.addObject(SystemConstant.LANDLORD_STATUS_MAP, landLordService.getLandLordStatus());
        mav.addObject(SystemConstant.STAFFS_ASSIGN_LANDLORD, landLordService.getStaffsOfLandLord(id));
        mav.addObject(SystemConstant.TRANSACTION_TYPES_MAP, landLordTransactionTypeService.getTransactionType());
        mav.addObject(SystemConstant.TRANSACTION_OF_LANDLORD, landLordService.getTransactionsOfLandLord(id));
        mav.addObject(SystemConstant.MODEL, model);
        ResponseUtils.initMessageResponse(mav, request);
        return mav;
    }
}
