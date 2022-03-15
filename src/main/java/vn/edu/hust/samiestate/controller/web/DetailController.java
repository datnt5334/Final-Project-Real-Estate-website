package vn.edu.hust.samiestate.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.hust.samiestate.constant.SystemConstant;
import vn.edu.hust.samiestate.dto.BuildingDTO;
import vn.edu.hust.samiestate.dto.request.BuildingSearchRequest;
import vn.edu.hust.samiestate.service.IDistrictService;
import vn.edu.hust.samiestate.service.impl.BuildingService;

@Controller(value = "detailControllerOfWeb")
public class DetailController {

    @Autowired
    private BuildingService buildingService;

    @Autowired
    private IDistrictService districtService;

    @RequestMapping(value = "/chi-tiet-{id}", method = RequestMethod.GET)
    public ModelAndView getDetail(@PathVariable("id") Long id) {
        ModelAndView mav = new ModelAndView("web/detail");
        BuildingDTO model = buildingService.findBuildingById(id);
        mav.addObject("districtsMap", districtService.getDistrict());
        mav.addObject("modelSearch", new BuildingSearchRequest());
        mav.addObject("staffs", buildingService.getAssignStaffsOfBuilding(id));
        mav.addObject(SystemConstant.MODEL, model);
        return mav;
    }
}
