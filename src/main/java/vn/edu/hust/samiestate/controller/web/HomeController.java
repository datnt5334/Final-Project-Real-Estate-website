package vn.edu.hust.samiestate.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.hust.samiestate.constant.SystemConstant;
import vn.edu.hust.samiestate.dto.request.BuildingSearchRequest;
import vn.edu.hust.samiestate.service.IDistrictService;
import vn.edu.hust.samiestate.service.impl.BuildingService;

@Controller(value = "homeControllerOfWeb")
public class HomeController {

    @Autowired
    private BuildingService buildingService;

    @Autowired
    private IDistrictService districtService;

    @RequestMapping(value = "/trang-chu", method = RequestMethod.GET)
    public ModelAndView homePage() {
        ModelAndView mav = new ModelAndView("web/home");
        mav.addObject("buildingView", buildingService.getLatestBuildings(
                PageRequest.of(0, 3)));
        mav.addObject("latestBuilding", buildingService.getLatestBuildings(
                PageRequest.of(0, 4)));
        BuildingSearchRequest request1 = new BuildingSearchRequest();
        request1.setLevel("A");
        BuildingSearchRequest request2 = new BuildingSearchRequest();
        request2.setLevel("B");
        BuildingSearchRequest request3 = new BuildingSearchRequest();
        request3.setLevel("C");
        mav.addObject("buildingRankA", buildingService.getBuildings(request1,
                PageRequest.of(0, 3)));
        mav.addObject("buildingRankB", buildingService.getBuildings(request2,
                PageRequest.of(0, 3)));
        mav.addObject("buildingRankC", buildingService.getBuildings(request3,
                PageRequest.of(0, 3)));
        mav.addObject(SystemConstant.DISTRICT_MAP, districtService.getDistrict());
        mav.addObject(SystemConstant.MODEL_SEARCH, new BuildingSearchRequest());
        return mav;
    }

}
