package org.nlb.springboot03.controller;

import com.alibaba.druid.util.StringUtils;
import org.nlb.springboot03.controller.viewObject.userViewModel;
import org.nlb.springboot03.error.ExceptionMessage;
import org.nlb.springboot03.error.enumError;
import org.nlb.springboot03.response.responseResult;
import org.nlb.springboot03.service.model.userModel;
import org.nlb.springboot03.service.serviceImpl.userServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Controller
@RequestMapping("/user")
@CrossOrigin
public class userController extends baseController{
    @Autowired
    private userServiceImpl userService;
    @Autowired
    private HttpServletRequest httpServletRequest;

    public static final String contentType= "application/x-www-form-urlencoded";


    @RequestMapping("/get")
    @ResponseBody
    public responseResult getUser(@RequestParam("id") Integer id) throws ExceptionMessage {
        userModel userModel=userService.getid(id);
        userViewModel user= converterViewModel(userModel);
        //
        if(user==null) {
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
    //用户注册
    public responseResult register(@RequestParam("name") String name,
                                   @RequestParam("age") Integer age,
                                   @RequestParam("gender") byte gender,
                                   @RequestParam("telephone") String telephone,
                                   @RequestParam("registerMode") String registerMode,
                                   @RequestParam("thirdId")String thirdId,
                                   @RequestParam("optCode") String optCode) throws ExceptionMessage {
        String oriOptCode = (String)this.httpServletRequest.getSession().getAttribute(telephone);
        if(!StringUtils.equals(optCode,oriOptCode)){
            throw new ExceptionMessage(enumError.PARAMETERS,"opt不符合");
        }else{
            return null;
        }
    }



    //opt短信
    @RequestMapping(value = "/userOpt",method = RequestMethod.POST,consumes = {contentType})
    @ResponseBody
    public responseResult optPhone(@RequestParam("phoneNumber")String telephone){
        //生成opt验证码
        Random random = new Random();
        int randomInt = random.nextInt(9999);
        responseResult result =new responseResult();
        randomInt+=1000;
        String opt = String.valueOf(randomInt);
        //opt与手机号码关联,一般通过redis来进行操作，redis速度快，并且本身就是kv属性，可有覆盖的方法，还有expire等
        //但还没试过，而且redis在我的linux上，不好调试，先用httpSession的方法
        //opt验证码通过短信的通道发送到手机（
        System.out.println(opt);
        System.out.println(telephone);
        httpServletRequest.getSession().setAttribute(telephone,opt);
        result.setData(opt);
        result.setStatus("success");
        return result;
    }



}
