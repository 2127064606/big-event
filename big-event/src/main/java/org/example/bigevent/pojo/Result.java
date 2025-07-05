package org.example.bigevent.pojo;


import lombok.Data;

@Data
public class Result {
    private Integer code;
    private String msg;
    private Object data;

    public static Result success(Object data){
        Result res = Result.success();
        res.data = data;
        return res;
    }

    public static Result success(){
        Result res = new Result();
        res.code = 1;
        res.msg = "success";
        return res;
    }

    public static Result error(String msg){
        Result res = new Result();
        res.msg = msg;
        res.code = 0;
        return res;
    }
}
