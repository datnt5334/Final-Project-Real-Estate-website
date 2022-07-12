package vn.edu.hust.samiestate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.hust.samiestate.entity.LandLordEntity;
import vn.edu.hust.samiestate.repository.custom.LandLordRepositoryCustom;

public interface LandLordRepository extends JpaRepository<LandLordEntity, Long>, LandLordRepositoryCustom {
    void deleteByIdIn(long[] ids);
}
