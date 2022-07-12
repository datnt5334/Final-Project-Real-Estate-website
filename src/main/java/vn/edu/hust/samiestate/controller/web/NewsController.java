package vn.edu.hust.samiestate.controller.web;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.hust.samiestate.constant.SystemConstant;
import vn.edu.hust.samiestate.dto.NewsDTO;
import vn.edu.hust.samiestate.dto.request.BuildingSearchRequest;
import vn.edu.hust.samiestate.dto.request.NewsSearchRequest;
import vn.edu.hust.samiestate.dto.response.NewsSearchResponse;
import vn.edu.hust.samiestate.service.impl.CategoryService;
import vn.edu.hust.samiestate.service.impl.DistrictService;
import vn.edu.hust.samiestate.service.impl.NewsService;

import java.util.List;

@Controller(value = "NewsControllerOfWeb")
public class NewsController {

    private final NewsService newsService;
    private final DistrictService districtService;
    private final CategoryService categoryService;

    public NewsController(NewsService newsService, DistrictService districtService, CategoryService categoryService) {
        this.newsService = newsService;
        this.districtService = districtService;
        this.categoryService = categoryService;
    }

    @RequestMapping(value = "/all-news", method = RequestMethod.GET)
    public ModelAndView getNews(@ModelAttribute(SystemConstant.MODEL) NewsDTO model) {
        ModelAndView mav = new ModelAndView("web/news/list");
        List<NewsSearchResponse> newsResponse = newsService.getLatestNews(
                PageRequest.of(model.getPage() - 1, model.getMaxPageItems()));
        model.setListResult(newsResponse);
        model.setTotalItems(newsService.getTotalItems(new NewsSearchRequest()));
        mav.addObject(SystemConstant.DISTRICT_MAP, districtService.getDistrict());
        mav.addObject(SystemConstant.MODEL_SEARCH, new BuildingSearchRequest());
        mav.addObject(SystemConstant.CATEGORIES_MAP, categoryService.getCategory());
        return mav;
    }

    @RequestMapping(value = "/news", method = RequestMethod.GET)
    public ModelAndView getSearchNews(@ModelAttribute(SystemConstant.MODEL) NewsSearchRequest modelSearch) {
        ModelAndView mav = new ModelAndView("web/news/search");
        List<NewsSearchResponse> newsResponse = newsService.getNews(modelSearch,
                PageRequest.of(modelSearch.getPage() - 1, modelSearch.getMaxPageItems()));
        modelSearch.setListResult(newsResponse);
        modelSearch.setTotalItems(newsService.getTotalItems(modelSearch));
        mav.addObject(SystemConstant.DISTRICT_MAP, districtService.getDistrict());
        mav.addObject(SystemConstant.MODEL_SEARCH, new BuildingSearchRequest());
        mav.addObject(SystemConstant.CATEGORIES_MAP, categoryService.getCategory());
        mav.addObject(SystemConstant.CATEGORY, categoryService.getCategoryByCode(modelSearch.getCategoryCode()));
        return mav;
    }

    @RequestMapping(value = "/news-detail-{id}", method = RequestMethod.GET)
    public ModelAndView getDetail(@PathVariable("id") Long id) {
        ModelAndView mav = new ModelAndView("web/news/detail");
        NewsDTO model = newsService.findNewsById(id);
        mav.addObject(SystemConstant.DISTRICT_MAP, districtService.getDistrict());
        mav.addObject(SystemConstant.CATEGORIES_MAP, categoryService.getCategory());
        mav.addObject(SystemConstant.MODEL_SEARCH, new BuildingSearchRequest());
        mav.addObject(SystemConstant.MODEL, model);
        return mav;
    }
}
