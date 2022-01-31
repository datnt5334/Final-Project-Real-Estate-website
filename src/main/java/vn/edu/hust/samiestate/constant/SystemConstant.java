package vn.edu.hust.samiestate.constant;

import java.util.Arrays;
import java.util.List;

public class SystemConstant {

    //role user
    public static final String MANAGER_ROLE = "ROLE_MANAGER";
    public static final String STAFF_ROLE = "ROLE_STAFF";

    //path
    public static final String HOME = "/trang-chu";
    public static final String ADMIN_HOME = "/admin/home";

    //model
    public static final String MODEL = "model";

    //message
    public static final String INSERT_SUCCESS = "insert_success";
    public static final String UPDATE_SUCCESS = "update_success";
    public static final String DELETE_SUCCESS = "delete_success";
    public static final String ERROR_SYSTEM = "error_system";
    public static final String ALERT = "alert";
    public static final String FAIL = "fail";
    public static final String SUCCESS = "success";
    public static final String DANGER = "danger";
    public static final String WARNING = "warning";
    public static final String MESSAGE_RESPONSE = "messageResponse";

    //password
    public static final String PASSWORD_DEFAULT = "123456";
    public static final String CHANGE_PASSWORD_FAIL = "change password fail!";

    //user
    public static final String USER_NOT_FOUND = "user not found!";
    public static final String ROLE_USER_NOT_FOUND = "role of user not found!";

    //for search
    public static final String BUILDING_SEARCH = "Building";
    public static final String CUSTOMER_SEARCH = "Customer";
    public static final String BUILDING_ALIAS = "b.";
    public static final String CUSTOMER_ALIAS = "c.";
    public static final String EQUAL_OPERATOR = "=";
    public static final List<String> SPECIAL_PARAMS_FOR_BUILDING_SEARCH =
            Arrays.asList("buildingtypes", "arearentfrom", "arearentto", "costrentfrom", "costrentto", "staffid");
    public static final List<String> SPECIAL_PARAMS_FOR_CUSTOMER_SEARCH =
            Arrays.asList("staffid");

}
