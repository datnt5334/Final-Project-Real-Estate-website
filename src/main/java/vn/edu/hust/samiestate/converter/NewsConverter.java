package vn.edu.hust.samiestate.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import vn.edu.hust.samiestate.dto.NewsDTO;
import vn.edu.hust.samiestate.dto.response.NewsSearchResponse;
import vn.edu.hust.samiestate.entity.CategoryEntity;
import vn.edu.hust.samiestate.entity.NewsEntity;
import vn.edu.hust.samiestate.exception.NotFoundException;
import vn.edu.hust.samiestate.repository.CategoryRepository;
import vn.edu.hust.samiestate.utils.DateUtils;
import vn.edu.hust.samiestate.utils.ValidateUtils;

import java.util.Optional;

@Component
public class NewsConverter {

    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;

    public NewsConverter(ModelMapper modelMapper,
                         CategoryRepository categoryRepository) {
        this.modelMapper = modelMapper;
        this.categoryRepository = categoryRepository;
    }

    public NewsEntity convertToEntity(NewsDTO dto) {
        NewsEntity result = modelMapper.map(dto, NewsEntity.class);

        // convert category
        if (ValidateUtils.isValidProperty(dto.getCategoryCode())) {
            CategoryEntity categoryFound = Optional.ofNullable(categoryRepository.findByCode(dto.getCategoryCode()))
                    .orElseThrow(() -> new NotFoundException("Category not found"));
            result.setCategory(categoryFound);
        }
        return result;
    }

    public NewsDTO convertToDTO(NewsEntity entity) {
        NewsDTO result = modelMapper.map(entity, NewsDTO.class);

        // createdDate
        result.setCreatedDateString(DateUtils.convertDateToString(entity.getCreatedDate()));
        // author
        result.setAuthor(entity.getUser().getFullName());

        return result;
    }

    public NewsSearchResponse convertToSearchResponse(NewsEntity entity) {
        NewsSearchResponse result = modelMapper.map(entity, NewsSearchResponse.class);

        //createdDate
        result.setCreatedDate(DateUtils.convertDateToString(entity.getCreatedDate()));

        return result;
    }
}
