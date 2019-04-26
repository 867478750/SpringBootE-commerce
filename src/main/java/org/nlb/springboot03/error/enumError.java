package org.nlb.springboot03.error;

public enum enumError implements comError {
    UNKNOW_ERROR(10002,"未知的错误"),
    PARAMETERS(10001,"参数不合法"),
    USER_NOT_EXIST(20001,"用户不存在")
;
    private int errorCode;
    private String errorMessage;
    @Override
    public int getErrorCode() {
        return this.errorCode;
    }
     enumError(int errorCode,String errorMessage){
        this.errorCode=errorCode;
        this.errorMessage=errorMessage;
    }
    @Override
    public String getErrorMessage() {
        return this.errorMessage;
    }
    @Override
    public comError setErrorMessage(String errorMessage) {
        this.errorMessage=errorMessage;
        return this;
    }
}
