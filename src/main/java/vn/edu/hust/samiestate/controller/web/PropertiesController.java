package vn.edu.hust.samiestate.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.hust.samiestate.constant.SystemConstant;
import vn.edu.hust.samiestate.dto.BuildingDTO;
import vn.edu.hust.samiestate.dto.request.BuildingSearchRequest;
import vn.edu.hust.samiestate.service.IDistrictService;
import vn.edu.hust.samiestate.service.impl.BuildingService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller(value = "PropertiesControllerOfWeb")
public class PropertiesController {

    @Autowired
    private BuildingService buildingService;

    @Autowired
    private IDistrictService districtService;

    @RequestMapping(value = "/properties", method = RequestMethod.GET)
    public ModelAndView getBuildings(@ModelAttribute(SystemConstant.MODEL) BuildingDTO model,
                                          HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("web/properties");
        List<BuildingDTO> buildingDTOList = buildingService.getLatestBuildings(
                PageRequest.of(model.getPage() - 1, model.getMaxPageItems()));
        model.setListResult(buildingDTOList);
        model.setTotalItems(buildingService.getTotalItems(new BuildingSearchRequest()));
        mav.addObject("districtsMap", districtService.getDistrict());
        mav.addObject("modelSearch", new BuildingSearchRequest());
        return mav;
    }

}
