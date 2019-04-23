package org.nlb.springboot03.controller;

import org.nlb.springboot03.controller.viewObject.userViewModel;
import org.nlb.springboot03.error.ExceptionMessage;
import org.nlb.springboot03.error.enumError;
import org.nlb.springboot03.response.responseResult;
import org.nlb.springboot03.service.model.userModel;
import org.nlb.springboot03.service.serviceImpl.userServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class userController {
    @Autowired
    userServiceImpl userService;
    @RequestMapping("/get")
    @ResponseBody
    public responseResult getUser(@RequestParam("id") Integer id) throws ExceptionMessage {
        userModel userModel=userService.getid(id);
        userViewModel user= converterViewModel(userModel);
        if(user==null){
            throw new ExceptionMessage(enumError.USER_NOT_EXIST);
        }
        return responseResult.create(user);
    }
    public userViewModel converterViewModel(userModel userModel) {
        if(userModel == null){
            return null;
        }else{
            userViewModel userViewModel = new userViewModel();
            BeanUtils.copyProperties(userModel,userViewModel);
            return userViewModel;
            }
        }
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object handlerException(HttpRequest httpRequest,Exception ex){
        responseResult responseResult = new responseResult();
        responseResult.setStatus("fail");
        responseResult.setData(ex);
        return responseResult;
    }


}
