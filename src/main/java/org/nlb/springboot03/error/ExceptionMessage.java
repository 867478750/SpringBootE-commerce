package org.nlb.springboot03.error;

public class ExceptionMessage extends Exception implements comError {
    private comError comError;
    public ExceptionMessage(comError comError){
        super();
        this.comError = comError;
    }
    public ExceptionMessage(comError comError,String errorMessage){
        super();
        this.comError=comError;
        comError.setErrorMessage(errorMessage);
    }
    @Override
    public int getErrorCode() {
        return this.comError.getErrorCode();
    }
    @Override
    public String getErrorMessage() {
        return this.comError.getErrorMessage();
    }
    @Override
    public comError setErrorMessage(String errorMessage) {
        this.comError.setErrorMessage(errorMessage);
        return this;
    }
}
