package org.nlb.springboot03.service.serviceImpl;

import org.nlb.springboot03.dao.userMapper;
import org.nlb.springboot03.dao.user_passwordMapper;
import org.nlb.springboot03.object.user;
import org.nlb.springboot03.object.user_password;
import org.nlb.springboot03.service.model.userModel;
import org.nlb.springboot03.service.userService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class userServiceImpl implements userService {
    @Autowired
    userMapper usermapper;
    @Autowired
    user_passwordMapper user_passwordMapper;
    @Override
    public userModel getid(int i) {
        user user= usermapper.selectByPrimaryKey(i);
        if(user==null){
            return null;
        }
        user_password user_password=user_passwordMapper.selectById(i);
         userModel userModel=conveterUserModel(user,user_password);
        return userModel;
    }

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
