package org.nlb.springboot03.controller;

import org.nlb.springboot03.error.ExceptionMessage;
import org.nlb.springboot03.error.enumError;
import org.nlb.springboot03.response.responseResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class baseController {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object handlerException(HttpServletRequest httpRequest, Exception ex) {
        responseResult responseResult = new responseResult();
        Map<String,Object> response = new HashMap<>();
        responseResult.setStatus("fail");
        if (ex instanceof ExceptionMessage){
            ExceptionMessage exceptionMessage = (ExceptionMessage) ex;
            response.put("ErrorCode",exceptionMessage.getErrorCode());
            response.put("ErrorMessage",exceptionMessage.getErrorMessage());
            responseResult.setData(response);
            return responseResult;
        }else {
            response.put("ErrorCode", enumError.UNKNOW_ERROR.getErrorCode());
            response.put("ErrorMessage",enumError.UNKNOW_ERROR.getErrorMessage());
            responseResult.setData(response);
            return responseResult;
        }
    }



}
