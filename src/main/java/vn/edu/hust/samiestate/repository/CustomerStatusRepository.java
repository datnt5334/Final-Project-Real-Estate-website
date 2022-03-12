package vn.edu.hust.samiestate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.hust.samiestate.entity.CustomerStatusEntity;

public interface CustomerStatusRepository extends JpaRepository<CustomerStatusEntity, Long> {
    CustomerStatusEntity findByCode(String code);
}
