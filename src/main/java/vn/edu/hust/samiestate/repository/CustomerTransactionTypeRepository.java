package vn.edu.hust.samiestate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.hust.samiestate.entity.CustomerTransactionTypeEntity;

public interface CustomerTransactionTypeRepository extends JpaRepository<CustomerTransactionTypeEntity, Long> {
    CustomerTransactionTypeEntity findByCode(String code);
    void deleteByIdIn(long[] ids);
}
