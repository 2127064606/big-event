package org.example.bigevent.controller;


import org.example.bigevent.exception.DuplicateDataException;
import org.example.bigevent.exception.ErrorFormatInfoException;
import org.example.bigevent.exception.LoginErrorException;
import org.example.bigevent.pojo.Result;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(value = LoginErrorException.class)
    public ResponseEntity<Result> LoginErrorException(LoginErrorException e) {
        CacheControl cacheControl = CacheControl.noStore().mustRevalidate();
        Result result = new Result();
        result.setCode(0);
        result.setMsg(e.getMessage());
        // 模拟返回 401 状态码
        return ResponseEntity.status(401)
                .cacheControl(cacheControl)
                .body(result);
    }

    @ExceptionHandler(value = DuplicateDataException.class)
    public Result DuplicateUserResult(DuplicateDataException e){
        System.out.println("DuplicateUserException");
        return Result.error(e.getMessage());
    }

    @ExceptionHandler(value = ErrorFormatInfoException.class)
    public Result ErrorFormatInfoResult(ErrorFormatInfoException e){
        System.out.println("ErrorFormatInfoException");
        return Result.error(e.getMessage());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result MethodArgumentNotValidResult(MethodArgumentNotValidException e){
        return Result.error(e.getBindingResult().getFieldError().getDefaultMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public Result ExceptionResult(Exception e){
        System.out.println(e.getClass().getName());
        return Result.error(e.getMessage());
    }

}
