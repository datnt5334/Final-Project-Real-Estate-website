package vn.edu.hust.samiestate.repository.custom;

import org.springframework.data.domain.Pageable;
import vn.edu.hust.samiestate.builder.BuildingSearchBuilder;
import vn.edu.hust.samiestate.entity.BuildingEntity;

import java.util.List;

public interface BuildingRepositoryCustom {

    List<BuildingEntity> findByCondition(BuildingSearchBuilder builder, Pageable pageable);
    long countByCondition(BuildingSearchBuilder builder);
}
