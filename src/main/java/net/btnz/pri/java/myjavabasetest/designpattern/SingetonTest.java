package net.btnz.pri.java.myjavabasetest.designpattern;

import java.lang.reflect.Constructor;

/**
 * Created by BalterNotz on 2016/12/6.
 * 单例模式要防止反射生成实例
 * 可用的办公法是在类中加一个静态的变量(boolean)用于标实是否已经存在一个实例
 * 若已存在，则在试图生成新的实例时，抛出异常。
 */
public class SingetonTest {

    private static SingetonTest singleton = null;
    private int s = 0;

    // 构造方法是私有的
    private SingetonTest(){}

    private static boolean flag = false;

//    // 构造方法是私有的
//    private SingetonTest(){
//        if(flag){
//            flag = !flag;
//        }
//        else{
//            try {
//                throw new Exception("duplicate instance create error!" + SingetonTest.class.getName());
//            } catch (Exception e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//        }
//    }

    // 同步的获取实例方法
    public static synchronized SingetonTest getInstance(){
        // 懒汉模式的单例方法
        if(null == singleton){
            singleton = new SingetonTest();
        }
        return singleton;
    }


    public int getS() {
        return s;
    }

    public void setS(int s) {
        this.s = s;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        try {
            Constructor con = SingetonTest.class.getDeclaredConstructor();
            con.setAccessible(true);
            // 通过反射获取实例
            SingetonTest singetonTest1 = (SingetonTest)con.newInstance();
            SingetonTest singetonTest2 = (SingetonTest)con.newInstance();
            // 常规方法获取实例
            SingetonTest singetonTest3 = SingetonTest.getInstance();
            SingetonTest singetonTest4 = SingetonTest.getInstance();
            // 测试输出
            System.out.println("singetonTest1.equals(singetonTest2) :" +  singetonTest1.equals(singetonTest2));
            System.out.println("singetonTest3.equals(singetonTest4) :" +  singetonTest3.equals(singetonTest4));
            System.out.println("singetonTest1.equals(singetonTest3) :" +  singetonTest1.equals(singetonTest3));
            singetonTest1.setS(1);
            singetonTest2.setS(2);
            singetonTest3.setS(3);
            singetonTest4.setS(4);
            System.out.println("1:" + singetonTest1.getS() + "  2:" + singetonTest2.getS()+ "  3:" + singetonTest3.getS()+ "  4:" + singetonTest4.getS());

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}