package org.nlb.springboot03.error;

public interface comError {
    public int getErrorCode();
    public String getErrorMessage();
    public comError setErrorMessage(String errorMessage);

}
