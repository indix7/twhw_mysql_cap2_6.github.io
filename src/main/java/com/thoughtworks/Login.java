package com.thoughtworks;

public class Login {
    private String message = "";

    public boolean checkLogin(String info) {
        String[] information = info.split(",");
        if (information.length != 2) {
            return false;
        }
        String name = information[0];
        String passwords = information[1];
        return (checkNameExist(name) && !checkTries(name) && checkPasswords(name, passwords));
    }

    private boolean checkTries(String name) {
        int tries = DataBase.getTries(name);
        if (tries <= 3 && tries != -1) {
            return false;
        } else {
            this.message = "您已3次输错密码，账号被锁定";
            return true;
        }
    }

    private boolean checkPasswords(String name, String passwords) {
        User user = DataBase.login(name, passwords);
        if (user != null) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(user.getName())
                    .append(",欢迎回来！\n您的手机号是")
                    .append(user.getPhone())
                    .append(",邮箱是")
                    .append(user.getEmail());
            this.message = stringBuilder.toString();
            return true;
        } else {
            this.message = "密码或用户名错误\n请重新输入用户名和密码：";
            DataBase.updateTries(name, DataBase.getTries(name) + 1);
            return false;
        }
    }

    private boolean checkNameExist(String name) {
        if (DataBase.checkNameExits(name)) {
            return true;
        } else {
            this.message = "密码或用户名错误\n请重新输入用户名和密码：";
            return false;
        }
    }

    public String getMessage() {
        return message;
    }
}
