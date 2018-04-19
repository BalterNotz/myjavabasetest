#ASM操作Java Bytecode

source: http://asm.ow2.org/index.html

ASM is an all purpose Java bytecode manipulation and analysis framework. It can be used to modify existing classes or dynamically generate classes, directly in binary form. Provided common transformations and analysis algorithms allow to easily assemble custom complex transformations and code analysis tools.

ASM offer similar functionality as other bytecode frameworks, but it is focused on simplicity of use and performance. Because it was designed and implemented to be as small and as fast as possible, it makes it very attractive for using in dynamic systems*.

(*) ASM can of course be used in a static way too.

The best way to learn to use ASM is to write a Java source file that is equivalent to what you want to generate and then use the ASMifier mode of the Bytecode Outline plugin for Eclipse (or the ASMifier tool) to see the equivalent ASM code. If you want to implement a class transformer, write two Java source files (before and after transformation) and use the compare view of the plugin in ASMifier mode to compare the equivalent ASM code.

#使用ASM操作Java字节码，实现AOP原理

source: http://blog.csdn.net/catoop/article/details/50629921

本文通过一个的例子来实现：使用ASM动态生成Java字节码文件（.class） 或者 加载字节码后动态修改字节码，添加我们需要执行的代码，来模拟实现spring AOP。

使用 asm-5.0.3.jar 
demo工程的package为com.shanhy.demo.asm.hello

5个Java文件： 
AopClassAdapter.java 用来处理哪些方法需要进行修改 
AopInteceptor.java 要修改的方法中，准备添加的我们自己的代码逻辑 
AopMethodVisitor.java 修改字节码 
HelloGeneratorClass.java 通过asm动态生成java类，测试的main方法也在这里 
MyClassLoader.java 自定义ClassLoader

AopClassAdapter:

    package com.shanhy.demo.asm.hello;

    import org.objectweb.asm.ClassVisitor;
    import org.objectweb.asm.MethodVisitor;
    import org.objectweb.asm.Opcodes;

    public class AopClassAdapter extends ClassVisitor implements Opcodes {

        public AopClassAdapter(int api, ClassVisitor cv) {
            super(api, cv);
        }

        @Override
        public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
            MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
            // 对test开头的方法进行特殊处理
            if (name.startsWith("test")) {
                mv = new AopMethodVisitor(this.api, mv);
            }
            return mv;
        }
    }

AopInteceptor:

    package com.shanhy.demo.asm.hello;

    public class AopInteceptor {

        public static void before(){
            System.out.println(".......before().......");
        }

        public static void after(){
            System.out.println(".......after().......");
        }
    }

AopMethodVisitor:

    package com.shanhy.demo.asm.hello;

    import org.objectweb.asm.MethodVisitor;
    import org.objectweb.asm.Opcodes;

    public class AopMethodVisitor extends MethodVisitor implements Opcodes {

        public AopMethodVisitor(int api, MethodVisitor mv) {
            super(api, mv);
        }

        @Override
        public void visitCode() {
            super.visitCode();
            this.visitMethodInsn(INVOKESTATIC, "com/shanhy/demo/asm/hello/AopInteceptor", "before", "()V", false);
        }

        @Override
        public void visitInsn(int opcode) {
            if (opcode >= IRETURN && opcode <= RETURN)// 在返回之前安插after 代码。
                this.visitMethodInsn(INVOKESTATIC, "com/shanhy/demo/asm/hello/AopInteceptor", "after", "()V", false);
            super.visitInsn(opcode);
        }
    }

HelloGeneratorClass:

    package com.shanhy.demo.asm.hello;

    import java.io.File;
    import java.io.FileOutputStream;
    import java.lang.reflect.Method;

    import org.objectweb.asm.ClassReader;
    import org.objectweb.asm.ClassWriter;
    import org.objectweb.asm.FieldVisitor;
    import org.objectweb.asm.Label;
    import org.objectweb.asm.MethodVisitor;
    import org.objectweb.asm.Opcodes;

    /**
     * 通过ASM生成类的字节码
     * 
     * @author Administrator
     *
     */
    public class HelloGeneratorClass implements Opcodes {

        /**
        * 使用构造Hello类class字节码<br/>
        * package中的Hello.java只是代码原型，本例中只供对比用，没有实际使用到这个类。<br/>
        * ASM代码生成工具使用 bytecode
        *
        * @return
        * @throws Exception
        * @author SHANHY
        * @create 2016年2月3日
        */
        public static byte[] generatorHelloClass() throws Exception {

            ClassWriter cw = new ClassWriter(0);
            FieldVisitor fv;
            MethodVisitor mv;

            cw.visit(V1_7, ACC_PUBLIC + ACC_SUPER, "com/shanhy/demo/asm/hello/Hello", null, "java/lang/Object", null);

            cw.visitSource("Hello.java", null);

            {
                fv = cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "FLAG", "Ljava/lang/String;", null,
                    "\u6211\u662f\u5e38\u91cf");
                fv.visitEnd();
            }
            {
                mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
                mv.visitCode();
                Label l0 = new Label();
                mv.visitLabel(l0);
                mv.visitVarInsn(ALOAD, 0);
                mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
                mv.visitInsn(RETURN);
                Label l1 = new Label();
                mv.visitLabel(l1);
                mv.visitMaxs(1, 1);
                mv.visitEnd();
            }
            {
                mv = cw.visitMethod(ACC_PUBLIC, "display", "()V", null, null);
                mv.visitCode();
                Label l0 = new Label();
                mv.visitLabel(l0);
                mv.visitInsn(ICONST_0);
                mv.visitVarInsn(ISTORE, 1);
                Label l1 = new Label();
                mv.visitLabel(l1);
                Label l2 = new Label();
                mv.visitJumpInsn(GOTO, l2);
                Label l3 = new Label();
                mv.visitLabel(l3);
                mv.visitFrame(Opcodes.F_APPEND, 1, new Object[] { Opcodes.INTEGER }, 0, null);
                mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
                mv.visitLdcInsn(">>>>>>>>>>\u6211\u662f\u5e38\u91cf");
                mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
                Label l4 = new Label();
                mv.visitLabel(l4);
                mv.visitIincInsn(1, 1);
                mv.visitLabel(l2);
                mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
                mv.visitVarInsn(ILOAD, 1);
                mv.visitIntInsn(BIPUSH, 8);
                mv.visitJumpInsn(IF_ICMPLT, l3);
                Label l5 = new Label();
                mv.visitLabel(l5);
                mv.visitInsn(RETURN);
                Label l6 = new Label();
                mv.visitLabel(l6);
                mv.visitMaxs(2, 2);
                mv.visitEnd();
            }
            {
                mv = cw.visitMethod(ACC_PUBLIC, "testList", "()Ljava/util/List;", "()Ljava/util/List<Ljava/lang/String;>;",
                    null);
                mv.visitCode();
                Label l0 = new Label();
                mv.visitLabel(l0);
                mv.visitTypeInsn(NEW, "java/util/ArrayList");
                mv.visitInsn(DUP);
                mv.visitMethodInsn(INVOKESPECIAL, "java/util/ArrayList", "<init>", "()V", false);
                mv.visitVarInsn(ASTORE, 1);
                Label l1 = new Label();
                mv.visitLabel(l1);
                mv.visitVarInsn(ALOAD, 1);
                mv.visitLdcInsn("Tome");
                mv.visitMethodInsn(INVOKEINTERFACE, "java/util/List", "add", "(Ljava/lang/Object;)Z", true);
                mv.visitInsn(POP);
                Label l2 = new Label();
                mv.visitLabel(l2);
                mv.visitVarInsn(ALOAD, 1);
                mv.visitLdcInsn("Jack");
                mv.visitMethodInsn(INVOKEINTERFACE, "java/util/List", "add", "(Ljava/lang/Object;)Z", true);
                mv.visitInsn(POP);
                Label l3 = new Label();
                mv.visitLabel(l3);
                mv.visitVarInsn(ALOAD, 1);
                mv.visitLdcInsn("Lily");
                mv.visitMethodInsn(INVOKEINTERFACE, "java/util/List", "add", "(Ljava/lang/Object;)Z", true);
                mv.visitInsn(POP);
                Label l4 = new Label();
                mv.visitLabel(l4);
                mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
                mv.visitTypeInsn(NEW, "java/lang/StringBuilder");
                mv.visitInsn(DUP);
                mv.visitLdcInsn(">>>>>>>>>>testList > list.size = ");
                mv.visitMethodInsn(INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "(Ljava/lang/String;)V", false);
                mv.visitVarInsn(ALOAD, 1);
                mv.visitMethodInsn(INVOKEINTERFACE, "java/util/List", "size", "()I", true);
                mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(I)Ljava/lang/StringBuilder;",
                    false);
                mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;", false);
                mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
                Label l5 = new Label();
                mv.visitLabel(l5);
                mv.visitVarInsn(ALOAD, 1);
                mv.visitInsn(ARETURN);
                Label l6 = new Label();
                mv.visitLabel(l6);
                mv.visitMaxs(4, 2);
                mv.visitEnd();
            }
            {
                mv = cw.visitMethod(ACC_PUBLIC + ACC_VARARGS, "testMapList", "(Z[Ljava/util/Map;)Ljava/util/List;",
                    "(Z[Ljava/util/Map<Ljava/lang/String;Ljava/lang/String;>;)Ljava/util/List<Ljava/util/Map<Ljava/lang/String;Ljava/lang/String;>;>;",
                    null);
                mv.visitCode();
                Label l0 = new Label();
                mv.visitLabel(l0);
                mv.visitTypeInsn(NEW, "java/util/ArrayList");
                mv.visitInsn(DUP);
                mv.visitMethodInsn(INVOKESPECIAL, "java/util/ArrayList", "<init>", "()V", false);
                mv.visitVarInsn(ASTORE, 3);
                Label l1 = new Label();
                mv.visitLabel(l1);
                mv.visitVarInsn(ILOAD, 1);
                Label l2 = new Label();
                mv.visitJumpInsn(IFEQ, l2);
                Label l3 = new Label();
                mv.visitLabel(l3);
                mv.visitVarInsn(ALOAD, 2);
                mv.visitInsn(DUP);
                mv.visitVarInsn(ASTORE, 7);
                mv.visitInsn(ARRAYLENGTH);
                mv.visitVarInsn(ISTORE, 6);
                mv.visitInsn(ICONST_0);
                mv.visitVarInsn(ISTORE, 5);
                Label l4 = new Label();
                mv.visitJumpInsn(GOTO, l4);
                Label l5 = new Label();
                mv.visitLabel(l5);
                mv.visitFrame(Opcodes.F_FULL, 8,
                    new Object[] { "com/shanhy/demo/asm/hello/Hello", Opcodes.INTEGER, "[Ljava/util/Map;",
                            "java/util/List", Opcodes.TOP, Opcodes.INTEGER, Opcodes.INTEGER, "[Ljava/util/Map;" },
                    0, new Object[] {});
                mv.visitVarInsn(ALOAD, 7);
                mv.visitVarInsn(ILOAD, 5);
                mv.visitInsn(AALOAD);
                mv.visitVarInsn(ASTORE, 4);
                Label l6 = new Label();
                mv.visitLabel(l6);
                mv.visitVarInsn(ALOAD, 3);
                mv.visitVarInsn(ALOAD, 4);
                mv.visitMethodInsn(INVOKEINTERFACE, "java/util/List", "add", "(Ljava/lang/Object;)Z", true);
                mv.visitInsn(POP);
                Label l7 = new Label();
                mv.visitLabel(l7);
                mv.visitIincInsn(5, 1);
                mv.visitLabel(l4);
                mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
                mv.visitVarInsn(ILOAD, 5);
                mv.visitVarInsn(ILOAD, 6);
                mv.visitJumpInsn(IF_ICMPLT, l5);
                mv.visitLabel(l2);
                mv.visitFrame(Opcodes.F_FULL, 4, new Object[] { "com/shanhy/demo/asm/hello/Hello", Opcodes.INTEGER,
                    "[Ljava/util/Map;", "java/util/List" }, 0, new Object[] {});
                mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
                mv.visitTypeInsn(NEW, "java/lang/StringBuilder");
                mv.visitInsn(DUP);
                mv.visitLdcInsn(">>>>>>>>>>testMapList > list.size = ");
                mv.visitMethodInsn(INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "(Ljava/lang/String;)V", false);
                mv.visitVarInsn(ALOAD, 3);
                mv.visitMethodInsn(INVOKEINTERFACE, "java/util/List", "size", "()I", true);
                mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(I)Ljava/lang/StringBuilder;",
                    false);
                mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;", false);
                mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
                Label l8 = new Label();
                mv.visitLabel(l8);
                mv.visitVarInsn(ALOAD, 3);
                mv.visitInsn(ARETURN);
                Label l9 = new Label();
                mv.visitLabel(l9);
                mv.visitMaxs(4, 8);
                mv.visitEnd();
            }
            cw.visitEnd();

            return cw.toByteArray();
        }

        /**
         * 生成保存class文件
         *
         * @param args
         * @author SHANHY
         * @create 2016年2月3日
         */
        public static void main3(String[] args) {
            try {
                byte[] data = generatorHelloClass();
                File file = new File("H:/Hello.class");
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(data);
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /**
         * AOP测试
         *
         * @param args
         * @author SHANHY
         * @create  2016年2月3日
         */
        public static void main(String[] args) {
            try {
                byte[] data = generatorHelloClass();

                ClassWriter cw = new ClassWriter(0);
                ClassReader cr = new ClassReader(data);
                cr.accept(new AopClassAdapter(ASM5,  cw), ClassReader.SKIP_DEBUG);

                data = cw.toByteArray();

                MyClassLoader myClassLoader = new MyClassLoader();
                Class<?> helloClass = myClassLoader.defineClass("com.shanhy.demo.asm.hello.Hello", data);
                Object obj = helloClass.newInstance();
                Method method = helloClass.getMethod("display", null);
                method.invoke(obj, null);

                method = helloClass.getMethod("testList", null);
                Object result = method.invoke(obj, null);
                System.out.println(result);

                Class<?> stuClass = Thread.currentThread().getContextClassLoader().loadClass("com.shanhy.demo.asm.example.StudentServiceImpl");
                obj = stuClass.newInstance();
                method = stuClass.getMethod("print", String.class);
                System.out.println(method.invoke(obj, "单单"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

HelloGeneratorClass:

    package com.shanhy.demo.asm.hello;

    import org.objectweb.asm.Opcodes;

    /**
     * 自定义ClassLoader
     *
     * @author   单红宇(365384722)
     * @myblog  http://blog.csdn.net/catoop/
     * @create    2016年2月2日
     */
    public class MyClassLoader extends ClassLoader implements Opcodes {

        public MyClassLoader() {
            super();
        }

        public MyClassLoader(ClassLoader parent) {
            super(parent);
        }

        @Override
        public Class<?> loadClass(String name) throws ClassNotFoundException {
            return super.loadClass(name);
        }

        public Class<?> defineClass(String name, byte[] b){
            return super.defineClass(name, b, 0, b.length);
        }
    }

最后这个是我们动态生成的Hello类的源码原型。

    package com.shanhy.demo.asm.hello;

    import java.util.ArrayList;
    import java.util.List;
    import java.util.Map;

    public class Hello {

        // 声明 一个常量
        public static final String FLAG = "我是常量";

        // 普通方法
        public void display(){
            for (int i = 0; i < 8; i++) {
                System.out.println(">>>>>>>>>>" + FLAG);
            }
        }

        // 带有List返回值
        public List<String> testList(){
            List<String> list = new ArrayList<>();
            list.add("Tome");
            list.add("Jack");
            list.add("Lily");
            System.out.println(">>>>>>>>>>testList > list.size = " + list.size());
            return list;
        }

        // 泛型返回值，包含List和Map
        // 两个参数，参数为Map动参
        public List<Map<String, String>> testMapList(boolean val, Map<String, String>... map){
            List<Map<String, String>> list = new ArrayList<>();
            if(val){
                for (Map<String, String> m : map) {
                list.add(m);
                }
            }
            System.out.println(">>>>>>>>>>testMapList > list.size = " + list.size());
            return list;
        }
    }

实际项目中一般不需要我们去用字节码来生成类，只有在特殊需求的时候才会使用。
话说“不常用的东西解决不常见的问题”嘛。