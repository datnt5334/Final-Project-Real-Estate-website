package vn.edu.hust.samiestate.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.edu.hust.samiestate.entity.NewsEntity;

import java.util.List;

public interface NewsRepository extends JpaRepository<NewsEntity, Long> {
    @Query("SELECT e FROM NewsEntity e WHERE (:title is null or :title = '' or e.title LIKE %:title%) and (:categoryCode is null" +
            " or :categoryCode = '' or e.category.code = :categoryCode)")
    List<NewsEntity> findByTitleAndCategoryCode(@Param("title") String title, @Param("categoryCode") String categoryCode, Pageable pageable);

    @Query("SELECT COUNT(e) FROM NewsEntity e WHERE (:title is null or :title = '' or e.title LIKE %:title%) and (:categoryCode is null" +
            " or :categoryCode = '' or e.category.code = :categoryCode)")
    long countByTitleAndCategoryCode(String title, String categoryCode);

    void deleteByIdIn(long[] ids);
    List<NewsEntity> findAllByOrderByCreatedDateDesc(Pageable pageable);
}
