package vn.edu.hust.samiestate.repository.custom.impl;

import org.springframework.data.domain.Pageable;
import vn.edu.hust.samiestate.builder.CustomerSearchBuilder;
import vn.edu.hust.samiestate.constant.SystemConstant;
import vn.edu.hust.samiestate.entity.CustomerEntity;
import vn.edu.hust.samiestate.repository.custom.CustomerRepositoryCustom;
import vn.edu.hust.samiestate.utils.ReflectionUtils;
import vn.edu.hust.samiestate.utils.SqlUtils;
import vn.edu.hust.samiestate.utils.ValidateUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.List;

public class CustomerRepositoryImpl implements CustomerRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<CustomerEntity> findByCondition(CustomerSearchBuilder builder, Pageable pageable) {
        StringBuilder sql = new StringBuilder("SELECT c.* FROM customer AS c");

        StringBuilder joinFindQuery = new StringBuilder();
        StringBuilder normalFindQuery = new StringBuilder();
        StringBuilder specialFindQuery = new StringBuilder();

        buildJoinQuery(builder, joinFindQuery);
        buildNormalWhereQuery(builder, normalFindQuery);
        buildSpecialWhereQuery(builder, specialFindQuery);

        sql.append(joinFindQuery).append(" WHERE 1 = 1").append(normalFindQuery).append(specialFindQuery)
                .append(" GROUP BY c.id").append(" ORDER BY c.id ASC");

        Long offset = pageable.getOffset();
        Integer limit = pageable.getPageSize();

        if (ValidateUtils.isValidProperty(offset) && ValidateUtils.isValidProperty(limit)) {
            sql.append(" LIMIT "+offset+", "+limit+"");
        }

        Query query = entityManager.createNativeQuery(sql.toString(), CustomerEntity.class);
        return query.getResultList();
    }

    @Override
    public long countByCondition(CustomerSearchBuilder builder) {
        StringBuilder sql = new StringBuilder("SELECT COUNT(DISTINCT c.id) FROM customer AS c");

        StringBuilder joinCountQuery = new StringBuilder();
        StringBuilder normalCountQuery = new StringBuilder();
        StringBuilder specialCountQuery = new StringBuilder();

        buildJoinQuery(builder, joinCountQuery);
        buildNormalWhereQuery(builder, normalCountQuery);
        buildSpecialWhereQuery(builder, specialCountQuery);

        sql.append(joinCountQuery).append(" WHERE 1 = 1").append(normalCountQuery).append(specialCountQuery);

        Query query = entityManager.createNativeQuery(sql.toString());
        BigInteger countNumber = (BigInteger) query.getSingleResult();
        return countNumber.longValue();
    }

    private void buildSpecialWhereQuery(CustomerSearchBuilder builder, StringBuilder specialQuery) {

        //staffId
        Long staffId = builder.getStaffId();

        if (ValidateUtils.isValidProperty(staffId)) {
            specialQuery.append(SqlUtils.buildQueryUsingOperator("ac.staff_id", staffId,
                    SystemConstant.EQUAL_OPERATOR));
        }

        // statusCode
        String statusCode = builder.getStatusCode();
        if (ValidateUtils.isValidProperty(statusCode)) {
            specialQuery.append(SqlUtils.buildQueryUsingOperator("c.statuscode", statusCode,
                    SystemConstant.EQUAL_OPERATOR));
        }

    }

    private void buildJoinQuery(CustomerSearchBuilder builder, StringBuilder joinQuery) {

        if (ValidateUtils.isValidProperty(builder.getStaffId())) {
            joinQuery.append(" INNER JOIN assignmentcustomer AS ac ON c.id = ac.customer_id");
        }
    }

    private void buildNormalWhereQuery(CustomerSearchBuilder builder, StringBuilder normalQuery) {

        try {
            ReflectionUtils.buildNormalQuery(builder, normalQuery, SystemConstant.CUSTOMER_SEARCH);
        } catch (IllegalAccessException e) {
            return;
        }
    }

}
