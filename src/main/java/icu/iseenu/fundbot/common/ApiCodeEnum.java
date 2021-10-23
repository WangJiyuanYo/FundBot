package icu.iseenu.fundbot.common;

public enum ApiCodeEnum {

    SUCCESS(200, "SUCCESS"), //请求成功

    ERROR(0, "FAIL"),

    CUSTOM_FAIL(9999, "自定义业务异常"),  //自定义业务异常

    SYSTEM_ERROR(10, "系统异常[%s]"),
    PARAMS_ERROR(11, "参数有误[%s]"),
    DB_ERROR(12, "数据库服务异常"),

    SYS_OPERATION_FAIL_CREATE(5000, "新增失败"),
    SYS_OPERATION_FAIL_DELETE(5001, "删除失败"),
    SYS_OPERATION_FAIL_UPDATE(5002, "修改失败"),
    SYS_OPERATION_FAIL_SELETE(5003, "记录不存在"),
    SYS_PERMISSION_ERROR(5004, "权限错误，当前用户不支持此操作");


    private int code;

    private String msg;

    ApiCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public int getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }
}
