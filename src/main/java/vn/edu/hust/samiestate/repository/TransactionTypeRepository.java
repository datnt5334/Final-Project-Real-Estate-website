package vn.edu.hust.samiestate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.hust.samiestate.entity.TransactionTypeEntity;

public interface TransactionTypeRepository extends JpaRepository<TransactionTypeEntity, Long> {
    TransactionTypeEntity findByCode(String code);
}
