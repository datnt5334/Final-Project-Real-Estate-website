package vn.edu.hust.samiestate.service.impl;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.hust.samiestate.converter.NewsConverter;
import vn.edu.hust.samiestate.dto.NewsDTO;
import vn.edu.hust.samiestate.dto.request.NewsSearchRequest;
import vn.edu.hust.samiestate.dto.response.NewsSearchResponse;
import vn.edu.hust.samiestate.entity.NewsEntity;
import vn.edu.hust.samiestate.entity.UserEntity;
import vn.edu.hust.samiestate.exception.NotFoundException;
import vn.edu.hust.samiestate.repository.NewsRepository;
import vn.edu.hust.samiestate.repository.UserRepository;
import vn.edu.hust.samiestate.security.utils.SecurityUtils;
import vn.edu.hust.samiestate.service.INewsService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NewsService implements INewsService {

    private final NewsConverter newsConverter;
    private final NewsRepository newsRepository;
    private final UserRepository userRepository;

    public NewsService(NewsConverter newsConverter, NewsRepository newsRepository, UserRepository userRepository) {
        this.newsConverter = newsConverter;
        this.newsRepository = newsRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public NewsDTO save(NewsDTO newsDTO) {
        if (Objects.nonNull(newsDTO)) {
            Long newsId = newsDTO.getId();
            NewsEntity newsEntity = newsConverter.convertToEntity(newsDTO);

            if (newsId != null) {
                Optional<NewsEntity> newsFoundOptional = Optional.ofNullable(newsRepository.findById(newsId))
                        .orElseThrow(() -> new NotFoundException("News not found!"));
                newsEntity.setUser(newsFoundOptional.get().getUser());
            } else {
                Long currentUserId = SecurityUtils.getPrincipal().getId();
                Optional<UserEntity> userFoundOptional = Optional.ofNullable(userRepository.findById(currentUserId))
                        .orElseThrow(() -> new NotFoundException("User not found!"));
                newsEntity.setUser(userFoundOptional.get());
            }

            return newsConverter.convertToDTO(newsRepository.save(newsEntity));
        }
        return null;
    }

    @Override
    public NewsDTO findNewsById(Long id) {
        return newsConverter.convertToDTO(newsRepository.findById(id).get());
    }

    @Override
    public List<NewsSearchResponse> getNews(NewsSearchRequest searchRequest, Pageable pageable) {
        List<NewsSearchResponse> result = new ArrayList<>();
        List<NewsEntity> newsEntities = newsRepository.findByTitleAndCategoryCode(searchRequest.getTitle(),
                searchRequest.getCategoryCode(), pageable);
        result = newsEntities.stream().map(newsConverter::convertToSearchResponse).collect(Collectors.toList());
        return  result;
    }

    @Override
    public List<NewsSearchResponse> getLatestNews(Pageable pageable) {
        List<NewsSearchResponse> result = newsRepository.findAllByOrderByCreatedDateDesc(pageable).stream()
                .map(item -> newsConverter.convertToSearchResponse(item)).collect(Collectors.toList());
        return result;
    }

    @Override
    public int getTotalItems(NewsSearchRequest searchRequest) {
        int totalItems = 0;
        totalItems = (int) newsRepository.countByTitleAndCategoryCode(searchRequest.getTitle(), searchRequest.getCategoryCode());
        return totalItems;
    }

    @Override
    @Transactional
    public void delete(long[] ids) {
        newsRepository.deleteByIdIn(ids);
    }
}
