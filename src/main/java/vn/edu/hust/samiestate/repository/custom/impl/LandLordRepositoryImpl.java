package vn.edu.hust.samiestate.repository.custom.impl;

import org.springframework.data.domain.Pageable;
import vn.edu.hust.samiestate.builder.LandLordSearchBuilder;
import vn.edu.hust.samiestate.constant.SystemConstant;
import vn.edu.hust.samiestate.entity.LandLordEntity;
import vn.edu.hust.samiestate.repository.custom.LandLordRepositoryCustom;
import vn.edu.hust.samiestate.utils.ReflectionUtils;
import vn.edu.hust.samiestate.utils.SqlUtils;
import vn.edu.hust.samiestate.utils.ValidateUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.List;

public class LandLordRepositoryImpl implements LandLordRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<LandLordEntity> findByCondition(LandLordSearchBuilder builder, Pageable pageable) {
        StringBuilder sql = new StringBuilder("SELECT l.* FROM landlord AS l");

        StringBuilder joinFindQuery = new StringBuilder();
        StringBuilder normalFindQuery = new StringBuilder();
        StringBuilder specialFindQuery = new StringBuilder();

        buildJoinQuery(builder, joinFindQuery);
        buildNormalWhereQuery(builder, normalFindQuery);
        buildSpecialWhereQuery(builder, specialFindQuery);

        sql.append(joinFindQuery).append(" WHERE 1 = 1").append(normalFindQuery).append(specialFindQuery)
                .append(" GROUP BY l.id").append(" ORDER BY l.id ASC");

        Long offset = pageable.getOffset();
        Integer limit = pageable.getPageSize();

        if (ValidateUtils.isValidProperty(offset) && ValidateUtils.isValidProperty(limit)) {
            sql.append(" LIMIT "+offset+", "+limit+"");
        }

        Query query = entityManager.createNativeQuery(sql.toString(), LandLordEntity.class);
        return query.getResultList();
    }

    @Override
    public long countByCondition(LandLordSearchBuilder builder) {
        StringBuilder sql = new StringBuilder("SELECT COUNT(DISTINCT l.id) FROM landlord AS l");

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

    private void buildSpecialWhereQuery(LandLordSearchBuilder builder, StringBuilder specialQuery) {

        // staffId
        Long staffId = builder.getStaffId();
        if (ValidateUtils.isValidProperty(staffId)) {
            specialQuery.append(SqlUtils.buildQueryUsingOperator("al.staff_id", staffId,
                    SystemConstant.EQUAL_OPERATOR));
        }

        // statusCode
        String statusCode = builder.getStatusCode();
        if (ValidateUtils.isValidProperty(statusCode)) {
            specialQuery.append(SqlUtils.buildQueryUsingOperator("l.statuscode", statusCode,
                    SystemConstant.EQUAL_OPERATOR));
        }
    }

    private void buildJoinQuery(LandLordSearchBuilder builder, StringBuilder joinQuery) {

        if (ValidateUtils.isValidProperty(builder.getStaffId())) {
            joinQuery.append(" INNER JOIN assignmentlandlord AS al ON l.id = al.landlord_id");
        }
    }

    private void buildNormalWhereQuery(LandLordSearchBuilder builder, StringBuilder normalQuery) {

        try {
            ReflectionUtils.buildNormalQuery(builder, normalQuery, SystemConstant.LANDLORD_SEARCH);
        } catch (IllegalAccessException e) {
            return;
        }
    }
}
