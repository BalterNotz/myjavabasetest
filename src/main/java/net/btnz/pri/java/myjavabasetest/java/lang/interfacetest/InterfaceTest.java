package net.btnz.pri.java.myjavabasetest.java.lang.interfacetest;

public interface InterfaceTest {
    public abstract String hello();

    default String defaulthello() {
        return "defaulthello Hello World! 你好！";
    }
}