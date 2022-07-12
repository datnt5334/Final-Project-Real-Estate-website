package vn.edu.hust.samiestate.service;

import vn.edu.hust.samiestate.dto.CategoryDTO;

import java.util.List;
import java.util.Map;

public interface ICategoryService {

    Map<String, String> getCategory();
    List<CategoryDTO> getCategoryList();
    CategoryDTO getCategoryByCode(String categoryCode);
    void save(CategoryDTO categoryDTO);
    void delete(long[] ids);
}
