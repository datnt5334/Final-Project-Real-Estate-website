package vn.edu.hust.samiestate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.hust.samiestate.entity.LandLordTransactionTypeEntity;

public interface LandLordTransactionTypeRepository extends JpaRepository<LandLordTransactionTypeEntity, Long> {
    LandLordTransactionTypeEntity findByCode(String code);
    void deleteByIdIn(long[] ids);
}
