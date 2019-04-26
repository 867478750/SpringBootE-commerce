package org.nlb.springboot03.validata;

import org.apache.commons.lang3.StringUtils;


import java.util.HashMap;
import java.util.Map;
public class validataResult {
    private boolean hasError = false;
    private Map<String,String> map= new HashMap<>();

    public boolean isHasError() {
        return hasError;
    }
    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }
    public Map<String, String> getMap() {
        return map;
    }
    public void setMap(Map<String, String> map) {
        this.map = map;
    }
    //获取信息
    public String getErrorMessage(){
        return StringUtils.join(map.values().toArray(),",");
    }
}
