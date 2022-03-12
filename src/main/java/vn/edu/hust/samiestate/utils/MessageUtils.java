package vn.edu.hust.samiestate.utils;

import vn.edu.hust.samiestate.constant.SystemConstant;

import java.util.HashMap;
import java.util.Map;

public class MessageUtils {

	public static Map<String, String> getMessage(String message) {
		Map<String, String> result = new HashMap<>();
		if (message.equals(SystemConstant.UPDATE_SUCCESS)) {
			result.put(SystemConstant.MESSAGE_RESPONSE, "Cập nhật thành công!");
			result.put(SystemConstant.ALERT, SystemConstant.SUCCESS);
		} else if (message.equals(SystemConstant.INSERT_SUCCESS)) {
			result.put(SystemConstant.MESSAGE_RESPONSE, "Thêm mới thành công!");
			result.put(SystemConstant.ALERT, SystemConstant.SUCCESS);
		} else if (message.equals(SystemConstant.DELETE_SUCCESS)) {
			result.put(SystemConstant.MESSAGE_RESPONSE, "Xóa thành công!");
			result.put(SystemConstant.ALERT, SystemConstant.SUCCESS);
		} else if (message.equals(SystemConstant.ERROR_SYSTEM)) {
			result.put(SystemConstant.MESSAGE_RESPONSE, "Lỗi hệ thống!");
			result.put(SystemConstant.ALERT, SystemConstant.DANGER);
		} else if (message.equals("building_name_require")) {
			result.put(SystemConstant.MESSAGE_RESPONSE, "Tên tòa nhà không được để trống!");
			result.put(SystemConstant.ALERT, SystemConstant.WARNING);
		} else if (message.equals("customer_name_require")) {
			result.put(SystemConstant.MESSAGE_RESPONSE, "Tên khách hàng không được để trống!");
			result.put(SystemConstant.ALERT, SystemConstant.WARNING);
		} else if (message.equals("username_role_require")) {
			result.put(SystemConstant.MESSAGE_RESPONSE, "Tên đăng nhập huặc vai trò không được để trống!");
			result.put(SystemConstant.ALERT, SystemConstant.WARNING);
		} else if (message.equals("change_password_fail")) {
			result.put(SystemConstant.MESSAGE_RESPONSE, "Thay đổi mật khẩu bị lỗi!");
			result.put(SystemConstant.ALERT, SystemConstant.WARNING);
		} else if (message.equals("reset_password_success")) {
			result.put(SystemConstant.MESSAGE_RESPONSE, "Reset mật khẩu thành công!");
			result.put(SystemConstant.ALERT, SystemConstant.SUCCESS);
		}
		return result;
	}
}
