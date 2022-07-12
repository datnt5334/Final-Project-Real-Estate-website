package vn.edu.hust.samiestate.repository.custom;

import org.springframework.data.domain.Pageable;
import vn.edu.hust.samiestate.builder.LandLordSearchBuilder;
import vn.edu.hust.samiestate.entity.LandLordEntity;

import java.util.List;

public interface LandLordRepositoryCustom {

    List<LandLordEntity> findByCondition(LandLordSearchBuilder builder, Pageable pageable);
    long countByCondition(LandLordSearchBuilder builder);
}
