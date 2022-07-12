package vn.edu.hust.samiestate.api.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hust.samiestate.dto.NewsDTO;
import vn.edu.hust.samiestate.service.impl.NewsService;

@RestController
@RequestMapping("/api/news")
public class NewsAPI {

    private final NewsService newsService;

    public NewsAPI(NewsService newsService) {
        this.newsService = newsService;
    }

    @PostMapping
    public NewsDTO createNews(@RequestBody NewsDTO newsDTO) {
        try {
            return newsService.save(newsDTO);
        } catch (Exception e) {
            throw e;
        }
    }

    @PutMapping("/{id}")
    public NewsDTO updateNews(@RequestBody NewsDTO newsDTO) {
        try {
            return newsService.save(newsDTO);
        } catch (Exception e) {
            throw e;
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteNews(@RequestBody long[] ids) {
        if (ids.length > 0) {
            newsService.delete(ids);
        }
        return ResponseEntity.noContent().build();
    }
}
