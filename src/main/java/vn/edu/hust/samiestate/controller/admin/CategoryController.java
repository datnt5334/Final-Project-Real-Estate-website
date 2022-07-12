package vn.edu.hust.samiestate.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.hust.samiestate.service.impl.CategoryService;
import vn.edu.hust.samiestate.utils.ResponseUtils;

import javax.servlet.http.HttpServletRequest;

@Controller(value = "categoryControllerOfAdmin")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping(value = "/admin/category/list", method = RequestMethod.GET)
    public ModelAndView getCategory(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/category/list");
        mav.addObject("categoryList", categoryService.getCategoryList());
        ResponseUtils.initMessageResponse(mav, request);
        return mav;
    }
}
