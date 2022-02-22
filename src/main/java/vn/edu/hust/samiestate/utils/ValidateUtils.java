package vn.edu.hust.samiestate.utils;

import java.lang.reflect.Field;
import java.util.Collection;

public class ValidateUtils {

    public static <T> boolean isValidObject(T object) throws IllegalAccessException {
        Field[] fields = object.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            Object objectValue = field.get(object);

            if (objectValue != null && objectValue != "") {
                return true;
            }
        }

        return false;
    }

    public static boolean isValidProperty(Object obj) {

        boolean isTrue = (obj != null && !obj.toString().isEmpty());

        if (isTrue) {
            if (obj instanceof String) {
                return true;
            } else if (obj instanceof Integer) {
                return Integer.parseInt(obj.toString()) >= 0;
            } else if (obj instanceof Long) {
                return Long.parseLong(obj.toString()) >= 0;
            } else if (obj instanceof Collection) {
                return !((Collection<?>) obj).isEmpty();
            } else if (obj instanceof Double) {
                return Double.parseDouble(obj.toString()) >= 0;
            }
        }

        return false;
    }
}
