package org.nlb.springboot03.controller;

import org.nlb.springboot03.controller.viewObject.userViewModel;
import org.nlb.springboot03.service.model.userModel;
import org.nlb.springboot03.service.serviceImpl.userServiceImpl;
import org.nlb.springboot03.service.userService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class userController {
    @Autowired
    userServiceImpl userService;
    @RequestMapping("/get")
    @ResponseBody
    public userViewModel getUser(@RequestParam("id") Integer id){
        userModel userModel=userService.getid(id);
        return converterViewModel(userModel);
    }

    public userViewModel converterViewModel(userModel userModel){
        if(userModel == null){
            return null;
        }else {
            userViewModel userViewModel = new userViewModel();
            BeanUtils.copyProperties(userModel,userViewModel);
            return userViewModel;
        }
        }
}
