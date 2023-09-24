package com.orangemust.love.config;

import com.orangemust.core.response.Result;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 请求参数校验传递的json格式参数校验
     *
     * @param exception
     * @return
     * @throws Exception
     * @author khy
     * @createTime 2021年5月18日下午5:24:55
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public Result validatedPostException(MethodArgumentNotValidException exception) {
        List<FieldError> fieldErrors = exception.getFieldErrors();
        List<String> errorList = fieldErrors.stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList());
        String errorMessage = errorList.get(0);
        return Result.error().message(errorMessage);
    }

    /**
     * 普通的参数传递的形式;
     *
     * @param exception
     * @return
     * @throws Exception
     * @author khy
     * @createTime 2021年5月18日下午5:25:16
     */
    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public Result validatedGetException(BindException exception) {
        List<FieldError> fieldErrors = exception.getFieldErrors();
        List<String> errorList = fieldErrors.stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList());
        String errorMessage = errorList.get(0);
        return Result.error().message(errorMessage);
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result exception(Exception e) throws Exception {
        return Result.error().message(e.getMessage());
    }
}
