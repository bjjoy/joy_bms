package cn.bjjoy.web.joy_bms.exception;

/**
 * Created by GXM on 2017/11/15
 **/
public class OperationException extends Exception {

    private int code;

    private String message;

    public OperationException(int code, String message){
        super(message);
        this.code = code;
        this.message = message;
    }
}
