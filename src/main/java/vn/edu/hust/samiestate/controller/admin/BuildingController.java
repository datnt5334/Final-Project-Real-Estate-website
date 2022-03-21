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
import vn.edu.hust.samiestate.dto.BuildingDTO;
import vn.edu.hust.samiestate.dto.request.BuildingSearchRequest;
import vn.edu.hust.samiestate.dto.response.BuildingSearchResponse;
import vn.edu.hust.samiestate.service.IBuildingService;
import vn.edu.hust.samiestate.service.IDistrictService;
import vn.edu.hust.samiestate.service.IUserService;
import vn.edu.hust.samiestate.utils.ResponseUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller(value = "buildingsControllerOfAdmin")
public class BuildingController {

    @Autowired
    private IDistrictService districtService;

    @Autowired
    private IBuildingService buildingService;

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/admin/building/list", method = RequestMethod.GET)
    public ModelAndView getBuilding(@ModelAttribute(SystemConstant.MODEL) BuildingSearchRequest model,
                                    HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/building/list");
        List<BuildingSearchResponse> responseList = buildingService.getBuildings(model,
                PageRequest.of(model.getPage() - 1, model.getMaxPageItems()));
        model.setListResult(responseList);
        model.setTotalItems(buildingService.getTotalItems(model));
        mav.addObject(SystemConstant.DISTRICT_MAP, districtService.getDistrict());
        mav.addObject(SystemConstant.STAFFS_MAP, userService.getStaffMaps());
        ResponseUtils.initMessageResponse(mav, request);
        return mav;
    }

    @RequestMapping(value = "/admin/building/edit", method = RequestMethod.GET)
    public ModelAndView addBuilding(@ModelAttribute(SystemConstant.MODEL) BuildingDTO model, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/building/edit");
        mav.addObject(SystemConstant.DISTRICT_MAP, districtService.getDistrict());
        ResponseUtils.initMessageResponse(mav, request);
        return mav;
    }

    @RequestMapping(value = "/admin/building/edit-{id}", method = RequestMethod.GET)
    public ModelAndView updateBuilding(@PathVariable("id") Long id, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/building/edit");
        BuildingDTO model = buildingService.findBuildingById(id);
        mav.addObject(SystemConstant.DISTRICT_MAP, districtService.getDistrict());
        mav.addObject(SystemConstant.MODEL, model);
        ResponseUtils.initMessageResponse(mav, request);
        return mav;
    }
}
