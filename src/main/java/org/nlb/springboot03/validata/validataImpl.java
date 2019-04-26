package org.nlb.springboot03.validata;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;
@Component
public class validataImpl implements InitializingBean {
    private Validator validator;
    //初始化后会回调该bean中的这个方法
    @Override
    public void afterPropertiesSet() throws Exception {
        //将validata通过工厂的方式实例化一个校验器（对于校验是我们最好的是需要一个生产一个）
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }
    public validataResult validate(Object bean){
        validataResult validataResult = new validataResult();
        Set<ConstraintViolation<Object>> constraintViolations= validator.validate(bean);
        if(constraintViolations.size()>0){
            validataResult.setHasError(true);
            constraintViolations.forEach(constraintViolation->{
                String errorMessage = constraintViolation.getMessage();
                String prop = constraintViolation.getPropertyPath().toString();
                validataResult.getMap().put(prop,errorMessage);
            });
        }
        return validataResult;
    }
}
