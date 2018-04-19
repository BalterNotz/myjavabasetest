package net.btnz.pri.java.myjavabasetest.javaexplore.dynacompile;

import javax.tools.*;
import javax.xml.crypto.URIReferenceException;
import java.io.IOException;
import java.net.URI;
import java.util.Arrays;

/**
 * Created by zhangsongwei on 2016/12/7.
 * JSR 199
 * 动态编译
 */
public class CompilerTest {
    public static void main(String[] args) throws Exception {
        String source = "public class Main {public static void main(String[] args){System.out.println(\"Hello World!\");}}";
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
        StringSourceJavaObject sourceObject = new CompilerTest.StringSourceJavaObject("Main", source);
        Iterable fileObjects = Arrays.asList(sourceObject);
        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, null, null, null, fileObjects);
        boolean result = task.call();
        if(result){
            System.out.println("编译成功。");
        }
    }
    public static boolean compile(String className, String source) throws Exception {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
        StringSourceJavaObject sourceObject = new CompilerTest.StringSourceJavaObject(className, source);
        Iterable fileObjects = Arrays.asList(sourceObject);
        String path = CompilerTest.class.getClassLoader().getResource("").getPath();
        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, null, Arrays.asList("-d", path), null, fileObjects);
        boolean result = task.call();
        return result;
    }
    static class StringSourceJavaObject extends SimpleJavaFileObject {
        private String content = null;
        public StringSourceJavaObject(String name, String content) throws URIReferenceException {
            super(URI.create("string:///" + name.replace('.', '/') + Kind.SOURCE.extension), Kind.SOURCE);
            this.content = content;
        }
        public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
            return content;
        }
    }
}
