package org.nlb.springboot03.service.serviceImpl;

import org.apache.commons.lang3.StringUtils;
import org.nlb.springboot03.dao.userMapper;
import org.nlb.springboot03.dao.user_passwordMapper;
import org.nlb.springboot03.error.ExceptionMessage;
import org.nlb.springboot03.error.enumError;
import org.nlb.springboot03.object.user;
import org.nlb.springboot03.object.user_password;
import org.nlb.springboot03.service.model.userModel;
import org.nlb.springboot03.service.userService;
import org.nlb.springboot03.validata.validataImpl;
import org.nlb.springboot03.validata.validataResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Validation;

@Service
public class userServiceImpl implements userService {
    @Autowired
    userMapper usermapper;
    @Autowired
    user_passwordMapper user_passwordMapper;
    @Autowired
    validataImpl validator;

    @Override
    public userModel getid(int i) {
        user user = usermapper.selectByPrimaryKey(i);
        if (user == null) {
            return null;
        }
        user_password user_password = user_passwordMapper.selectById(i);
        userModel userModel = conveterUserModel(user, user_password);
        return userModel;
    }

    @Override
    @Transactional
    public void register(userModel userModel) throws ExceptionMessage {
        if (userModel == null) {
            throw new ExceptionMessage(enumError.USER_NOT_EXIST);
        }
//        }if(StringUtils.isEmpty(userModel.getName())||StringUtils.isEmpty(userModel.getTelephone())||
//        userModel.getGender()==0||userModel.getAge()==null){
//            throw new ExceptionMessage(enumError.PARAMETERS);
//        }
        validataResult result = validator.validate(userModel);
        if (result.isHasError()) {
            throw new ExceptionMessage(enumError.PARAMETERS, result.getErrorMessage());
        }
        user user = conveterUser(userModel);
        try {
            usermapper.insertSelective(user);
        } catch (DuplicateKeyException ex) {
            throw new ExceptionMessage(enumError.DUPLICATE_PHONENUMBER, "手机号重复");
        }

        int id = usermapper.selectFromTelephone(user.getTelephone());
        user_password user_password = conveterPassword(userModel, id);
        user_passwordMapper.insertSelective(user_password);

    }

    @Override
    public userModel validateService(String str, String EncoderNumber) throws ExceptionMessage {
        int id= usermapper.selectFromTelephone(str);
        user_password user_password = user_passwordMapper.selectById(id);
        if(!StringUtils.equals(EncoderNumber,user_password.getPassword())){
            throw new ExceptionMessage(enumError.ERROR);
        }
        user user = usermapper.selectFromTelephone2(str);
        userModel userModel = this.conveterUserModel(user,user_password);
        return userModel;
    }

    //密码的获取及赋值
    public user_password conveterPassword(userModel userModel,int id){
        if(userModel==null){
            return null;
        }else{
            user_password user_password = new user_password();
            user_password.setPassword(userModel.getPassword());
            user_password.setUserId(id);//因为id是自增的，不用管，但是这个需要赋值，外键关联
            return user_password;
        }
    }
    //从model转化为实体类,model多了一个password
    public user conveterUser(userModel userModel){
        if(userModel==null){
            return null;
        }else{
            user user = new user();
            BeanUtils.copyProperties(userModel,user);
            return user;
        }
    }
    //从实体类转为model
    private userModel conveterUserModel(user user, user_password user_password){
        userModel userModel = new userModel();
        if(user!=null){
        BeanUtils.copyProperties(user,userModel);
        }
        if(user_password!=null) {
            userModel.setPassword(user_password.getPassword());
        }
        return userModel;
    }
}
