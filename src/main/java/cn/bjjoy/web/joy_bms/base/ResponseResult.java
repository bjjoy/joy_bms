package cn.bjjoy.web.joy_bms.base;

public class ResponseResult {

    private String traceID;

    private Integer code;

    private String msg;

    private Object data;

    public ResponseResult(){

    }

    public ResponseResult(String traceID, Integer code, String msg, Object data){
        this.traceID = traceID;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResponseResult(Integer code, String msg){
        this.traceID = null;
        this.code = code;
        this.msg = msg;
        this.data = null;
    }

    public String getTraceID() {
        return traceID;
    }

    public void setTraceID(String traceID) {
        this.traceID = traceID;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
