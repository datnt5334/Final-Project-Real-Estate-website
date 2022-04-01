package vn.edu.hust.samiestate.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.hust.samiestate.constant.SystemConstant;
import vn.edu.hust.samiestate.dto.request.BuildingSearchRequest;
import vn.edu.hust.samiestate.dto.response.BuildingSearchResponse;
import vn.edu.hust.samiestate.service.IDistrictService;
import vn.edu.hust.samiestate.service.impl.BuildingService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller(value = "SearchPropertiesControllerOfWeb")
public class SearchPropertiesController {

    @Autowired
    private BuildingService buildingService;

    @Autowired
    private IDistrictService districtService;

    @RequestMapping(value = "/properties-search", method = RequestMethod.GET)
    public ModelAndView getSearchBuildings(@ModelAttribute("modelSearch") BuildingSearchRequest modelSearch,
                                     HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("web/search");
        List<BuildingSearchResponse> responseList = buildingService.getSearchBuildings(modelSearch,
                PageRequest.of(modelSearch.getPage() - 1, modelSearch.getMaxPageItems()));
        modelSearch.setListResult(responseList);
        modelSearch.setTotalItems(buildingService.getTotalItems(modelSearch));
        mav.addObject(SystemConstant.DISTRICT_MAP, districtService.getDistrict());
        return mav;
    }
}
