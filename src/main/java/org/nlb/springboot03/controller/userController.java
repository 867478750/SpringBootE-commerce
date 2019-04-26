package org.nlb.springboot03.controller;

import com.alibaba.druid.util.StringUtils;
import org.apache.tomcat.util.security.MD5Encoder;
import org.nlb.springboot03.controller.viewObject.userViewModel;
import org.nlb.springboot03.error.ExceptionMessage;
import org.nlb.springboot03.error.enumError;
import org.nlb.springboot03.response.responseResult;
import org.nlb.springboot03.service.model.userModel;
import org.nlb.springboot03.service.serviceImpl.userServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Random;

@Controller
@RequestMapping("/user")
@CrossOrigin(allowedHeaders = "*",allowCredentials = "true")
public class userController extends baseController{
    //extends baseController
    @Autowired
    private userServiceImpl userService;
    @Autowired
    private HttpServletRequest httpServletRequest;

    public static final String contentType= "application/x-www-form-urlencoded";

//登陆
    @RequestMapping("/login")
    @ResponseBody
    public responseResult registerUser(@RequestParam("phoneNumber") String phoneNumber,
                                       @RequestParam("password") String password) throws ExceptionMessage, UnsupportedEncodingException, NoSuchAlgorithmException {
        if(StringUtils.isEmpty(password)||phoneNumber==null){
            throw new ExceptionMessage(enumError.ERROR,"账号密码错误");
        }else{
            userModel userModel = this.validate(phoneNumber,password);
            this.httpServletRequest.getSession().setAttribute("login","true");
            this.httpServletRequest.getSession().setAttribute("userModel","true");
            System.out.println(userModel);
            return responseResult.create("success");
        }
    }

    public userModel validate(String str,String number) throws UnsupportedEncodingException, NoSuchAlgorithmException, ExceptionMessage {
        String EncoderNumber = this.MD5Conveter(number);
        return userService.validateService(str,EncoderNumber);
    }

    @RequestMapping(value = "/getId",method = RequestMethod.POST,consumes = {contentType})
    @ResponseBody
    public responseResult getUser(@RequestParam("getId") Integer id) throws ExceptionMessage {
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
    @RequestMapping(value = "/register",method = RequestMethod.POST,consumes = {contentType})
    @ResponseBody
    public responseResult register(@RequestParam("name") String name,
                                   @RequestParam("age") Integer age,
                                   @RequestParam("gender") byte gender,
                                   @RequestParam("telephone") String telephone,
                                   @RequestParam("registerMode") String registerMode,
                                   @RequestParam("optCode") String optCode,
                                   @RequestParam("password") String password) throws ExceptionMessage, NoSuchAlgorithmException, UnsupportedEncodingException {
        String oriOptCode = (String)this.httpServletRequest.getSession().getAttribute(telephone);
        if(!StringUtils.equals(optCode,oriOptCode)){
            throw new ExceptionMessage(enumError.PARAMETERS,"opt不符合");
        }else{
            userModel userModel = new userModel();
            userModel.setPassword(this.MD5Conveter(password));
            userModel.setAge(age);
            userModel.setGender(gender);
            userModel.setName(name);
            userModel.setThirdId("byPhone");
            userModel.setTelephone(telephone);
            userModel.setRegisterMode(registerMode);
            userService.register(userModel);
            return responseResult.create("success");
        }
    }

    //MD5
    public static String MD5Conveter(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        Base64.Encoder base64Encoder = Base64.getEncoder();
        return base64Encoder.encodeToString(md5.digest(str.getBytes("utf-8")));
    }



    //opt短信
    @RequestMapping(value = "/userOtp",method = RequestMethod.POST,consumes = {contentType})
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
