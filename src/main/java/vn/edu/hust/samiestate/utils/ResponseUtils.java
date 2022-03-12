package vn.edu.hust.samiestate.utils;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.hust.samiestate.constant.SystemConstant;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class ResponseUtils {

    public static void initMessageResponse(ModelAndView mav, HttpServletRequest request) {
        String message = request.getParameter("message");
        if (message != null && StringUtils.isNotEmpty(message)) {
            Map<String, String> messageMap = MessageUtils.getMessage(message);
            mav.addObject(SystemConstant.ALERT, messageMap.get(SystemConstant.ALERT));
            mav.addObject(SystemConstant.MESSAGE_RESPONSE, messageMap.get(SystemConstant.MESSAGE_RESPONSE));
        }
    }
}
