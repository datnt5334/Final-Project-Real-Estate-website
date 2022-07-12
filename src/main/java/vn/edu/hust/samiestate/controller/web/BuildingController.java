package vn.edu.hust.samiestate.controller.web;

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
import vn.edu.hust.samiestate.service.impl.BuildingService;
import vn.edu.hust.samiestate.service.impl.CategoryService;
import vn.edu.hust.samiestate.service.impl.DistrictService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller(value = "BuildingControllerOfWeb")
public class BuildingController {

    private final BuildingService buildingService;
    private final DistrictService districtService;
    private final CategoryService categoryService;

    public BuildingController(BuildingService buildingService, DistrictService districtService, CategoryService categoryService) {
        this.buildingService = buildingService;
        this.districtService = districtService;
        this.categoryService = categoryService;
    }

    @RequestMapping(value = "/properties", method = RequestMethod.GET)
    public ModelAndView getBuildings(@ModelAttribute(SystemConstant.MODEL) BuildingDTO model,
                                          HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("web/building/properties");
        List<BuildingDTO> buildingDTOList = buildingService.getLatestBuildings(
                PageRequest.of(model.getPage() - 1, model.getMaxPageItems()));
        model.setListResult(buildingDTOList);
        model.setTotalItems(buildingService.getTotalItems(new BuildingSearchRequest()));
        mav.addObject(SystemConstant.DISTRICT_MAP, districtService.getDistrict());
        mav.addObject(SystemConstant.MODEL_SEARCH, new BuildingSearchRequest());
        mav.addObject(SystemConstant.CATEGORIES_MAP, categoryService.getCategory());
        return mav;
    }

    @RequestMapping(value = "/properties-search", method = RequestMethod.GET)
    public ModelAndView getSearchBuildings(@ModelAttribute("modelSearch") BuildingSearchRequest modelSearch,
                                           HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("web/search");
        List<BuildingSearchResponse> responseList = buildingService.getSearchBuildings(modelSearch,
                PageRequest.of(modelSearch.getPage() - 1, modelSearch.getMaxPageItems()));
        modelSearch.setListResult(responseList);
        modelSearch.setTotalItems(buildingService.getTotalItems(modelSearch));
        mav.addObject(SystemConstant.DISTRICT_MAP, districtService.getDistrict());
        mav.addObject(SystemConstant.CATEGORIES_MAP, categoryService.getCategory());
        return mav;
    }

    @RequestMapping(value = "/building-detail-{id}", method = RequestMethod.GET)
    public ModelAndView getDetail(@PathVariable("id") Long id) {
        ModelAndView mav = new ModelAndView("web/building/detail");
        BuildingDTO model = buildingService.findBuildingById(id);
        mav.addObject(SystemConstant.DISTRICT_MAP, districtService.getDistrict());
        mav.addObject(SystemConstant.CATEGORIES_MAP, categoryService.getCategory());
        mav.addObject(SystemConstant.MODEL_SEARCH, new BuildingSearchRequest());
        mav.addObject("staffs", buildingService.getAssignStaffsOfBuilding(id));
        mav.addObject(SystemConstant.MODEL, model);
        return mav;
    }

}
