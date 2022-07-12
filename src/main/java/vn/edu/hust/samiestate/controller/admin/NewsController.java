package vn.edu.hust.samiestate.controller.admin;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.hust.samiestate.constant.SystemConstant;
import vn.edu.hust.samiestate.dto.NewsDTO;
import vn.edu.hust.samiestate.dto.request.NewsSearchRequest;
import vn.edu.hust.samiestate.dto.response.NewsSearchResponse;
import vn.edu.hust.samiestate.dto.response.UserSearchResponse;
import vn.edu.hust.samiestate.service.impl.CategoryService;
import vn.edu.hust.samiestate.service.impl.NewsService;
import vn.edu.hust.samiestate.utils.ResponseUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller(value = "newsControllerOfAdmin")
public class NewsController {

    private final NewsService newsService;
    private final CategoryService categoryService;

    public NewsController(NewsService newsService, CategoryService categoryService) {
        this.newsService = newsService;
        this.categoryService = categoryService;
    }

    @RequestMapping(value = "/admin/news/list", method = RequestMethod.GET)
    public ModelAndView getNews(@ModelAttribute(SystemConstant.MODEL) NewsSearchRequest model,
                                HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/news/list");
        List<NewsSearchResponse> newsResponse = newsService.getNews(model,
                PageRequest.of(model.getPage() - 1, model.getMaxPageItems()));
        model.setListResult(newsResponse);
        model.setTotalItems(newsService.getTotalItems(model));
        mav.addObject(SystemConstant.CATEGORIES_MAP, categoryService.getCategory());
        ResponseUtils.initMessageResponse(mav, request);
        return mav;
    }

    @RequestMapping(value = "/admin/news/edit", method = RequestMethod.GET)
    public ModelAndView addNews(@ModelAttribute(SystemConstant.MODEL) NewsDTO model, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/news/edit");
        mav.addObject(SystemConstant.CATEGORIES_MAP, categoryService.getCategory());
        ResponseUtils.initMessageResponse(mav, request);
        return mav;
    }

    @RequestMapping(value = "/admin/news/edit-{id}", method = RequestMethod.GET)
    public ModelAndView updateNews(@PathVariable("id") Long id, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/news/edit");
        NewsDTO model = newsService.findNewsById(id);
        mav.addObject(SystemConstant.CATEGORIES_MAP, categoryService.getCategory());
        mav.addObject(SystemConstant.MODEL, model);
        ResponseUtils.initMessageResponse(mav, request);
        return mav;
    }
}
