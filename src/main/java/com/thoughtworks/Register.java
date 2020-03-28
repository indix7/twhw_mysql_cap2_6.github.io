package com.thoughtworks;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Register {
    private String message = "";

    public boolean checkRegister(String info) {
        String[] information = info.split(",");
        if (information.length != 4) {
            this.message = "格式错误\n请按正确格式输入注册信息：";
            return false;
        }
        User user = new User(information[0], information[1], information[2], information[3]);
        if (checkUserName(user)
                && checkCellNumber(user)
                && checkEmailAddress(user)
                && checkPasswords(user)) {
            this.saveInfo(user);
            return true;
        } else {
            return false;
        }
    }

    private boolean checkUserName(User user) {
        if (user.getName().length() >= 2 && user.getName().length() <= 10) {
            return true;
        } else {
            this.message = "用户名不合法\n请输入合法的注册信息：";
            return false;
        }
    }

    private boolean checkCellNumber(User user) {
        String regex = "1\\d{10}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(user.getPhone());
        if (matcher.find()) {
            return true;
        } else {
            this.message = "手机号不合法\n请输入合法的注册信息：";
            return false;
        }
    }

    private boolean checkEmailAddress(User user) {
        String regex = "\\w+@\\w(.\\w+)+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(user.getEmail());
        if (matcher.find()) {
            return true;
        } else {
            this.message = "邮箱不合法\n请输入合法的注册信息：";
            return false;
        }
    }

    private boolean checkPasswords(User user) {
        String regex = "(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,16}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(user.getPasswords());
        if (matcher.find()) {
            return true;
        } else {
            this.message = "密码不合法\n请输入合法的注册信息：";
            return false;
        }
    }

    private void saveInfo(User user) {
        this.message = user.getName() + ", 恭喜你注册成功！";
        DataBase.register(user);
    }

    public String getMessage() {
        return message;
    }
}
