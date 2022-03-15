package vn.edu.hust.samiestate.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.hust.samiestate.service.impl.DistrictService;
import vn.edu.hust.samiestate.utils.ResponseUtils;

import javax.servlet.http.HttpServletRequest;

@Controller(value = "districtControllerOfAdmin")
public class DistrictController {

    @Autowired
    private DistrictService districtService;

    @RequestMapping(value = "/admin/district/list", method = RequestMethod.GET)
    public ModelAndView getDistrict(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/district/list");
        mav.addObject("districtList", districtService.getDistrictList());
        ResponseUtils.initMessageResponse(mav, request);
        return mav;
    }
}
