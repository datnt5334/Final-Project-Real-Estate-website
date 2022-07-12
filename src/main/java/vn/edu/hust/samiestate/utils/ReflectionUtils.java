package vn.edu.hust.samiestate.utils;

import vn.edu.hust.samiestate.constant.SystemConstant;

import java.lang.reflect.Field;
import java.util.List;

public class ReflectionUtils {

    public static <T> void buildNormalQuery(T objectSearchBuilder, StringBuilder query,
                                            String nameSearch) throws IllegalAccessException {
        if (nameSearch.equals(SystemConstant.BUILDING_SEARCH)) {
            buildQuery(objectSearchBuilder, query, SystemConstant.BUILDING_ALIAS,
                    SystemConstant.SPECIAL_PARAMS_FOR_BUILDING_SEARCH);
        }

        if (nameSearch.equals(SystemConstant.CUSTOMER_SEARCH)) {
            buildQuery(objectSearchBuilder, query, SystemConstant.CUSTOMER_ALIAS,
                    SystemConstant.SPECIAL_PARAMS_FOR_CUSTOMER_SEARCH);
        }

        if (nameSearch.equals(SystemConstant.LANDLORD_SEARCH)) {
            buildQuery(objectSearchBuilder, query, SystemConstant.LANDLORD_ALIAS,
                    SystemConstant.SPECIAL_PARAMS_FOR_LANDLORD_SEARCH);
        }
    }

    private static <T> void buildQuery(T objectSearchBuilder, StringBuilder query, String alias,
                                       List<String> specialParams) throws IllegalAccessException {
        Field[] fields = objectSearchBuilder.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);

            String fieldName = field.getName().toLowerCase();
            Object objectValue = field.get(objectSearchBuilder);

            if (ValidateUtils.isValidProperty(objectValue) && !specialParams.contains(fieldName)) {
                String column = alias + fieldName;
                if (field.getType().equals(String.class)) {
                    query.append(SqlUtils.buildQueryUsingLike(column, objectValue.toString()));
                } else if (field.getType().equals(Integer.class) || field.getType().equals(Double.class)) {
                    query.append(SqlUtils.buildQueryUsingOperator(column, objectValue, SystemConstant.EQUAL_OPERATOR));
                }
            }
        }
    }
}
