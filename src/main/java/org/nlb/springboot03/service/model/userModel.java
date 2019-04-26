package org.nlb.springboot03.service.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class userModel {
    private int id;
    @NotBlank(message = "用户名不能为空")
    private String name;
    private byte gender;
    @NotNull
    @Max(value = 150,message = "不能超150岁")
    @Min(value = 0,message = "最小为0岁")
    private Integer age;
    @NotBlank(message = "手机号不能为空")
    private String telephone;
    private String registerMode;
    private String thirdId;
    @NotBlank(message = "密码不能为空")
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "userModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                ", telephone='" + telephone + '\'' +
                ", registerMode='" + registerMode + '\'' +
                ", thirdId='" + thirdId + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte getGender() {
        return gender;
    }

    public void setGender(byte gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getRegisterMode() {
        return registerMode;
    }

    public void setRegisterMode(String registerMode) {
        this.registerMode = registerMode;
    }

    public String getThirdId() {
        return thirdId;
    }

    public void setThirdId(String thirdId) {
        this.thirdId = thirdId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
