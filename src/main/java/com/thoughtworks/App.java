package com.thoughtworks;

public class App {

    public static void main(String[] args) {
        DataBase.createDB();

//    test();

        Input input = new Input();
        input.process();
    }

    private static void test() {
        //测试用语句，第一次后注释掉，name是主键
        DataBase.register(new User("test001", "12345678910", "test@gmail.com", "123asd456"));
        DataBase.register(new User("test002", "12345678900", "test002@gmail.com", "123asd456"));
        DataBase.register(new User("test003", "12345678999", "test003@gmail.com.cn", "123asd456"));
    }
}
