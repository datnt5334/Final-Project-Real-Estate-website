package vn.edu.hust.samiestate.utils;

public class SqlUtils {

    public static String buildQueryUsingLike(String column, String value) {
        return (!ValidateUtils.isValidProperty(value)) ? ""
                : String.format(" AND %s LIKE %s", column, "'%" + value + "%'");
    }

    public static String buildQueryUsingOperator(String column, Object value, String operator) {
        if (!ValidateUtils.isValidProperty(value)) {
            return "";
        }

        return (value instanceof String) ? String.format(" AND %s %s '%s'", column, operator, value)
                : String.format(" AND %s %s %s", column, operator, value);
    }

    public static String buildQueryUsingBetween(String column, Object from, Object to) {
        if (from == null && to == null) {
            return "";
        } else {
            if (from == null) {
                from = 0;
            }
            if (to == null) {
                if (from instanceof Integer) {
                    to = Integer.MAX_VALUE;
                } else if (from instanceof Double) {
                    to = Double.MAX_VALUE;
                }
            }

            return String.format(" AND %s BETWEEN %s AND %s", column, from, to);
        }
    }
}
