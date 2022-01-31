package vn.edu.hust.samiestate.controlleradvice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import vn.edu.hust.samiestate.constant.SystemConstant;
import vn.edu.hust.samiestate.exception.FieldNullOrEmptyException;
import vn.edu.hust.samiestate.exception.NotFoundException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(FieldNullOrEmptyException.class)
    public ResponseEntity<Map<String, Object>> handleFieldNullException(FieldNullOrEmptyException ex, WebRequest request) {
        Map<String, Object> data = new HashMap<>();
        data.put("error", ex.getMessage());
        data.put("message", SystemConstant.FAIL);
        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFoundException(NotFoundException ex, WebRequest request) {
        Map<String, Object> data = new HashMap<>();
        data.put("error", ex.getMessage());
        data.put("message", SystemConstant.FAIL);
        return new ResponseEntity<>(data, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
