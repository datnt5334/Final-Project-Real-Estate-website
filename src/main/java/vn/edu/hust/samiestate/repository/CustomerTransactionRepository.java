package vn.edu.hust.samiestate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.hust.samiestate.entity.CustomerTransactionEntity;

public interface CustomerTransactionRepository extends JpaRepository<CustomerTransactionEntity, Long> {
}
