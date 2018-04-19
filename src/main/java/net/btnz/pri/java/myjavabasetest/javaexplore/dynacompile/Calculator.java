package net.btnz.pri.java.myjavabasetest.javaexplore.dynacompile;

import java.lang.reflect.Method;

/**
 * Created by zhangsongwei on 2016/12/7.
 */
public class Calculator {
    public static void main(String[] args) throws Exception {
        String expr = "(Math.PI + Math.E)/2";
        System.out.println(calculate(expr));
    }
    private static double calculate(String expr) throws Exception {
        String packageName = "net.btnz.pri.java.myjavabasetest.javaexplore.dynacompile";
        String className = "CalculatorMain";
        String methodName = "calculate";
        String source = "package " + packageName +"; public class " + className + " { public static double " + methodName + "() { return " + expr + ";}}";
        boolean result = CompilerTest.compile(className, source);
        if(!result){
            throw new Exception("错误的表达式");
        }
        ClassLoader loader = Calculator.class.getClassLoader();
        try{
            Class clazz = loader.loadClass(packageName + "." + className);
            Method method = clazz.getMethod(methodName, new Class[] {});
            Object value = method.invoke(null, new Class[] {});
            return (Double) value;
        } catch (Exception e){
            throw new Exception(e);
        }
    }
}
