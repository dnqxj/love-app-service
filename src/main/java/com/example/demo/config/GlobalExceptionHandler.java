package com.example.demo.config;

import com.alibaba.fastjson.JSONObject;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@RestController
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 请求参数校验传递的json格式参数校验
     * @author  khy
     * @createTime 2021年5月18日下午5:24:55
     * @param req
     * @param exception
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public Object validatedPostException(HttpServletRequest req, MethodArgumentNotValidException exception) {
        JSONObject json = new JSONObject();
        json.put("path", req.getRequestURI());
        BindingResult bindingResult = exception.getBindingResult();
        if(bindingResult.hasErrors()){
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            List<String>errorList = fieldErrors.stream().map(e->e.getDefaultMessage()).collect(Collectors.toList());
            Map<String, String> errorMap = fieldErrors.stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            json.put("errorList", errorList);
            json.put("errorMap", errorMap);
            json.put("message", errorList.get(0));
        }
        json.put("timestamp", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        json.put("status", "error");
        return json;
    }

    /**
     * 普通的参数传递的形式;
     * @author  khy
     * @createTime 2021年5月18日下午5:25:16
     * @param req
     * @param exception
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public Object validatedGetException(HttpServletRequest req, BindException exception){
        JSONObject json = new JSONObject();
        json.put("path", req.getRequestURI());
        if(exception.hasErrors()){
            List<FieldError> fieldErrors = exception.getFieldErrors();
            List<String>errorList = fieldErrors.stream().map(e->e.getDefaultMessage()).collect(Collectors.toList());
            Map<String, String> errorMap = fieldErrors.stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            json.put("errorList", errorList);
            json.put("errorMap", errorMap);
            json.put("message", errorList.get(0));
        }
        json.put("timestamp", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        json.put("success", false);
        return json;
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Object exception(HttpServletRequest req, Exception e) throws Exception {
        JSONObject json = new JSONObject();
        json.put("retCode", "003");
        json.put("retMes",e.getMessage());
        json.put("path", req.getRequestURI());
        json.put("timestamp", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        json.put("message", e.getMessage());
        json.put("success", false);
        return json;
    }
}

