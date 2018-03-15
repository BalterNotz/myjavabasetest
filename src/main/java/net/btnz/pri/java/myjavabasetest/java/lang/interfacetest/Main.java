package net.btnz.pri.java.myjavabasetest.java.lang.interfacetest;

public class Main {
    public static void main(String...args){
        InterfaceTest lambda = () -> "Hello World! 你好！";
        System.out.println(lambda.hello());
        new Thread(() -> System.out.println(
                lambda.defaulthello()
        )).start();
    }
}
