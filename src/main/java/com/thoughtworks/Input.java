package com.thoughtworks;

import java.util.Scanner;

public class Input {

    public void process() {
        System.out.println("1. 注册\n" +
                "2. 登录\n" +
                "3. 退出\n" +
                "请输入你的选择(1~3)：");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (input.equals("1")) {
            System.out.println("请输入注册信息(格式：用户名,手机号,邮箱,密码)：");
            while (!this.register(scanner.nextLine())) {
                System.out.print("");
            }
        }
        if (input.equals("2")) {
            System.out.println("请输入用户名和密码(格式：用户名,密码)：");
            while (!this.login(scanner.nextLine())) {
                System.out.print("");
            }
        }
        if (input.equals("3")) {
            scanner.close();
            return;
        }
        this.process();
    }

    private boolean register(String input) {
        Register register = new Register();
        boolean result = register.checkRegister(input);
        System.out.println(register.getMessage());
        return result;
    }

    private boolean login(String input) {
        Login login = new Login();
        boolean result = login.checkLogin(input);
        System.out.println(login.getMessage());
        return result;
    }
}
