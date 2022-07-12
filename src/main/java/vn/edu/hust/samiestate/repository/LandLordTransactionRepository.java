package vn.edu.hust.samiestate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.hust.samiestate.entity.LandLordTransactionEntity;

public interface LandLordTransactionRepository extends JpaRepository<LandLordTransactionEntity, Long> {
}
