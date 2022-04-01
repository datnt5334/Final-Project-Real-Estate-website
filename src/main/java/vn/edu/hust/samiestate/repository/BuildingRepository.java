package vn.edu.hust.samiestate.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.hust.samiestate.entity.BuildingEntity;
import vn.edu.hust.samiestate.repository.custom.BuildingRepositoryCustom;

import java.util.List;

public interface BuildingRepository extends JpaRepository<BuildingEntity, Long>, BuildingRepositoryCustom {
    void deleteByIdIn(long[] ids);
    List<BuildingEntity> findAllByOrderByCreatedDateDesc(Pageable pageable);
    List<BuildingEntity> findByLevel(String level, Pageable pageable);
}
