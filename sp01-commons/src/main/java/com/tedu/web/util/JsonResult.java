package com.tedu.web.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JsonResult<T> {
    /** 成功 */
    public static final int SUCCESS = 200;
    /** 沒有登錄 */
    public static final int NOT_LOGIN = 400;
    /** 發生異常 */
    public static final int EXCEPTION = 401;
    /** 系統錯誤 */
    public static final int SYS_ERROR = 402;
    /** 參數錯誤 */
    public static final int PARAMS_ERROR = 403;
    /** 不支持或已經廢棄 */
    public static final int NOT_SUPPORTED = 410;
    /** AuthCode錯誤 */
    public static final int INVALID_AUTHCODE = 444;
    /** 太頻繁的調用 */
    public static final int TOO_FREQUENT = 445;
    /** 未知的錯誤 */
    public static final int UNKNOWN_ERROR = 499;
    private int code;
    private String msg;
    private T data;

    public static JsonResult build() {
        return new JsonResult();
    }

    public static JsonResult build(int code) {
        return new JsonResult().code(code);
    }

    public static JsonResult build(int code, String msg) {
        return new JsonResult<String>().code(code).msg(msg);
    }

    public static <T> JsonResult<T> build(int code, T data) {
        return new JsonResult<T>().code(code).data(data);
    }

    public static <T> JsonResult<T> build(int code, String msg, T data) {
        return new JsonResult<T>().code(code).msg(msg).data(data);
    }

    public JsonResult<T> code(int code) {
        this.code = code;
        return this;
    }

    public JsonResult<T> msg(String msg) {
        this.msg = msg;
        return this;
    }

    public JsonResult<T> data(T data) {
        this.data = data;
        return this;
    }

    public static JsonResult okay() {
        return build(SUCCESS);
    }

    public static JsonResult okay(String msg) {
        return build(SUCCESS, msg);
    }

    public static <T> JsonResult<T> okay(T data) {
        return build(SUCCESS, data);
    }

    public static JsonResult error() {
        return build(EXCEPTION);
    }

    public static JsonResult error(String msg) {
        return build(EXCEPTION, msg);
    }

    @Override
    public String toString() {
        return JsonUtil.to(this);
    }

}
