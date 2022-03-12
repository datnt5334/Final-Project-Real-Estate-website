package vn.edu.hust.samiestate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.hust.samiestate.entity.CustomerEntity;
import vn.edu.hust.samiestate.repository.custom.CustomerRepositoryCustom;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long>, CustomerRepositoryCustom {
    void deleteByIdIn(long[] ids);
}
