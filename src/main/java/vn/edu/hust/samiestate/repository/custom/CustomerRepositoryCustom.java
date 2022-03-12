package vn.edu.hust.samiestate.repository.custom;

import org.springframework.data.domain.Pageable;
import vn.edu.hust.samiestate.builder.CustomerSearchBuilder;
import vn.edu.hust.samiestate.entity.CustomerEntity;

import java.util.List;

public interface CustomerRepositoryCustom {

    List<CustomerEntity> findByCondition(CustomerSearchBuilder builder, Pageable pageable);
    long countByCondition(CustomerSearchBuilder builder);
}
