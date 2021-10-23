package icu.iseenu.fundbot.common;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class Result {

    private Integer code;
    private String message;
    private Object data;

    public Result(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static Result ok() {
        return ok(null);
    }

    public static Result ok(Object data) {
        return new Result(ApiCodeEnum.SUCCESS.getCode(), ApiCodeEnum.SUCCESS.getMsg(), data);
    }

    public static Result ok(String message, Object data) {
        return new Result(ApiCodeEnum.SUCCESS.getCode(), message, data);
    }


    public static Result error() {
        return error(null);
    }

    public static Result error(Object data) {
        return new Result(ApiCodeEnum.ERROR.getCode(), ApiCodeEnum.ERROR.getMsg(), data);
    }

    public static Result error(String message) {
        return new Result(ApiCodeEnum.ERROR.getCode(), message);
    }

    public static Result error(String message, Object data) {
        return new Result(ApiCodeEnum.ERROR.getCode(), message, data);
    }

}
