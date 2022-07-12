package vn.edu.hust.samiestate.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.hust.samiestate.converter.CategoryConverter;
import vn.edu.hust.samiestate.dto.CategoryDTO;
import vn.edu.hust.samiestate.entity.CategoryEntity;
import vn.edu.hust.samiestate.exception.FieldNullOrEmptyException;
import vn.edu.hust.samiestate.repository.CategoryRepository;
import vn.edu.hust.samiestate.service.ICategoryService;
import vn.edu.hust.samiestate.utils.ValidateUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CategoryService implements ICategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryConverter categoryConverter;

    public CategoryService(CategoryRepository categoryRepository,
                           CategoryConverter categoryConverter) {
        this.categoryRepository = categoryRepository;
        this.categoryConverter = categoryConverter;
    }

    @Override
    public Map<String, String> getCategory() {
        Map<String,String> results = new HashMap<>();
        List<CategoryEntity> categoryEntities = categoryRepository.findAll();
        categoryEntities.forEach(entity -> {
            CategoryDTO categoryDTO = categoryConverter.convertToDTO(entity);
            results.put(categoryDTO.getCode(), categoryDTO.getName());
        });

        return results;
    }

    @Override
    public List<CategoryDTO> getCategoryList() {
        List<CategoryDTO> results = categoryRepository.findAll().stream().
                map(item -> categoryConverter.convertToDTO(item)).collect(Collectors.toList());

        return results;
    }

    @Override
    public CategoryDTO getCategoryByCode(String categoryCode) {
        return categoryConverter.convertToDTO(categoryRepository.findByCode(categoryCode));
    }

    @Override
    @Transactional
    public void save(CategoryDTO categoryDTO) {
        if (Objects.nonNull(categoryDTO)) {
            if (!ValidateUtils.isValidProperty(categoryDTO.getName())) {
                throw new FieldNullOrEmptyException("Field name is null or empty");
            }

            if (!ValidateUtils.isValidProperty(categoryDTO.getCode())) {
                throw new FieldNullOrEmptyException("Field code is null or empty");
            }

            CategoryEntity categoryEntity = categoryConverter.convertToEntity(categoryDTO);

            categoryRepository.save(categoryEntity);
        }
    }

    @Override
    @Transactional
    public void delete(long[] ids) {
        categoryRepository.deleteByIdIn(ids);
    }
}
