package org.nlb.springboot03.response;

public class responseResult {
    private String status;
    private Object data;
    public static responseResult create(Object result){
        return responseResult.create(result,"sucess");
    }
    public static responseResult create(Object result,String status){
        responseResult result1 =  new responseResult();
        result1.setData(result);
        result1.setStatus(status);
        return result1;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
