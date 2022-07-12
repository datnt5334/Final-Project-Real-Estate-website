package vn.edu.hust.samiestate.service;
import org.springframework.data.domain.Pageable;
import vn.edu.hust.samiestate.dto.NewsDTO;
import vn.edu.hust.samiestate.dto.request.NewsSearchRequest;
import vn.edu.hust.samiestate.dto.response.NewsSearchResponse;

import java.util.List;

public interface INewsService {

    NewsDTO save(NewsDTO newsDTO);
    NewsDTO findNewsById(Long id);
    List<NewsSearchResponse> getNews(NewsSearchRequest searchRequest, Pageable pageable);
    List<NewsSearchResponse> getLatestNews(Pageable pageable);
    int getTotalItems(NewsSearchRequest searchRequest);
    void delete(long[] ids);
}
