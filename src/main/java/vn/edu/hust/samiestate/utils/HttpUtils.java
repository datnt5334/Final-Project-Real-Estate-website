package vn.edu.hust.samiestate.utils;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;

public class HttpUtils {

    public static <T> void toModel(T object, HttpServletRequest request) {
        try {
            BeanUtils.populate(object, request.getParameterMap());
        } catch (IllegalAccessException | InvocationTargetException e) {
            System.out.print(e.getMessage());
        }
    }
}
