package vn.edu.hust.samiestate.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.hust.samiestate.entity.UserEntity;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findOneByUserNameAndStatus(String name, int status);
    Page<UserEntity> findByUserNameContainingIgnoreCaseOrFullNameContainingIgnoreCaseAndStatusNot(String userName, String fullName, int status,
                                                                                                  Pageable pageable);
    Page<UserEntity> findByStatusNot(int status, Pageable pageable);
    long countByUserNameContainingIgnoreCaseOrFullNameContainingIgnoreCaseAndStatusNot(String userName, String fullName, int status);
    long countByStatusNot(int status);
    UserEntity findOneByUserName(String userName);
    void deleteByIdIn(long[] ids);
    List<UserEntity> findByStatusAndRoles_Code(Integer status, String roleCode);
    List<UserEntity> findByBuildings_Id(Long buildingId);
    List<UserEntity> findByCustomers_Id(Long customerId);
    List<UserEntity> findByLandLords_Id(Long landLordId);
}
