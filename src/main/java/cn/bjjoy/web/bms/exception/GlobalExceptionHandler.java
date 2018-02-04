package cn.bjjoy.web.bms.exception;

import cn.bjjoy.web.bms.base.ResponseCode;
import cn.bjjoy.web.bms.base.ResponseResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * Created by bjjoy on 2017/11/15
 **/
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LogManager.getLogger(GlobalExceptionHandler.class);

    @ResponseBody
    @ExceptionHandler(value = OperationException.class)
    public ResponseResult operationExceptionHandler(OperationException oe){
        LOGGER.error(oe.getData());
        LOGGER.error(oe.getMessage(), oe);
        return new ResponseResult(oe.getCode(), oe.getMsg());
    }

    @ResponseBody
    @ExceptionHandler(value = NoHandlerFoundException.class)
    public ResponseResult noHandlerFoundException(NoHandlerFoundException e){
        LOGGER.error(e.getMessage(), e);
        return new ResponseResult(ResponseCode.SYSTEM_ERROR, ResponseCode.SYSTEM_ERROR_TEXT);
    }

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResponseResult defaultExceptionHandler(Exception e){
        LOGGER.error(e.getMessage(),e);
        return new ResponseResult(ResponseCode.SYSTEM_ERROR, ResponseCode.SYSTEM_ERROR_TEXT);
    }
}
