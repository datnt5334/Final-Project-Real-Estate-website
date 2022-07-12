package vn.edu.hust.samiestate.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import vn.edu.hust.samiestate.dto.CategoryDTO;
import vn.edu.hust.samiestate.entity.CategoryEntity;

@Component
public class CategoryConverter {

    private final ModelMapper modelMapper;

    public CategoryConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CategoryEntity convertToEntity(CategoryDTO dto) {
        CategoryEntity result = modelMapper.map(dto, CategoryEntity.class);
        return result;
    }

    public CategoryDTO convertToDTO(CategoryEntity entity) {
        CategoryDTO result = modelMapper.map(entity, CategoryDTO.class);
        return result;
    }
}
