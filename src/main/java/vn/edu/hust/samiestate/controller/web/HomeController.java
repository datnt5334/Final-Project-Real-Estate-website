package vn.edu.hust.samiestate.controller.web;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.hust.samiestate.constant.SystemConstant;
import vn.edu.hust.samiestate.dto.request.BuildingSearchRequest;
import vn.edu.hust.samiestate.service.impl.BuildingService;
import vn.edu.hust.samiestate.service.impl.CategoryService;
import vn.edu.hust.samiestate.service.impl.DistrictService;
import vn.edu.hust.samiestate.service.impl.NewsService;

@Controller(value = "homeControllerOfWeb")
public class HomeController {

    private final BuildingService buildingService;
    private final DistrictService districtService;
    private final CategoryService categoryService;
    private final NewsService newsService;

    public HomeController(BuildingService buildingService,
                          DistrictService districtService,
                          CategoryService categoryService,
                          NewsService newsService) {
        this.buildingService = buildingService;
        this.districtService = districtService;
        this.categoryService = categoryService;
        this.newsService = newsService;
    }

    @RequestMapping(value = "/trang-chu", method = RequestMethod.GET)
    public ModelAndView homePage() {
        ModelAndView mav = new ModelAndView("web/home");
        mav.addObject("latestBuilding", buildingService.getLatestBuildings(
                PageRequest.of(0, 4)));
        mav.addObject("buildingRankA", buildingService.getBuildingByLevel("A",
                PageRequest.of(0, 3)));
        mav.addObject("buildingRankB", buildingService.getBuildingByLevel("B",
                PageRequest.of(0, 3)));
        mav.addObject("buildingRankC", buildingService.getBuildingByLevel("C",
                PageRequest.of(0, 3)));
        mav.addObject("latestNews", newsService.getLatestNews(
                PageRequest.of(0, 4)));
        mav.addObject(SystemConstant.DISTRICT_MAP, districtService.getDistrict());
        mav.addObject(SystemConstant.MODEL_SEARCH, new BuildingSearchRequest());
        mav.addObject(SystemConstant.CATEGORIES_MAP, categoryService.getCategory());
        return mav;
    }

}
