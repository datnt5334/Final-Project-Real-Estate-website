package vn.edu.hust.samiestate.repository.custom.impl;

import org.springframework.data.domain.Pageable;
import vn.edu.hust.samiestate.builder.BuildingSearchBuilder;
import vn.edu.hust.samiestate.constant.SystemConstant;
import vn.edu.hust.samiestate.entity.BuildingEntity;
import vn.edu.hust.samiestate.repository.custom.BuildingRepositoryCustom;
import vn.edu.hust.samiestate.utils.ReflectionUtils;
import vn.edu.hust.samiestate.utils.SqlUtils;
import vn.edu.hust.samiestate.utils.ValidateUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.List;

public class BuildingRepositoryImpl implements BuildingRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<BuildingEntity> findByCondition(BuildingSearchBuilder builder, Pageable pageable) {
        StringBuilder sql = new StringBuilder("SELECT b.* FROM building AS b");

        StringBuilder joinFindQuery = new StringBuilder();
        StringBuilder normalFindQuery = new StringBuilder();
        StringBuilder specialFindQuery = new StringBuilder();

        buildJoinQuery(builder, joinFindQuery);
        buildNormalWhereQuery(builder, normalFindQuery);
        buildSpecialWhereQuery(builder, specialFindQuery);

        sql.append(joinFindQuery).append(" WHERE 1 = 1").append(normalFindQuery).append(specialFindQuery)
                .append(" GROUP BY b.id");

        Long offset = pageable.getOffset();
        Integer limit = pageable.getPageSize();

        if (ValidateUtils.isValidProperty(offset) && ValidateUtils.isValidProperty(limit)) {
            sql.append(" LIMIT "+offset+", "+limit+"");
        }

        Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);
        return query.getResultList();
    }

    @Override
    public long countByCondition(BuildingSearchBuilder builder) {

        StringBuilder sql = new StringBuilder("select count(distinct b.id) from building AS b");

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

    private void buildSpecialWhereQuery(BuildingSearchBuilder builder, StringBuilder specialQuery) {

        //areaRentFrom and areaRentTo
        Integer areaRentFrom = builder.getAreaRentFrom();
        Integer areaRentTo = builder.getAreaRentTo();

        if (ValidateUtils.isValidProperty(areaRentFrom) || ValidateUtils.isValidProperty(areaRentTo)) {

            specialQuery.append(" AND EXISTS (SELECT * FROM rentarea AS ra WHERE b.id = ra.building_id");

            specialQuery.append(SqlUtils.buildQueryUsingBetween("ra.value", areaRentFrom, areaRentTo));

            specialQuery.append(")");
        }

        //costRentFrom and costRentTo
        Integer costRentFrom = builder.getCostRentFrom();
        Integer costRentTo = builder.getCostRentTo();

        if (ValidateUtils.isValidProperty(costRentFrom) || ValidateUtils.isValidProperty(costRentTo)) {
            specialQuery.append(SqlUtils.buildQueryUsingBetween("b.rentprice", costRentFrom, costRentTo));
        }

        //staffId
        Long staffId = builder.getStaffId();

        if (ValidateUtils.isValidProperty(staffId)) {

            specialQuery.append(SqlUtils.buildQueryUsingOperator("asb.staff_id", staffId,
                    SystemConstant.EQUAL_OPERATOR));
        }

        //district
        String districtCode = builder.getDistrictCode();

        if (ValidateUtils.isValidProperty(districtCode)) {

            specialQuery.append(SqlUtils.buildQueryUsingOperator("d.code", districtCode,
                    SystemConstant.EQUAL_OPERATOR));
        }
    }

    private void buildJoinQuery(BuildingSearchBuilder builder, StringBuilder joinQuery) {

        if (ValidateUtils.isValidProperty(builder.getStaffId())) {
            joinQuery.append(" INNER JOIN assignmentbuilding AS asb ON b.id = asb.building_id");
        }

        if (ValidateUtils.isValidProperty(builder.getDistrictCode())) {
            joinQuery.append(" INNER JOIN district AS d ON b.district_id = d.id");
        }

    }

    private void buildNormalWhereQuery(BuildingSearchBuilder builder, StringBuilder normalQuery) {

        try {
            ReflectionUtils.buildNormalQuery(builder, normalQuery, SystemConstant.BUILDING_SEARCH);
        } catch (IllegalAccessException e) {
            return;
        }
    }
}
