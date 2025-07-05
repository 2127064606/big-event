package org.example.bigevent.controller;


import org.example.bigevent.exception.DuplicateDataException;
import org.example.bigevent.exception.ErrorFormatInfoException;
import org.example.bigevent.pojo.Result;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

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
