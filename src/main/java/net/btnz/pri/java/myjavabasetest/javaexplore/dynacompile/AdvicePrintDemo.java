//package net.btnz.pri.java.myjavabasetest.javaexplore.dynacompile;
//
//import jdk.internal.org.objectweb.asm.ClassWriter;
//import jdk.internal.org.objectweb.asm.tree.ClassNode;
//import jdk.internal.org.objectweb.asm.tree.*;
//import org.objectweb.asm.ClassReader;
//
//import javax.tools.JavaFileObject;
//import java.io.*;
//
//import static jdk.internal.org.objectweb.asm.Opcodes.GETSTATIC;
//import static jdk.internal.org.objectweb.asm.Opcodes.INVOKEVIRTUAL;
//
///**
// * Created by zhangsongwei on 2016/12/7.
// */
//public class AdvicePrintDemo {
//    public static void main(String[] args) throws IOException {
//        String demoFilePath = PrintDemo.class.getClassLoader().getResource("").getPath() + PrintDemo.class.getName().replace('.', File.separatorChar) + JavaFileObject.Kind.CLASS.extension;
//        String outputFilePath = PrintDemo.class.getClassLoader().getResource("").getPath() + PrintDemo.class.getName().replace('.', File.separatorChar) + "Modified" + JavaFileObject.Kind.CLASS.extension;
//        System.out.println(PrintDemo.class.getPackage().getName());
//        System.out.println(demoFilePath);
//
//        InputStream is = new FileInputStream(demoFilePath);
//        ClassReader cr = new ClassReader(is);
//        ClassNode cn = new ClassNode();
//        cr.accept(cn, 0);
//        for(Object object : cn.methods){
//            MethodNode mn = (MethodNode) object;
//            if("<init>".equals(mn.name) || "<clinit>".equals(mn.name)) {
//                continue;
//            }
//            InsnList insns = mn.instructions;
//            InsnList il = new InsnList();
//            il.add(new FieldInsnNode(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;"));
//            il.add(new LdcInsnNode("Enter method -> " + mn.name));
//            il.add(new MethodInsnNode(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V"));
//            insns.insert(il);
//            mn.maxStack += 3;
//        }
//        ClassWriter cw = new ClassWriter(0);
//        cn.accept(cw);
//        byte[] b = cw.toByteArray();
//
//        is.close();
//        OutputStream os = new FileOutputStream(outputFilePath);
//        os.write(b, 0, b.length);
//        os.close();
//    }
//}
