#1.5

1. 线程池
2. foreach
3. 自动装箱与拆箱：
3. 枚举(常用来设计单例模式)
3. 静态导入
3. 可变参数
3. 内省
3. 元数据（Metadata）JDK1.5引入了Annotation的概念来描述元数据。
4. Java 泛型（generics）

#1.6

1. Web服务元数据
2. 脚本语言支持
3. JTable的排序和过滤
4. 更简单,更强大的JAX-WS
5. 轻量级Http Server
6. 嵌入式数据库 Derby
7. Compiler API
8. 插入式注解处理API(Pluggable Annotation Processing API)
9. 用Console开发控制台程序

#1.7
1. switch中可以使用字串了
2. 运用List tempList = new ArrayList<>(); 即泛型实例化类型自动推断
3. 语法上支持集合，而不一定是数组
4. 新增一些取环境信息的工具方法
5. Boolean类型反转，空指针安全,参与位运算
6. 两个char间的equals
7. 安全的加减乘除
8. map集合支持并发请求，且可以写成 Map map = {name:"xxx",age:18};
9. 新的整数字面表达方式 - "0b"前缀和"_"连数符
10. 在单个catch代码块中捕获多个异常，以及用升级版的类型检查重新抛出异常
11. try-with-resources语句
12. Fork/Join框架。

#1.8
1. Java的并行API演变历程
1. Java并行新特性
1. 允许在接口中有默认方法实现
2. Lambda表达式
3. 函数式接口
4. 方法和构造函数引用
5. Lambda的范围
6. 内置函数式接口
7. Streams
8. Parallel Streams
9. Map
10. 时间日期API
11. Annotations


#1.9
1. Jigsaw 项目;模块化源码
2. 简化进程API
3. 轻量级 JSON API
4. 钱和货币的API
5. 改善锁争用机制
6. 代码分段缓存
7. 智能Java编译, 第二阶段
8. HTTP 2.0客户端
9. Kulla计划: Java的REPL实现

=====================================

#JDK1.5新特性：
##线程池
在多线程大师Doug Lea的贡献下，在JDK1.5中加入了许多对
并发特性的支持，例如：线程池。
##foreach
    for(Object o : objs) {
        ...
    }
##1. 自动装箱与拆箱
    Integer iObj = 3;
    System.out.println(iObj + 12);
    Integer i1 = 137(-128--127范围时，为true);
    Integer i2 = 137(-128--127范围时，为true);
    System.out.println(i1 == i2); //false，但是括号中时却返回ture，原因是Integer采用的是享元模式
    Integer i3 = Integer.valueOf(213);
    Integer i4 = Integer.valueOf(213);
    System.out.println(i3==i4);//同上，另一种包装形式

##2. 枚举
    public class EnumTest {
        public static void main(String[] args) {
            WeekDay1 weekDay = WeekDay1.MON;
            System.out.println(weekDay.nextDay());
            WeekDay weekDay2 = WeekDay.FRI;
            System.out.println(weekDay2);
            System.out.println(weekDay2.name());
            System.out.println(weekDay2.ordinal());
            System.out.println(WeekDay.valueOf("SUN").toString());
            System.out.println(WeekDay.values().length);
            new Date(300){};
        }

        public enum WeekDay{
           SUN(1),MON(),TUE,WED,THI,FRI,SAT;
           private WeekDay(){System.out.println("first");}
           private WeekDay(int day){System.out.println("second");}
        }


        public enum TrafficLamp{
            RED(30){
                public TrafficLamp nextLamp(){
                    return GREEN;
                }
            },
            GREEN(45){
                public TrafficLamp nextLamp(){
                    return YELLOW;
                }
            },
            YELLOW(5){
                public TrafficLamp nextLamp(){
                    return RED;
                }
            };
            public abstract TrafficLamp nextLamp();
            private int time;
            private TrafficLamp(int time){this.time = time;}
        }
    }

##3. 静态导入
import static静态导入是JDK1.5中的新特性。一般我们导入一个类都用 
import com…..ClassName;而静态导入是这样：
import static com…..ClassName.*;这里的多了个static，还有就是
类名ClassName后面多了个 .* ，意思是导入这个类里的静态方法。当然，
也可以只导入某个静态方法，只要把 .* 换成静态方法名就行了。然后在
这个类中，就可以直接用方法名调用静态方法，而不必用ClassName.方法
名 的方式来调用。
    
    import static java.lang.Math.*;
    public class StaticImport {
        public static void main(String[] args){
            int x = 1;
            try {
                x++;
            } finally {
                System.out.println("template");
            }
            System.out.println(x);
            System.out.println(max(3, 6));
            System.out.println(abs(3 - 6));
        }
    }

##4. 可变参数
    public class VarableParameter {
        public static void main(String[] args) {
            System.out.println(add(2,3));
            System.out.println(add(2,3,5));
        }
        
        public static int add(int x,int... args){
            int sum = x;
            for(int arg : args){
                sum += arg;
            }
            return sum;
        }
    }
##5. 内省
<http://blog.csdn.net/u010445297/article/details/60967146>
<http://blog.csdn.net/megustas_jjc/article/details/53525026>
<http://ask.csdn.net/questions/374973>

内省一般用在框架内部用于设置对象的属生。

###Wiki上的解释：
>在计算机科学中，内省是指计算机程序在运行时（Run time）检查对象
（Object）类型的一种能力，通常也可以称作运行时类型检查。 不应该
将内省和反射混淆。相对于内省，反射更进一步，是指计算机程序在运行
时（Run time）可以访问、检测和修改它本身状态或行为的一种能力。

反射是在运行状态把Java类中的各种成分映射成相应的Java类，可以动态
的获取所有的属性以及动态调用任意一个方法，强调的是运行状态。

内省(IntroSpector)是Java 语言对 Bean 类属性、事件的一种缺省处理
方法。　JavaBean是一种特殊的类，主要用于传递数据信息，这种类中的方
法主要用于访问私有的字段，且方法名符合某种命名规则。如果在两个模块
之间传递信息，可以将信息封装进JavaBean中，这种对象称为“值对象”
(Value Object)，或“VO”。方法比较少。这些信息储存在类的私有变量中
，通过set()、get()获得。内省机制是通过反射来实现的，BeanInfo用来
暴露一个bean的属性、方法和事件，以后我们就可以操纵该JavaBean的属性。

在Java内省中，用到的基本上就是上述几个类。 通过BeanInfo这个类就可
以获取到类中的方法和属性。例如类 A 中有属性 name, 那我们可以通过 
getName,setName 来得到其值或者设置新的值。通过 getName/setName 
来访问 name 属性，这就是默认的规则。 Java 中提供了一套 API 用来访
问某个属性的 getter/setter 方法，通过这些 API 可以使你不需要了解
这个规则（但你最好还是要搞清楚），这些 API 存放于包 java.beans 中,
一般的做法是通过类 Introspector 的 getBeanInfo方法 来获取某个对
象的 BeanInfo 信息,然后通过 BeanInfo 来获取属性的描述器
(PropertyDescriptor),通过这个属性描述器就可以获取某个属性对应的 
getter/setter 方法,然后我们就可以通过反射机制来调用这些方法，这就
是内省机制。

###JDK内省类库
* java.beans.Introspector：Introspector 类为通过工具学习有关受目
标 Java Bean 支持的属性、事件和方法的知识提供了一个标准方法。
* java.beans.BeanInfo接口：希望提供有关其 bean 的显式信息的 bean 
实现者可以提供某个 BeanInfo 类，该类实现此 BeanInfo 接口并提供有关
其 bean 的方法、属性、事件等显式信息。
* java.beans.PropertyDescriptor：PropertyDescriptor 描述 Java 
Bean 通过一对存储器方法导出的一个属性。


    ReflectPoint pt1 = new ReflectPoint(3,5);
    BeanInfo beanInfo = Introspector.getBeanInfo(pt1.getClass());
    PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
    Object retVal = null;
    for(PropertyDescriptor pd : pds){
        Method methodGetX = pd.getReadMethod();
        retVal = methodGetX.invoke(pt1);
    }
内省操作非常的繁琐，所以所以Apache开发了一套简单、易用的API来操作Bean
的属性——BeanUtils工具包。对于该工具包API的作用及使用方法，我将在下一
篇文章进行总结，这里先提供下BeanUtils工具包的下载地址：
http://commons.apache.org/beanutils/；注意：BeanUtils的包依赖于
logging包，logging包的下载地址为：http://commons.apache.org/logging/。

##Java 泛型（generics）

<https://www.cnblogs.com/TIMHY/p/7758200.html>

JDK 5 中引入的一个新特性, 泛型提供了编译时类型安全检测机制，该机制允许
程序员在编译时检测到非法的类型。泛型的本质是参数化类型，也就是说所操作的
数据类型被指定为一个参数。泛型是一种把类型的明确工作推迟到创建对象或者调
用方法的时候才去明确的特殊类型。注意：类型参数只能代表引用型类型，不能是
原始类型（像int,double,char的等）。

泛型出现的原因:

早期的时候，使用Object来代表任意类型。但是这样在向上转型的是没有问题的，
但是在向下转型的时候存在类型转换的问题，这样的程序其实是不安全的。所以
Java在JDK5之后提供了泛型来解决这个问题。

[Java泛型(Generic)理解之一:T和?的区别](http://blog.csdn.net/cloudeagle_bupt/article/details/53311053)

#jdk1.6新特性：
##1. Web服务元数据
Java 里的Web服务元数据跟微软的方案基本没有语义上的区别,自从JDK5添加了
元数据功能(Annotation)之后,SUN几乎重构了整个J2EE体 系, 由于变化很大
,干脆将名字也重构为Java EE, Java EE(当前版本为5.0)将元数据纳入很多规
范当中,这其中就包括Web Services的相关规范, 加入元数据之后的Web 
Services服务器端编程模型就跟上面看到的C#片断差不多了, 这显然比以前的
JAX-RPC编程模型简单(当然, Axis的编程模型也很简单).这里要谈的Web服务
元数据(JSR 181)只是Java Web 服务规范中的一个,它跟Common Annotations
, JAXB2, StAX, SAAJ和JAX-WS等共同构成Java EE 5的Web Services技术堆
栈.

    package WebServices;
    import java.io.File;
    import java.io.IOException;
    import javax.jws.Oneway;
    import javax.jws.WebMethod;
    import javax.jws.WebParam;
    import javax.jws.WebResult;
    import javax.jws.WebService;
    import javax.xml.ws.Endpoint;

    @WebService(targetNamespace="http://blog.csdn.net/chinajash",serviceName="HelloService")
    public class WSProvider {
        @WebResult(name="Greetings")//自定义该方法返回值在WSDL中相关的描述
        @WebMethod
        public String sayHi(@WebParam(name="MyName") String name){
            return "Hi,"+name; //@WebParam是自定义参数name在WSDL中相关的描述
        }
        @Oneway //表明该服务方法是单向的,既没有返回值,也不应该声明检查异常
        @WebMethod(action="printSystemTime",operationName="printSystemTime")//自定义该方法在WSDL中相关的描述
        public void printTime(){
            System.out.println(System.currentTimeMillis());
        }
        public static void main(String[] args) {
            Thread wsPublisher = new Thread(new WSPublisher());
            wsPublisher.start();
        }
        private static class WSPublisher implements Runnable{
            public void run() {
                //发布WSProvider到http://localhost:8888/chinajash/WSProvider这个地址,之前必须调用wsgen命令
                //生成服务类WSProvider的支持类,命令如下:
                //wsgen -cp . WebServices.WSProvider
                Endpoint.publish("http://localhost:8888/chinajash/WSProvider",new WSProvider());
            }
        }
    }


如果想看到Web Services Engine生成的WSDL文件是否遵守上面的元数据, 我们
没有必要将上面的WSProvider部署到支持JSR-181的应用服务器或Servlet形式的
Web Services Engine,现在JDK6已经提供了一个很简单的机制可以用来测试和发
布Web Services,下面讲讲如何在JDK6环境下发布Web Services和查看生成的WSDL

1. 将/bin加入path环境变量
2. 在命令行下切换当前目录到WSProvider的class文件所在的目录,运行下面命令
wsgen -cp . WebServices.WSProvider
在这个例子中会生成以下3个类的源代码文件及class文件
SayHi
SayHiResponse
PrintTime
3. 执行如下代码发布WSProvider到
http://localhost:8888/chinajash/WSProvider,在这里可以执行WSProvider
类的main方法就可以
Endpoint.publish("http://localhost:8888/chinajash/WSProvider",
new WSProvider());
4. 在浏览器输入http://localhost:8888/chinajash/WSProvider?wsdl就可以
看到生成的WSDL文件，为了节省篇幅,这里就不把生成的WSDL文件贴上了，大家可
以自己动手试试.

##2. 脚本语言支持
JDK6增加了对脚本语言的支持(JSR 223)， 原理上是将脚本语言编译成bytecode
，这样脚本语言也能享用Java平台的诸多优势，包括可移植性，安全等，另外，由
于现在是编译成 bytecode后再执行，所以比原来边解释边执行效率要高很多。加
入对脚本语言的支持后，对Java语言也提供了以下好处。
1. 许多脚本语言都有动态特性，比如，你不需要用一个变量之前先声明它，你可以
用一个变量存放完全不同类型的对象，你不需要做强制类型转换，因为转换都是自动
的。现在Java语言也可以通过对脚本语言的支持间接获得这种灵活性。
2. 可以用脚本语言快速开发产品原型，因为现在可以Edit-Run，而无需
Edit-Compile-Run，当然，因为Java有非常好的IDE支持，我 们完全可以在IDE
里面编辑源文件，然后点击运行(隐含编译)，以此达到快速开发原型的目的，所以
这点好处基本上可以忽略。
3. 通过引入脚本语言可以轻松实现Java应用程序的扩展和自定义，我们可以把原
来分布在在Java应用程序中的配置逻辑，数学表达式和业务规则提取出来，转用
JavaScript来处理。

Sun的JDK6实现包含了一个基于Mozilla Rhino的 脚本语言引擎，支持JavaScript
，这并不是说明JDK6只支持JavaScript，任何第三方都可以自己实现一个JSR-223
兼容的脚本引擎 使得JDK6支持别的脚本语言，比如，你想让JDK6支持Ruby，那你可
以自己按照JSR 223 的规范实现一个Ruby的脚本引擎类，具体一点，你需要实现
javax.script.ScriptEngine(简单起见，可以继承
 javax.script.AbstractScriptEngine)和
 javax.script.ScriptEngineFactory两个接口。 当然，在你实现自己的脚本
 语言引擎之前，先到scripting.dev.java.net project 这里看看是不是有人已
 经帮你做了工作，这样你就可以直接拿来用就行。
 
###Scripting API
Scripting API是用于在Java里面编写脚本语言程序的API， 在Javax.script
中可以找到Scripting API，我们就是用这个API来编写JavaScript程序，这个
包里面有一个ScriptEngineManager类，它是使用Scripting API的入口，
ScriptEngineManager可以通过jar服务发现(service discovery)机制寻找合
适的脚本引擎类(ScriptEngine)，使用Scripting API的最简单方式只需下面三步
1. 创建一个ScriptEngineManager对象
2. 通过ScriptEngineManager获得ScriptEngine对象
3. 用ScriptEngine的eval方法执行脚本


下面是一个Hello World程序

    package Scripting;
    
    import java.io.File;
    import javax.script.Invocable;
    import javax.script.ScriptEngine;
    import javax.script.ScriptEngineManager;
    import javax.script.ScriptException;
    
    public class HelloScript {
        public static void main(String[] args) throws Exception {
            ScriptEngineManager factory = new ScriptEngineManager();
            //step 1
            ScriptEngine engine = factory.getEngineByName("JavaScript");
            //Step 2
            engine.eval_r("print('Hello, Scripting')");
            
        }
    }
    
Step 3 运行上面程序，控制台会输出Hello, Scripting
上面这个简单的Scripting程序演示了如何在Java里面运行脚本
语言，除此之外，我们还可以利用Scripting API实现以下功能
1. 暴露Java对象为脚本语言的全局变量
2. 在Java中调用脚本语言的方法
3. 脚本语言可以实现Java的接口
4. 脚本语言可以像Java一样使用JDK平台下的类下面的类演示了以上4种功能


    public class ScriptingAPITester {
        public static void main(String[] args) throws Exception {
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("JavaScript"); 
            testScriptVariables(engine);
            //演示如何暴露Java对象为脚本语言的全局变量
            testInvokeScriptMethod(engine);
            //演示如何在Java中调用脚本语言的方法
            testScriptInterface(engine);
            //演示脚本语言如何实现Java的接口
            testUsingJDKClasses(engine);
            //演示脚本语言如何使用JDK平台下的类
        }
        public static void testScriptVariables(ScriptEngine engine) throws ScriptException{
            File file = new File("test.txt");
            engine.put("f", file);
            engine.eval_r("println('Total Space:'+f.getTotalSpace())");
        }
        public static void testInvokeScriptMethod(ScriptEngine engine) throws Exception{
            String script = "function hello(name) {return 'Hello,' + name;}";
            engine.eval_r(script);
            Invocable inv = (Invocable) engine;
            String res = (String)inv.invokeFunction("hello", "Scripting" );
            System.out.println("res:"+res);
        }
        public static void testScriptInterface(ScriptEngine engine) throws ScriptException{
            String script = "var obj = new Object(); obj.run = function() { println('run method called'); }";
            engine.eval_r(script);
            Object obj = engine.get("obj");
            Invocable inv = (Invocable) engine;
            Runnable r = inv.getInterface(obj,Runnable.class);
            Thread th = new Thread(r);
            th.start();
        }
        public static void testUsingJDKClasses(ScriptEngine engine) throws Exception{
            //Packages是脚本语言里的一个全局变量,专用于访问JDK的package
            String js = "function doSwing(t){var f=new Packages.javax.swing.JFrame(t);f.setSize(400,300);f.setVisible(true);}";
            engine.eval_r(js);
            Invocable inv = (Invocable) engine;
            inv.invokeFunction("doSwing", "Scripting Swing" );
        }
    }

###Scripting Tool
SUN 提供的JDK6中有一个命令行工具jrunscript，你可以在/bin下面找到这个工
具，jrunscript是一个脚本语言的解释程序，它独立于脚本语言，但默认是用
JavaScript，我们可以用jrunscript来测试自己写的 脚本语言是否正确，下面是
一个在命令行运行jrunscript的简单例子

    js>println("Hello,JrunScript");
    Hello,JrunScript
    js>9*8
    72.0
    js>
##3. JTable的排序和过滤
原来的JTable基本上是只能显示数据，在JDK6新增了对JTable的排序和过滤功能，
下面代码演示了这两个功能

    public class JTableTester {
        static String data[][] = {
         {"China","Beijing","Chinese"},
         {"America","Washington","English"},
         {"Korea","Seoul","Korean"},
         {"Japan","Tokyo","Japanese"},
         {"France","Paris","French"},
         {"England","London","English"},
         {"Germany","Berlin","German"},
        };
        static String titles[] = {"Country","Capital","Language"};
        public static void main(String[] args) {
         DefaultTableModel m = new DefaultTableModel(data,titles);
         JTable t = new JTable(m);
         final TableRowSorter sorter = new TableRowSorter(m);
         t.setRowSorter(sorter); //为JTable设置排序器

         JScrollPane sPane = new JScrollPane();
         sPane.setViewportView(t);

         JPanel p = new JPanel();
         p.setLayout(new BoxLayout(p,BoxLayout.X_AXIS));
         JLabel l = new JLabel("Criteria:");
         final JTextField tf = new JTextField();
         JButton b = new JButton("Do Filter");
         p.add(l);
         p.add(tf);
         p.add(b);
         b.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
                 if(tf.getText().length()==0){
                     sorter.setRowFilter(null);
                 }else{
                     sorter.setRowFilter(RowFilter.regexFilter(tf.getText()));//为JTable设置基于正则表达式的过滤条件
                 }
             }
         });

         JFrame f = new JFrame("JTable Sorting and Filtering");
         f.getContentPane().add(sPane,BorderLayout.CENTER);
         f.getContentPane().add(p,BorderLayout.SOUTH);
         f.setSize(400,300);
         f.setVisible(true);
        }
    }
    
运行上面程序，单击JTable的某一个title，这个title对应的列就会按照升序/降序
重新排列；在下面的Criteria文本框中输入"ese"，点击"Do Filter"按钮，
JTable将只显示带有"ese"字符串的行，也就是China和Japan两行，如果文本框里
面什么都没有，点击"Do Filter"按钮，这时JTable会显示所有的行。

##4. 更简单,更强大的JAX-WS
###JAX-WS2.0的来历
JAX-WS(JSR-224) 是Java Architecture for XML Web Services的缩写,
简单说就是一种用Java和XML开发Web Services应用程序的框架, 目前版本是
2.0, 它是JAX-RPC 1.1的后续版本, J2EE 1.4带的就是JAX-RPC1.1, 而
Java EE 5里面包括了JAX-WS 2.0,但为了向后兼容,仍然支持JAX-RPC. 现在
,SUN又把JAX-WS直接放到了Java SE 6里面,由于JAX-WS会用到
Common Annotation(JSR 250),Java Web Services Metadata(JSR 181),
 JAXB2(JSR 222), StAX(JSR 173), 所以SUN也必须把后几个原属于Java EE
 范畴的Components下放到Java SE, 现在我们可以清楚地理解了为什么Sun要
 把这些看似跟Java SE没有关系的Components放进来,终极目的就是要在Java
SE里面支持Web Services.

###JAX-WS2.0的架构
JAX-WS不是一个孤立的框架,它依赖于众多其他的规范,本质上它由以下几部分组成
1. 用来开发Web Services的Java API
2. 用来处理Marshal/Unmarshal的XML Binding机制,JAX-WS2.0用JAXB2来处理Java Object与XML之间的映射,Marshalling就是把Java Object映射到XML,Unmarshalling则是把XML映射到Java Object.之所以要做Java Object与XML的映射,是因为最终作为方法参数和返回值的Java Object要通过网络传输协议(一般是SOAP)传送,这就要求必须对Java Object做类似序列化和反序列化的工作,在SOAP中就是要用XML来表示Java object的内部状态
3. 众多元数据(Annotations)会被JAX-WS用来描述Web Services的相关类,包括Common Annotations, Web Services Metadata, JAXB2的元数据和JAX-WS2.0规范自己的元数据.
4. Annotation Processing Tool(APT) 是JAX-WS重要的组成部分,由于JAX-WS2.0规范用到很多元数据,所以需要APT来处理众多的Annotations. 在/bin下有两个命令wsgen和wsimport,就是用到APT和Compiler API来处理碰到的Annotations,wsgen可以为Web Services Provider产生并编译必要的帮助类和相关支持文件,wsimport以WSDL作为输入为Web Service Consumer产生并编译必要的帮助类和相关支持文件.
5. JAX-WS还包括JAX-WS Runtime与应用服务器和工具之间的契约关系

###JAX-WS2.0的编程模型
现在用JAX-WS2.0来编写Web Services非常简单,不像JAX-RPC,JAX-WS可以把
任意POJO暴露为Web Services,服务类不需要实现接口,服务方法也没有必要抛
出RMI异常.下面介绍在JDK6环境下用JAX-WS2.0开发和测试Web Services的步骤

*1. 编写服务类,并用Web Services Metadata(JSR-181)标注这个服务类,我用
我的另一篇BlogJDK6的新特性之十:Web服务元数据中的WSProvider类作为服务类
的例子,在此我重复贴一下WSProvider类的源代码:

    @WebService(targetNamespace="http://blog.csdn.net/chinajash",serviceName="HelloService")
    public class WSProvider {
        @WebResult(name="Greetings")//自定义该方法返回值在WSDL中相关的描述
        @WebMethod
        public String sayHi(@WebParam(name="MyName") String name){
            return "Hi,"+name; //@WebParam是自定义参数name在WSDL中相关的描述
        }
        @Oneway //表明该服务方法是单向的,既没有返回值,也不应该声明检查异常
        @WebMethod(action="printSystemTime",operationName="printSystemTime")//自定义该方法在WSDL中相关的描述
        public void printTime(){
            System.out.println(System.currentTimeMillis());
        }
        public static void main(String[] args) {
            Thread wsPublisher = new Thread(new WSPublisher());
            wsPublisher.start();
        }
        private static class WSPublisher implements Runnable{
            public void run() {
                //发布WSProvider到http://localhost:8888/chinajash/WSProvider这个地址,之前必须调用wsgen命令
                //生成服务类WSProvider的支持类,命令如下:
                //wsgen -cp . WebServices.WSProvider
                Endpoint.publish("http://localhost:8888/chinajash/WSProvider",new WSProvider());
            }
        }
    }

2. 用wsgen生成上面服务类的必要的帮助类,然后调用用EndPoint类的静态方法
publish发布服务类(步骤请参考我的另一篇Blog JDK6的新特性之十:Web服务元数
据),我在这里是将服务类发布到http://localhost:8888/chinajash/WSProvider
3. 用wsimport为服务消费者(也就是服务的客户端)生成必要的帮助类,命令如下:
wsimport http://localhost:8888/chinajash/WSProvider?wsdl
这会在<当前目录>\net\csdn\blog\chinajash下生成客户端的帮助类,在这个例
子中会生成7个类


    HelloService.class
    ObjectFactory.class 
    package-info.class
    PrintSystemTime.class
    SayHi.class
    SayHiResponse.class
    WSProvider.class
4. 在客户端用下面代码即可调用步骤1定义的Web Service


    HelloService hs = new HelloService();
    WSProvider ws = hs.getWSProviderPort();
    System.out.println(ws.sayHi("chinajash"));
    ws.printSystemTime();

调用上述代码后客户端控制台输出
hi,chinajash
服务端控制台输出服务器当前系统时间

##5. 轻量级Http Server
JDK6的新特性之五:轻量级Http Server

JDK6提供了一个简单的Http Server API,据此我们可以构建自己的嵌入式
Http Server,它支持Http和Https协议,提供了HTTP1.1的部分实现，没有
被实现的那部分可以通过扩展已有的Http Server API 来实现,程序员必须
自己实现HttpHandler接口,HttpServer会调用HttpHandler实现类的回调
方法来处理客户端请求,在这里, 我们把一个Http请求和它的响应称为一个
交换,包装成HttpExchange类,HttpServer负责将HttpExchange传给 
HttpHandler实现类的回调方法.下面代码演示了怎样创建自己的Http Server


    public class HTTPServerAPITester {
     public static void main(String[] args) {
         try {
             HttpServer hs = HttpServer.create(new InetSocketAddress(8888),0);//设置HttpServer的端口为8888
             hs.createContext("/chinajash", new MyHandler());//用MyHandler类内处理到/chinajash的请求
             hs.setExecutor(null); // creates a default executor
             hs.start();
         } catch (IOException e) {
             e.printStackTrace();
         }
     }
    }

    class MyHandler implements HttpHandler {
      public void handle(HttpExchange t) throws IOException {
        InputStream is = t.getRequestBody();
        String response = "Happy New Year 2007!--Chinajash";
        t.sendResponseHeaders(200, response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
      }
    }


运行程序后,在浏览器内输入http://localhost:8888/xx,浏览器输出

##6. 嵌入式数据库 Derby
Derby是IBM送给开源社区的又一个礼物，是一个pure java的数据库，现在已经
被列入到java1.6中。不知道对于大数据量的性能如何，但传说中启动derby只
会给JVM添加2M的内存，对那些小数据库应用，比如像用access那种应该是挺有
诱惑力的。另外，麻雀虽小，五脏俱全，功能要比access多得多咯，包括事务处
理，并发，触发器都有，管理又简单，因此自己用来做点工具正好合适。
废话少说，介绍一下我折腾了半天的经验吧。

我的Derby配置过程：
1. 下载db-derby-10.1.3.1-bin.tar.gz,derby_core_plugin_10.1.3.zip
和derby_ui_plugin_1.1.0.zip，把两个插件安装到eclipse上
2. 打开ecllipse,新建一个project
3. 右键这个project，选择Apache Derby，再选择add apache derby native
，发现只是给我的project添加了几个derby的jar，还不是在我看着顺眼的lib目
录里，索性干掉，换上db-derby- 10.1.3.1-bin.tar.gz解压出来以后lib目录
下的jar文件，在Build Path里设置一下；
4. 右键Project，在apache derby里选择start apache derby network server
，控制台可以看到derby启动后打出的“服务器准备在端口 1527 上接受连接。”
5. 右键Project，在apache derby里选择ij(Interactive SQL)，启动SQL控制台；
6. 输入connect jdbc:derby:testdb;create=true; 注意要有单引号，可以在
工程跟目录下创建testdb数据库，可以看到一个新建的目录testdb，那里的文件就是数据库咯；
7. 用标准的SQL语句来建一个数据库试试：
create table test (a varchar(4) not null, b char(2) primary key);
居然可以用，太神奇了，呵呵
8，再插入一条语句试试呢，insert into test(a,b) values(a,11);，嗯，
不错，可以用select 查出来的哦。
9，再插一下：insert into test(a,b) values(a,11);，哦哦，报错了，
“错误 23505：语句异常终止，因为它导致“TEST”上所定义的
“SQL060710092132480”标识的唯一或主键约束或唯一索引中出现重复键值。” 
呵呵。
10，好了，现在可以像你控制的其他数据库一样来控制Derby了。

如果上述方法不行，或者你习惯了在eclipse之外使用和管理数据库，那么可以很
方便的把Derby“装”在系统里。下面我说一下步骤：
1. 把db-derby-10.1.3.1-bin.tar.gz解压到c:\derby,使lib和framework两个目录在c:\derby下边即可
2. 设置环境变量

设置一个c:\derby\framework\embeded\bin或c:\derby\framework\NetworkServe\bin到Path中，这样我们就可以直接执行上边介绍的connect这样的命令而不用每次钻到那个目录下去执行了

设置c:\derby\lib\derby.jar;c:\derby\lib\derbytoos.jar到CLASSPATH中，以便让这些java编成的命令能够正确执行；
3. 打开cmd
4. 敲入startNetworkServer，可以看到像在eclisp中提示的那样启动了server
5. 再打开一个cmd,敲入sysinfo,可以看到derby的环境信息了，注意在java user dir这一项，也许是java用户目录上和上边看到的会有所不同哦，这样在connect jdbc:derby:testdb;create=true;的建的数据库目录就不一样咯。
6. 敲入ij，好了，进入到上边的交互界面，可以建一个数据库看看了。
7. 最后在另外一个cmd中敲入stopNetworkServer就可以关闭数据库了。


如果你两种方法都试过了，那么需要注意的，还是上边步骤5的问题，这个问题是你可能随时会启动一个数据库或新建一个数据库，但如果你刚刚使用derby，你可能还没有察觉。
derby实际上有两种启动方式，一种是嵌入式的，一种是网络服务器的启动。
1. 我们在eclipse中右键start apache derby network server那个，就是网络服务器的启动方式，在这种方式下可以用另外一台计算机在ij中以：
connect jdbc:derby://192.168.0.28:1527/testdb
的方式进行链接。
2. 第二种启动方式是在ij里边就直接
connect jdbc:derby:testdb
这实际是在连当前配置环境下java user dir下那个目录的数据库。


看到这里可能有点糊涂了，这么就会出问题了那？
实际上derby的访问更像是一种使用derby driver对本地文件系统的访问，不管
启动不启动网络服务器，都可以用driver访问本地的数据库。这样，在ij里边像
第二种方式那样建立连接是完全可以的。启动了网络服务器，只不过是能够让其他
主机访问罢了。

另外一个问题是，在eclipse中和在系统中连接服务器，在connect的时候这个当
前配置环境是不一样的，eclipse默认工程所在路径是数据库的所在路径，而在系
统中“装”derby则会认为 c:\document and settings下边那个用户目录是数据
库的所在路径。

##Compiler API 
JDK6 的Compiler API(JSR 199)去动态编译Java源文件，Compiler API结合
反射功能就可以实现动态的产生Java代码并编译执行这些代码，有点动态语言的
特征。这个特性对于某些需要用到动态编译的应用程序相当有用， 比如
JSP Web Server，当我们手动修改JSP后，是不希望需要重启Web Server才可以
看到效果的，这时候我们就可以用Compiler API来实现动态编译JSP文件，当然
，现在的JSP Web Server也是支持JSP热部署的，现在的JSP Web Server通过
在运行期间通过Runtime.exec或ProcessBuilder来调用javac来编译代码，这
种方式需要我们产生另一个进程去做编译工作，不够优雅而且容易使代码依赖与特
定的操作系统；Compiler API通过一套易用的标准的API提供了更加丰富的方式
去做动态编译,而且是跨平台的。 

##插入式注解处理API(Pluggable Annotation Processing API)
JSR （JSR是Java Specification Requests的缩写，意思是Java 规范请求）
用Annotation Processor在编译期间而不是运行期间处理Annotation, 
Annotation Processor相当于编译器的一个插件,所以称为插入式注解处理。

##用Console开发控制台程序
JDK6中提供了java.io.Console 类专用来访问基于字符的控制台设备. 你的程序
如果要与Windows下的cmd或者Linux下的Terminal交互,就可以用Console类代劳。


#jdk1.7新特性：
##1. switch中可以使用字串

    String s = "test";
    switch (s) {
        case "test" :
        System.out.println("test");
    case "test1" :
        System.out.println("test1");
        break ;
    default :
        System.out.println("break");
        break ;
    }

##2. 运用List tempList = new ArrayList<>(); 即泛型实例化类型自动推断
##3. 语法上支持集合，而不一定是数组
    final List piDigits = [ 1,2,3,4,5,8 ];
##4. 新增一些取环境信息的工具方法
    File System.getJavaIoTempDir() // IO临时文件夹
    File System.getJavaHomeDir() // JRE的安装目录
    File System.getUserHomeDir() // 当前用户目录
    File System.getUserDir() // 启动java进程时所在的目录5
###5. Boolean类型反转，空指针安全,参与位运算
    Boolean Booleans.negate(Boolean booleanObj)
    True => False , False => True, Null => Null
    boolean Booleans.and(boolean[] array)
    boolean Booleans.or(boolean[] array)
    boolean Booleans.xor(boolean[] array)
    boolean Booleans.and(Boolean[] array)
    boolean Booleans.or(Boolean[] array)
    boolean Booleans.xor(Boolean[] array)
###6. 两个char间的equals
    boolean Character.equalsIgnoreCase(char ch1, char ch2)
###7. 安全的加减乘除
    int Math.safeToInt(long value)
    int Math.safeNegate(int value)
    long Math.safeSubtract(long value1, int value2)
    long Math.safeSubtract(long value1, long value2)
    int Math.safeMultiply(int value1, int value2)
    long Math.safeMultiply(long value1, int value2)
    long Math.safeMultiply(long value1, long value2)
    long Math.safeNegate(long value)
    int Math.safeAdd(int value1, int value2)
    long Math.safeAdd(long value1, int value2)
    long Math.safeAdd(long value1, long value2)
    int Math.safeSubtract(int value1, int value2)
###8. map集合支持并发请求，且可以写成 Map map = {name:"xxx",age:18};


###9. 新的整数字面表达方式 - "0b"前缀和"_"连数符
* 表示二进制字面值的前缀0b。

比如以下三个变量的值相同：

    byte b1 = 0b00100001;     // New
    byte b2 = 0x21;        // Old
    byte b3 = 33;        // Old

* 字面常量数字的下划线。用下划线连接整数提升其可读性，自身无含义，
不可用在数字的起始和末尾。

Java编码语言对给数值型的字面值加下划线有严格的规定。如上所述，你只能在
数字之间用下划线。你不能用把一个数字用下划线开头，或者已下划线结尾。这
里有一些其它的不能在数值型字面值上用下划线的地方：

在数字的开始或结尾
对浮点型数字的小数点附件
F或L下标的前面
该数值型字面值是字符串类型的时候  

    float pi1 = 3_.1415F; // 无效的; 不能在小数点之前有下划线
    float pi2 = 3._1415F; // 无效的; 不能在小数点之后有下划线
    long socialSecurityNumber1=999_99_9999_L;//无效的，不能在L下标之前加下划线
    int a1 = _52; // 这是一个下划线开头的标识符，不是个数字
    int a2 = 5_2; // 有效
    int a3 = 52_; // 无效的，不能以下划线结尾
    int a4 = 5_______2; // 有效的
    int a5 = 0_x52; // 无效，不能在0x之间有下划线
    int a6 = 0x_52; // 无效的，不能在数字开头有下划线
    int a7 = 0x5_2; // 有效的 (16进制数字)
    int a8 = 0x52_; // 无效的，不能以下划线结尾
    int a9 = 0_52; // 有效的（8进制数）
    int a10 = 05_2; // 有效的（8进制数）
    int a11 = 052_; // 无效的，不能以下划线结尾

###10. 在单个catch代码块中捕获多个异常，以及用升级版的类型检查重新抛出异常

在Java 7中，我们可以用一个catch块捕获所有这些异常：

    catch(IOException | SQLException | Exception ex){
        logger.error(ex);
        throw new MyException(ex.getMessage());
    }

如果用一个catch块处理多个异常，可以用管道符（|）将它们分开，在这种情况下
异常参数变量（ex）是定义为final的，所以不能被修改。这一特性将生成更少的
字节码并减少代码冗余。


另一个升级是编译器对重新抛出异常（rethrown exceptions）的处理。这一特
性允许在一个方法声明的throws从句中指定更多特定的异常类型。与以前版本相比
，Java SE 7 的编译器能够对再次抛出的异常(rethrown exception)做出更精
确的分析。这使得你可以在一个方法声明的throws从句中指定更具体的异常类型。

这个例子中的try语句块可能会抛出FirstException或者SecondException类
型的异常。设想一下，你想在rethrowException方法声明的throws从句中指定
这些异常类型。在Java SE 7之前的版本，你无法做到。

不过，在Java SE 7中，你可以在rethrowException方法声明的throws从句中
指定抛出的异常类型为FirstException和SecondException。Java SE 7的编
译器能够判定这个被throw语句抛出的异常参数e肯定是来自于try子句，而try
子句只会抛出FirstException或SecondException类型的异常。尽管catch子
句的异常参数e是java.lang.Exception类型，但是编译器可以判断出它是
FirstException或SecondException类型的一个实例：

    public class Java7MultipleExceptions {
    
        public static void main(String[] args) {
            try{
                rethrow("abc");
            }catch(FirstException | SecondException | ThirdException e){
                //以下赋值将会在编译期抛出异常，因为e是final型的
                //e = new Exception();
                System.out.println(e.getMessage());
            }
        }
    
        static void rethrow(String s) throws FirstException, SecondException, ThirdException {
            try {
                if (s.equals("First"))
                    throw new FirstException("First");
                else if (s.equals("Second"))
                    throw new SecondException("Second");
                else
                    throw new ThirdException("Third");
            } catch (Exception e) {
                //下面的赋值没有启用重新抛出异常的类型检查功能，这是Java 7的新特性
                // e=new ThirdException();
                throw e;
            }
        }
    
        static class FirstException extends Exception {
    
            public FirstException(String msg) {
                super(msg);
            }
        }
    
        static class SecondException extends Exception {
    
            public SecondException(String msg) {
                super(msg);
            }
        }
    
        static class ThirdException extends Exception {
    
            public ThirdException(String msg) {
                super(msg);
            }
        }
    
    }
    
不过，如果catch捕获的异常变量在catch子句中被重新赋值，那么异常类型检
查的分析将不会启用，因此在这种情况下，你不得不在方法声明的throws从句中
指定异常类型为java.lang.Exception。

更具体地说，从Java SE 7开始，当你在单个catch子句中声明一种或多种类型
的异常，并且重新抛出这些被捕获的异常时，需符合下列条件，编译器才会对再
次抛出的异常进行类型验证：

    try子句会抛出该异常。
    在此之前，没有其他的catch子句捕获该异常。
    该异常类型是catch子句捕获的多个异常中的一个异常类型的父类或子类。

###11. try-with-resources语句
try-with-resources语句是一个声明一个或多个资源的try语句。一个资源
作为一个对象，必须在程序结束之后关闭。try-with-resources语句确保在
语句的最后每个资源都被关闭，任何实现了Java.lang.AutoCloseable和
java.io.Closeable的对象都可以使用try-with-resource来实现异常处理
和关闭资源。

1.7之前的代码：

    public class PreJDK7 {  
    
        public static String readFirstLingFromFile(String path) throws IOException {  
            BufferedReader br = null;  
    
            try {  
                br = new BufferedReader(new FileReader(path));  
                return br.readLine();  
            } catch (IOException e) {  
                e.printStackTrace();  
            } finally {//必须在这里关闭资源  
                if (br != null)  
                    br.close();  
            }  
            return null;  
        }  
    }

1.7及以后的代码：

    public class AboveJDK7 {  
    
        static String readFirstLineFromFile(String path) throws IOException {  
    
            try (BufferedReader br = new BufferedReader(new FileReader(path))) {  
                return br.readLine();  
            }  
        }  
    }
    
try-with-resource可以声明多个资源(声明语句之间分号分割，最后一个可忽
略分号)。下面的例子是在一个ZIP文件中检索文件名并将检索后的文件存入一个
txt文件中。
    
    public class AboveJDK7_2 {  
        public static void writeToFileZipFileContents(String zipFileName,String outputFileName) throws java.io.IOException {  
            java.nio.charset.Charset charset = java.nio.charset.Charset.forName("US-ASCII");  
            java.nio.file.Path outputFilePath = java.nio.file.Paths.get(outputFileName);  
            //打开zip文件，创建输出流  
            try (  
                    java.util.zip.ZipFile zf = new java.util.zip.ZipFile(zipFileName);  
                    java.io.BufferedWriter writer = java.nio.file.Files.newBufferedWriter(outputFilePath, charset)  
                )
                {//遍历文件写入txt  
                    for (java.util.Enumeration entries = zf.entries(); entries.hasMoreElements();) {  
                            String newLine = System.getProperty("line.separator");  
                            String zipEntryName = ((java.util.zip.ZipEntry) entries.nextElement()).getName() + newLine;  
                            writer.write(zipEntryName, 0, zipEntryName.length());  
                    }  
                }  
        }  
    }

##Fork/Join框架
<http://blog.csdn.net/claram/article/details/52233181>

Fork/Join框架是一个比较特殊的线程池框架，专用于需要将一个任务不断分解成
多个子任务（分支），并将多个子任务的结果不断进行汇总得到最终结果（聚合）
的并行计算框架。

#java1.8新特性
##Java的并行API演变历程：
<http://blog.163.com/liuyong_xiaxia/blog/static/174355255201581561821988/>
1. 1.0-1.4版本中的java.lang.Thread。
2. 1.5版本中的java.util.concurrent。
3. 1.6版本中的Phasers等。
4. 1.7版本中的Fork/Join框架。
5. 1.8版本中的Lambda。

###Phasers

<http://blog.csdn.net/u010739551/article/details/51083004>

java多线程技术提供了Phaser工具类，Phaser表示“阶段器”，用来解决控制
多个线程分阶段共同完成任务的情景问题。其作用相比CountDownLatch和
CyclicBarrier更加灵活，例如有这样的一个题目：5个学生一起参加考试，
一共有三道题，要求所有学生到齐才能开始考试，全部同学都做完第一题，学
生才能继续做第二题，全部学生做完了第二题，才能做第三题，所有学生都做
完的第三题，考试才结束。分析这个题目：这是一个多线程（5个学生）分阶
段问题（考试考试、第一题做完、第二题做完、第三题做完），所以很适合用
Phaser解决这个问题。

###Fork/Join框架
<https://www.cnblogs.com/senlinyang/p/7885964.html>

Fork/Join框架是Java 7提供的一个用于并行执行任务的框架，是一个把大任
务分割成若干个小任务，最终汇总每个小任务结果后得到大任务结果的框架。
Fork/Join框架要完成两件事情：

1. 任务分割：首先Fork/Join框架需要把大的任务分割成足够小的子任务，如
果子任务比较大的话还要对子任务进行继续分割
2. 执行任务并合并结果：分割的子任务分别放到双端队列里，然后几个启动线
程分别从双端队列里获取任务执行。子任务执行完的结果都放在另外一个队列里
，启动一个线程从队列里取数据，然后合并这些数据。

在Java的Fork/Join框架中，使用两个类完成上述操作:

1. ForkJoinTask:我们要使用Fork/Join框架，首先需要创建一个ForkJoin
任务。该类提供了在任务中执行fork和join的机制。通常情况下我们不需要直
接集成ForkJoinTask类，只需要继承它的子类，Fork/Join框架提供了两个子类：

* RecursiveAction：用于没有返回结果的任务
* RecursiveTask:用于有返回结果的任务

2. ForkJoinPool:ForkJoinTask需要通过ForkJoinPool来执行

任务分割出的子任务会添加到当前工作线程所维护的双端队列中，进入队列的头
部。当一个工作线程的队列里暂时没有任务时，它会随机从其他工作线程的队列
的尾部获取一个任务(工作窃取算法)。

Fork/Join框架的实现原理

ForkJoinPool由ForkJoinTask数组和ForkJoinWorkerThread数组组成，
ForkJoinTask数组负责将存放程序提交给ForkJoinPool，而
ForkJoinWorkerThread负责执行这些任务。

ForkJoinTask与一般任务的主要区别在于它需要实现compute方法，在这个方
法里，首先需要判断任务是否足够小，如果足够小就直接执行任务。如果不足
够小，就必须分割成两个子任务，每个子任务在调用fork方法时，又会进入
compute方法，看看当前子任务是否需要继续分割成子任务，如果不需要继续分
割，则执行当前子任务并返回结果。使用join方法会等待子任务执行完并得到
其结果。

##允许在接口中有默认方法实现
在接口中新增了default方法和static方法，这两种方法可以有方法体 
这个特性又被称为扩展方法。下面是我们的第一个例子：

    public interface DefalutTest {
        static int a =5;
        default void defaultMethod(){
            System.out.println("DefalutTest defalut 方法");
        }
    
        int sub(int a,int b);
    
        static void staticMethod() {
            System.out.println("DefalutTest static 方法");
        }
    }
    
接口里的静态方法，即static修饰的有方法体的方法不会被继承或者实现，
但是静态变量会被继承，接口中的static方法不能被它的实现类直接使用，
但实现类可以使用default方法。需要使用static方法时可以使用
"接口类"."静态方法"的形式进行调用

default方法可以被子接口继承亦可被其实现类所调用

在传统版本上，接口中的所有方法必须是非静态的，且是abstract的，且是
public的。普通方法可以不写修饰符，也会默认为public和abstract，
当然你可以写上了。

但在java版本1.8中，这就不大一样了。
你可以为方法添加默认方法，这时候实现类不继承该方法也是可以编译通过的。
我们重点说下面的：
你还可以为接口添加静态方法。从技术角度来说，这是完全合法的。只是它看
起来违反了接口作为一个抽象定义的理念。

虚方法出现在Java的多态特性中，

父类与子类之间的多态性，对父类的函数进行重新定义。如果在子类中定义某
方法与其父类有相同的名称和参数，我们说该方法被重写 (Overriding)。
在Java中，子类可继承父类中的方法，而不需要重新编写相同的方法。但有时
子类并不想原封不动地继承父类的方法，而是想作一定的修改，这就需要采用
方法的重写。方法重写又称方法覆盖。

当设计类时，被重写的方法的行为怎样影响多态性。方法的重写使得子类能够
重写父类的方法。

当子类对象调用重写的方法时，调用的是子类的方法，而不是父类中被重写的方法。

Java虚方法你可以理解为java里所有被overriding的方法都是virtual的,所
有重写的方法都是override的。

抽象方法

抽象方法是用abstract修饰的方法，只能声明不能实现，抽象方法必须被声
明在抽象类里（反过来，抽象类里不一定要有抽象方法），抽象方法的的作用
就是强制子类实现该抽象方法（如果子类不是抽象类的话）。

在JVM字节码执行引擎中，方法调用会使用invokevirtual字节码指令来调用
所有的虚方法。

抽象类和接口的区别

1、抽象类可以有方法体，接口必须是方法声明。
2、可以把接口看成是更纯粹的抽象类。
3、他们都不可以被实例化，但可以完好的使用多态（好吧，这是相同点）。

##Lambda表达式
Lambda表达式可以看成是匿名内部类，使用Lambda表达式时，
接口必须是函数式接口

基本语法：

    <函数式接口>  <变量名> = (参数1，参数2...) -> {
        //方法体
    }

例子：

    public interface LambdaTest {
    
        abstract void print();
    }
    
    public interface LambdaTest2 {
    
        abstract void print(String a);
    }
    
    public interface DefalutTest {
    
        static int a =5;
        default void defaultMethod(){
            System.out.println("DefalutTest defalut 方法");
        }
    
        int sub(int a,int b);
    
        static void staticMethod() {
            System.out.println("DefalutTest static 方法");
        }
    }
    
    public class Main {
    
        public static void main(String[] args) {
            //匿名内部类--java8之前的实现方式
            DefalutTest dt = new DefalutTest(){
                @Override
                public int sub(int a, int b) {
                    // TODO Auto-generated method stub
                    return a-b;
                }
            };
    
            //lambda表达式--实现方式1
            DefalutTest dt2 =(a,b)->{
                return a-b;
            };
            System.out.println(dt2.sub(2, 1));
    
            //lambda表达式--实现方式2，省略花括号
            DefalutTest dt3 =(a,b)->a-b;
            System.out.println(dt3.sub(5, 6));
    
            //测试final，Lambda表达式内部不能修改非局部变量
            //int c = 5;
            //DefalutTest dt4 =(a,b)->a-c;
            //System.out.println(dt4.sub(5, 6));
    
            //无参方法，并且执行语句只有1条
            LambdaTest lt = ()-> System.out.println("测试无参");
            lt.print();
            //只有一个参数方法
            LambdaTest2 lt1 = s-> System.out.println(s);
            lt1.print("有一个参数");
        }
    }

###Lambda表达式其他特性：

####引用实例方法：
语法：

    <函数式接口>  <变量名> = <实例>::<实例方法名>
    //调用
    <变量名>.接口方法([实际参数...])

例子：

    public class Main {
    
        public static void main(String[] args) {
    
            LambdaTest2 lt1 = s-> System.out.println(s);
            lt1.print("有一个参数");
    
            //改写为：
            LambdaTest2 lt2 = System.out::println;
            lt2.print("实例引用方式调用");
        }
    }

###引用类方法
语法：

    <函数式接口>  <变量名> = <类>::<类方法名称>
    //调用
    <变量名>.接口方法([实际参数...])

例子：

    public interface LambdaTest3 {
    
        abstract void sort(int[] args);
    }
    
    public class Main {
    
        public static void main(String[] args) {
            List<Integer>  list = new ArrayList<Integer>();
            list.add(50);
            list.add(18);
            list.add(6);
            list.add(99);
            list.add(32);
            System.out.println(list.toString()+"排序之前");
            LambdaTest3 lt3 = Collections::sort;
            lt3.sort(list, (a,b) -> {
                return a-b;
            });
            System.out.println(list.toString()+"排序之后");
        }
    }

###引用类的实例方法
    //定义接口
    interface <函数式接口>{
        <返回值> <方法名>(<类><类名称>,[其他参数...]); 
    }
    <函数式接口>  <变量名> = <类>::<类实例方法名>
    //调用
    <变量名>.接口方法(类的实例,[实际参数...])
    

    public class LambdaClassTest {
    
        public int add(int a, int b){
            System.out.println("LambdaClassTest类的add方法");
            return a+b;
        }
    }
    
    public interface LambdaTest4 {
    
        abstract int add(LambdaClassTest lt,int a,int b);
    }
    
    public class Main {
    
        public static void main(String[] args) {
            LambdaTest4 lt4 = LambdaClassTest::add;
            LambdaClassTest lct = new LambdaClassTest();
            System.out.println(lt4.add(lct, 5, 8));
        }
    }

###引用构造器方法

    <函数式接口>  <变量名> = <类>::<new>
    //调用
    <变量名>.接口方法([实际参数...])
    
    
    public interface LambdaTest5 {
    
        abstract String creatString(char[] c);
    }
    public class Main {
    
        public static void main(String[] args) {
            LambdaTest5 lt5 = String::new;
            System.out.println(lt5.creatString(new char[]{'1','2','3','a'}));
        }
    }

###Lambda表达式作用域
在lambda表达式中访问外层作用域和老版本的匿名对象中的方式很相似。
你可以直接访问标记了final的外层局部变量，或者实例的字段以及静态变量。
我们可以直接在lambda表达式中访问外层的局部变量，但是该局部变量必须是final
的，即使没有加final关键字，之后我们无论在哪（lambda表达式内部或外部）修改
该变量，均报错。

    final int num = 1; //方法体内局部变量
    Converter stringConverter =
            (from) -> String.valueOf(from + num);
    stringConverter.convert(2);     // 3

但是与匿名对象不同的是，变量num并不需要一定是final。下面的代码依然是合法的：

    int num = 1; //方法体内局部变量
    Converter stringConverter =
            (from) -> String.valueOf(from + num);
    stringConverter.convert(2);     // 3


然而，num在编译的时候被隐式地当做final变量来处理。下面的代码就不合法：

    int num = 1; //方法体内局部变量
    Converter stringConverter =
            (from) -> String.valueOf(from + num);
    num = 3;

在lambda表达式内部企图改变num的值也是不允许的。

访问成员变量和静态变量

与局部变量不同，我们在lambda表达式的内部能获取到对成员变量或静态变量的
读写权。这种访问行为在匿名对象里是非常典型的。

    class Lambda4 {
        static int outerStaticNum;
        int outerNum;
    
        void testScopes() {
            Converter stringConverter1 = (from) -> {
                outerNum = 23;
                return String.valueOf(from);
            };
    
            Converter stringConverter2 = (from) -> {
                outerStaticNum = 72;
                return String.valueOf(from);
            };
        }
    }

##函数式接口
如果一个接口只有一个抽象方法，则该接口称之为函数式接口，因为 默认方法
不算抽象方法，所以你也可以给你的函数式接口添加默认方法。函数式接口可以
使用Lambda表达式，lambda表达式会被匹配到这个抽象方法上，我们可以将
lambda表达式当作任意只包含一个抽象方法的接口类型，确保你的接口一定达
到这个要求，你只需要给你的接口添加 @FunctionalInterface 注解，编译
器如果发现你标注了这个注解的接口有多于一个抽象方法的时候会报错的

举例：

    @FunctionalInterface
    interface Converter {
        T convert(F from);
    }
    
    Converter converter = (from) -> Integer.valueOf(from);
    Integer converted = converter.convert("123");
    System.out.println(converted);    // 123

注意，如果你不写@FunctionalInterface 标注，程序也是正确的。

###内置函数式接口
JDK 1.8 API中包含了很多内置的函数式接口。有些是在以前版本的Java中大家
耳熟能详的，例如Comparator接口，或者Runnable接口。对这些现成的接口进
行实现，可以通过@FunctionalInterface 标注来启用Lambda功能支持。

此外，Java 8 API 还提供了很多新的函数式接口，来降低程序员的工作负担。
有些新的接口已经在Google Guava库中很有名了。如果你对这些库很熟的话，
你甚至闭上眼睛都能够想到，这些接口在类库的实现过程中起了多么大的作用。

####Predicates
Predicate是一个布尔类型的函数，该函数只有一个输入参数。Predicate接口
包含了多种默认方法，用于处理复杂的逻辑动词（and, or，negate）：

    Predicate predicate = (s) -> s.length() > 0;
    
    predicate.test("foo");              // true
    predicate.negate().test("foo");     // false
    
    Predicate nonNull = Objects::nonNull;
    Predicate isNull = Objects::isNull;
    
    Predicate isEmpty = String::isEmpty;
    Predicate isNotEmpty = isEmpty.negate();

Functions


Function接口接收一个参数，并返回单一的结果。默认方法可以将多个函数串在一起（compse, andThen）：


Java代码
Function toInteger = Integer::valueOf;
Function backToString = toInteger.andThen(String::valueOf);

backToString.apply("123");     // "123"


Suppliers


Supplier接口产生一个给定类型的结果。与Function不同的是，Supplier没有输入参数。


Java代码
Supplier personSupplier = Person::new;
personSupplier.get();   // new Person


Consumers


Consumer代表了在一个输入参数上需要进行的操作。


Java代码
Consumer greeter = (p) -> System.out.println("Hello, " + p.firstName);
greeter.accept(new Person("Luke", "Skywalker"));


Comparators


Comparator接口在早期的Java版本中非常著名。Java 8 为这个接口添加了不同的默认方法。


Java代码
Comparator comparator = (p1, p2) -> p1.firstName.compareTo(p2.firstName);

Person p1 = new Person("John", "Doe");
Person p2 = new Person("Alice", "Wonderland");

comparator.compare(p1, p2);             // > 0
comparator.reversed().compare(p1, p2);  // < 0


Optionals


Optional不是一个函数式接口，而是一个精巧的工具接口，用来防止NullPointerEception产生。这个概念在下一节会显得很重要，所以我们在这里快速地浏览一下Optional的工作原理。


Optional是一个简单的值容器，这个值可以是null，也可以是non-null。考虑到一个方法可能会返回一个non-null的值，也可能返回一个空值。为了不直接返回null，我们在Java 8中就返回一个Optional。


Java代码
Optional optional = Optional.of("bam");

optional.isPresent();           // true
optional.get();                 // "bam"
optional.orElse("fallback");    // "bam"

optional.ifPresent((s) -> System.out.println(s.charAt(0)));     // "b"






Streams Top
java.util.Stream表示了某一种元素的序列，在这些元素上可以进行各种操作。Stream操作可以是中间操作，也可以是完结操作。完结操作 会返回一个某种类型的值，而中间操作会返回流对象本身，并且你可以通过多次调用同一个流操作方法来将操作结果串起来（就像StringBuffer的 append方法一样————译者注）。Stream是在一个源的基础上创建出来的，例如java.util.Collection中的list或者 set（map不能作为Stream的源）。Stream操作往往可以通过顺序或者并行两种方式来执行。


我们先了解一下序列流。首先，我们通过string类型的list的形式创建示例数据：


Java代码
List stringCollection = new ArrayList<>();
stringCollection.add("ddd2");
stringCollection.add("aaa2");
stringCollection.add("bbb1");
stringCollection.add("aaa1");
stringCollection.add("bbb3");
stringCollection.add("ccc");
stringCollection.add("bbb2");
stringCollection.add("ddd1");


Java 8中的Collections类的功能已经有所增强，你可以之直接通过调用Collections.stream()或者Collection.parallelStream()方法来创建一个流对象。下面的章节会解释这个最常用的操作。


Filter


Filter接受一个predicate接口类型的变量，并将所有流对象中的元素进行过滤。该操作是一个中间操作，因此它允许我们在返回结果的基 础上再进行其他的流操作（forEach）。ForEach接受一个function接口类型的变量，用来执行对每一个元素的操作。ForEach是一个 中止操作。它不返回流，所以我们不能再调用其他的流操作。


Java代码
stringCollection
    .stream()
    .filter((s) -> s.startsWith("a"))
    .forEach(System.out::println);

// "aaa2", "aaa1"


Sorted


Sorted是一个中间操作，能够返回一个排过序的流对象的视图。流对象中的元素会默认按照自然顺序进行排序，除非你自己指定一个Comparator接口来改变排序规则。


Java代码
stringCollection
    .stream()
    .sorted()
    .filter((s) -> s.startsWith("a"))
    .forEach(System.out::println);

// "aaa1", "aaa2"


一定要记住，sorted只是创建一个流对象排序的视图，而不会改变原来集合中元素的顺序。原来string集合中的元素顺序是没有改变的。


Java代码
System.out.println(stringCollection);
// ddd2, aaa2, bbb1, aaa1, bbb3, ccc, bbb2, ddd1


Map


map是一个对于流对象的中间操作，通过给定的方法，它能够把流对象中的每一个元素对应到另外一个对象上。下面的例子就演示了如何把每个 string都转换成大写的string. 不但如此，你还可以把每一种对象映射成为其他类型。对于带泛型结果的流对象，具体的类型还要由传递给map的泛型方法来决定。


Java代码
stringCollection
    .stream()
    .map(String::toUpperCase)
    .sorted((a, b) -> b.compareTo(a))
    .forEach(System.out::println);

// "DDD2", "DDD1", "CCC", "BBB3", "BBB2", "AAA2", "AAA1"


Match


匹配操作有多种不同的类型，都是用来判断某一种规则是否与流对象相互吻合的。所有的匹配操作都是终结操作，只返回一个boolean类型的结果。


Java代码
boolean anyStartsWithA =
    stringCollection
        .stream()
        .anyMatch((s) -> s.startsWith("a"));

System.out.println(anyStartsWithA);      // true

boolean allStartsWithA =
    stringCollection
        .stream()
        .allMatch((s) -> s.startsWith("a"));

System.out.println(allStartsWithA);      // false

boolean noneStartsWithZ =
    stringCollection
        .stream()
        .noneMatch((s) -> s.startsWith("z"));

System.out.println(noneStartsWithZ);      // true


Count


Count是一个终结操作，它的作用是返回一个数值，用来标识当前流对象中包含的元素数量。


Java代码
long startsWithB =
    stringCollection
        .stream()
        .filter((s) -> s.startsWith("b"))
        .count();

System.out.println(startsWithB);    // 3


Reduce


该操作是一个终结操作，它能够通过某一个方法，对元素进行削减操作。该操作的结果会放在一个Optional变量里返回。


Java代码
Optional reduced =
    stringCollection
        .stream()
        .sorted()
        .reduce((s1, s2) -> s1 + "#" + s2);

reduced.ifPresent(System.out::println);
// "aaa1#aaa2#bbb1#bbb2#bbb3#ccc#ddd1#ddd2"






Parallel Streams Top
像上面所说的，流操作可以是顺序的，也可以是并行的。顺序操作通过单线程执行，而并行操作则通过多线程执行。


下面的例子就演示了如何使用并行流进行操作来提高运行效率，代码非常简单。


首先我们创建一个大的list，里面的元素都是唯一的：


Java代码
int max = 1000000;
List values = new ArrayList<>(max);
for (int i = 0; i < max; i++) {
    UUID uuid = UUID.randomUUID();
    values.add(uuid.toString());
}


现在，我们测量一下对这个集合进行排序所使用的时间。


顺序排序


Java代码
long t0 = System.nanoTime();

long count = values.stream().sorted().count();
System.out.println(count);

long t1 = System.nanoTime();

long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
System.out.println(String.format("sequential sort took: %d ms", millis));

// sequential sort took: 899 ms


并行排序


Java代码
long t0 = System.nanoTime();

long count = values.parallelStream().sorted().count();
System.out.println(count);

long t1 = System.nanoTime();

long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
System.out.println(String.format("parallel sort took: %d ms", millis));

// parallel sort took: 472 ms


如你所见，所有的代码段几乎都相同，唯一的不同就是把stream()改成了parallelStream(), 结果并行排序快了50%。




Map Top
正如前面已经提到的那样，map是不支持流操作的。而更新后的map现在则支持多种实用的新方法，来完成常规的任务。


Java代码
Map map = new HashMap<>();

for (int i = 0; i < 10; i++) {
    map.putIfAbsent(i, "val" + i);
}

map.forEach((id, val) -> System.out.println(val));


上面的代码风格是完全自解释的：putIfAbsent避免我们将null写入；forEach接受一个消费者对象，从而将操作实施到每一个map中的值上。


下面的这个例子展示了如何使用函数来计算map的编码：


Java代码
map.computeIfPresent(3, (num, val) -> val + num);
map.get(3);             // val33

map.computeIfPresent(9, (num, val) -> null);
map.containsKey(9);     // false

map.computeIfAbsent(23, num -> "val" + num);
map.containsKey(23);    // true

map.computeIfAbsent(3, num -> "bam");
map.get(3);             // val33


接下来，我们将学习，当给定一个key值时，如何把一个实例从对应的key中移除：


Java代码
map.remove(3, "val3");
map.get(3);             // val33

map.remove(3, "val33");
map.get(3);             // null


另一个有用的方法：


Java代码
map.getOrDefault(42, "not found");  // not found


将map中的实例合并也是非常容易的：


Java代码
map.merge(9, "val9", (value, newValue) -> value.concat(newValue));
map.get(9);             // val9

map.merge(9, "concat", (value, newValue) -> value.concat(newValue));
map.get(9);             // val9concat


合并操作先看map中是否没有特定的key/value存在，如果是，则把key/value存入map，否则merging函数就会被调用，对现有的数值进行修改。




时间日期API Top
Java 8 包含了全新的时间日期API，这些功能都放在了java.time包下。新的时间日期API是基于Joda-Time库开发的，但是也不尽相同。下面的例子就涵盖了大多数新的API的重要部分。


Clock


Clock提供了对当前时间和日期的访问功能。Clock是对当前时区敏感的，并可用于替代 System.currentTimeMillis()方法来获取当前的毫秒时间。当前时间线上的时刻可以用Instance类来表示。Instance 也能够用于创建原先的java.util.Date对象。


Java代码
Clock clock = Clock.systemDefaultZone();
long millis = clock.millis();

Instant instant = clock.instant();
Date legacyDate = Date.from(instant);   // legacy java.util.Date


Timezones


时区类可以用一个ZoneId来表示。时区类的对象可以通过静态工厂方法方便地获取。时区类还定义了一个偏移量，用来在当前时刻或某时间与目标时区时间之间进行转换。


Java代码
System.out.println(ZoneId.getAvailableZoneIds());
// prints all available timezone ids

ZoneId zone1 = ZoneId.of("Europe/Berlin");
ZoneId zone2 = ZoneId.of("Brazil/East");
System.out.println(zone1.getRules());
System.out.println(zone2.getRules());

// ZoneRules[currentStandardOffset=+01:00]
// ZoneRules[currentStandardOffset=-03:00]


LocalTime


本地时间类表示一个没有指定时区的时间，例如，10 p.m.或者17：30:15，下面的例子会用上面的例子定义的时区创建两个本地时间对象。然后我们会比较两个时间，并计算它们之间的小时和分钟的不同。


Java代码
LocalTime now1 = LocalTime.now(zone1);
LocalTime now2 = LocalTime.now(zone2);

System.out.println(now1.isBefore(now2));  // false

long hoursBetween = ChronoUnit.HOURS.between(now1, now2);
long minutesBetween = ChronoUnit.MINUTES.between(now1, now2);

System.out.println(hoursBetween);       // -3
System.out.println(minutesBetween);     // -239


LocalTime是由多个工厂方法组成，其目的是为了简化对时间对象实例的创建和操作，包括对时间字符串进行解析的操作。


Java代码
LocalTime late = LocalTime.of(23, 59, 59);
System.out.println(late);       // 23:59:59

DateTimeFormatter germanFormatter =
    DateTimeFormatter
        .ofLocalizedTime(FormatStyle.SHORT)
        .withLocale(Locale.GERMAN);

LocalTime leetTime = LocalTime.parse("13:37", germanFormatter);
System.out.println(leetTime);   // 13:37


LocalDate


本地时间表示了一个独一无二的时间，例如：2014-03-11。这个时间是不可变的，与LocalTime是同源的。下面的例子演示了如何通过加减日，月，年等指标来计算新的日期。记住，每一次操作都会返回一个新的时间对象。


Java代码
LocalDate today = LocalDate.now();
LocalDate tomorrow = today.plus(1, ChronoUnit.DAYS);
LocalDate yesterday = tomorrow.minusDays(2);

LocalDate independenceDay = LocalDate.of(2014, Month.JULY, 4);
DayOfWeek dayOfWeek = independenceDay.getDayOfWeek();
System.out.println(dayOfWeek);    // FRIDAYParsing a LocalDate from a string is just as simple as parsing a LocalTime:


解析字符串并形成LocalDate对象，这个操作和解析LocalTime一样简单。


Java代码
DateTimeFormatter germanFormatter =
    DateTimeFormatter
        .ofLocalizedDate(FormatStyle.MEDIUM)
        .withLocale(Locale.GERMAN);

LocalDate xmas = LocalDate.parse("24.12.2014", germanFormatter);
System.out.println(xmas);   // 2014-12-24


LocalDateTime


LocalDateTime表示的是日期-时间。它将刚才介绍的日期对象和时间对象结合起来，形成了一个对象实例。LocalDateTime是不可变的，与LocalTime和LocalDate的工作原理相同。我们可以通过调用方法来获取日期时间对象中特定的数据域。


Java代码
LocalDateTime sylvester = LocalDateTime.of(2014, Month.DECEMBER, 31, 23, 59, 59);

DayOfWeek dayOfWeek = sylvester.getDayOfWeek();
System.out.println(dayOfWeek);      // WEDNESDAY

Month month = sylvester.getMonth();
System.out.println(month);          // DECEMBER

long minuteOfDay = sylvester.getLong(ChronoField.MINUTE_OF_DAY);
System.out.println(minuteOfDay);    // 1439


如果再加上的时区信息，LocalDateTime能够被转换成Instance实例。Instance能够被转换成以前的java.util.Date对象。


Java代码
Instant instant = sylvester
        .atZone(ZoneId.systemDefault())
        .toInstant();

Date legacyDate = Date.from(instant);
System.out.println(legacyDate);     // Wed Dec 31 23:59:59 CET 2014


格式化日期-时间对象就和格式化日期对象或者时间对象一样。除了使用预定义的格式以外，我们还可以创建自定义的格式化对象，然后匹配我们自定义的格式。


Java代码
DateTimeFormatter formatter =
    DateTimeFormatter
        .ofPattern("MMM dd, yyyy - HH:mm");

LocalDateTime parsed = LocalDateTime.parse("Nov 03, 2014 - 07:13", formatter);
String string = formatter.format(parsed);
System.out.println(string);     // Nov 03, 2014 - 07:13


不同于java.text.NumberFormat，新的DateTimeFormatter类是不可变的，也是线程安全的。


更多的细节，请看这里




Annotations Top
Java 8中的注解是可重复的。让我们直接深入看看例子，弄明白它是什么意思。


首先，我们定义一个包装注解，它包括了一个实际注解的数组


Java代码
@interface Hints {
    Hint[] value();
}

@Repeatable(Hints.class)
@interface Hint {
    String value();
}


只要在前面加上注解名：@Repeatable，Java 8 允许我们对同一类型使用多重注解：


变体1：使用注解容器（老方法）：


Java代码
@Hints({@Hint("hint1"), @Hint("hint2")})
class Person {}


变体2：使用可重复注解（新方法）：


Java代码
@Hint("hint1")
@Hint("hint2")
class Person {}


使用变体2，Java编译器能够在内部自动对@Hint进行设置。这对于通过反射来读取注解信息来说，是非常重要的。


Java代码
Hint hint = Person.class.getAnnotation(Hint.class);
System.out.println(hint);                   // null

Hints hints1 = Person.class.getAnnotation(Hints.class);
System.out.println(hints1.value().length);  // 2

Hint[] hints2 = Person.class.getAnnotationsByType(Hint.class);
System.out.println(hints2.length);          // 2


尽管我们绝对不会在Person类上声明@Hints注解，但是它的信息仍然可以通过getAnnotation(Hints.class)来读 取。并且，getAnnotationsByType方法会更方便，因为它赋予了所有@Hints注解标注的方法直接的访问权限。


Java代码
@Target({ElementType.TYPE_PARAMETER, ElementType.TYPE_USE})
@interface MyAnnotation {}






总结 Top
Java 8编程指南就到此告一段落。当然，还有很多内容需要进一步研究和说明。这就需要靠读者您来对JDK 8进行探究了，例如：Arrays.parallelSort, StampedLock和CompletableFuture等等 ———— 我这里只是举几个例子而已。


我希望这个博文能够对您有所帮助，也希望您阅读愉快。完整的教程源代码放在了GitHub上。您可以尽情地fork，并请通过Twitter告诉我您的反馈。


原文链接： winterbe 翻译： ImportNew.com - 黄小非
译文链接： http://www.importnew.com/10360.html
-------------------------------------------------------------------------------------------------------------------

#jav1.9新特性
[打破java1.9模块封装](https://www.jianshu.com/p/b1fd9b1acc22)

加快OpenJDK的开发速度：继2014年3月份发布了Java 8之后，我们进入下一个两年的发布周期。 Java 9预计在2016年发布，并且已经公布了JEP(JDK改进提议)中的前期列表。同时，我们已经把一些新特性整理到了JSR(Java规范请求)，还有提 出了一些希望包括在新版本中的其他特性。

这些重要的特性都包括在Jigsaw项目中。显著的性能改善和期待已久的API包括：进程API更新，JSON将成为java.util的一部分，货币处理API对于想处在技术最前沿的你，可从这里获得Java 9的初期版本。

被接受的特性
1. Jigsaw 项目;模块化源码
Jigsaw项目是为了模块化Java代码、将JRE分成可相互协作的组件，这也是Java 9 众多特色种的一个。JEP是迈向Jigsaw四步中的第一步，它不会改变JRE和JDK的真实结构。JEP是为了模块化JDK源代码，让编译系统能够模块 编译并在构建时检查模块边界。这个项目原本是随Java 8发布的，但由于推迟，所以将把它加到Java 9.

一旦它完成，它可能允许根据一个项目需求自定义组件从而减少rt.jar的大小。在JDK 7 和JDK 8的rt.jar包中有大约20,000个类，但有很多类在一些特定的环境里面并没有被用到(即使在Java 8的紧凑分布特性中已经包含了一部分解决方法也存在着类冗余)。这么做是为了能让Java能够容易应用到小型计算设备(比如网络设备)中，提高它的安全和 性能，同时也能让开发者更容易构建和维护这些类库。

2. 简化进程API
截止到目前，Java控制与管理系统进程的能力是有限的。举个例子，现在为了简便获取你程序的进程PID，你要么调用本地程序要么要自己使用一些变通方案。更多的是，每个（系统）平台需要有一个不同实现来确保你能获得正确的结果。
期望代码能获取Linux PIDS，现在是如下方式：





在Java 9中，可以变换成如下方式（支持所有的操作系统）：





这次更新将会扩展Java与操作系统的交互能力：新增一些新的直接明了的方法去处理PIDs，进程名字和状态以及枚举多个JVM和进程以及更多事情。

3. 轻量级 JSON API
目前有多种处理JSON的Java工具，但JSON API 独到之处在于JSON API将作为Java语言的一部分，轻量并且运用Java 8的新特性。它将放在java.util包里一起发布(但在JSR 353里面的JSON是用第三方包或者其他的方法处理的).

4. 钱和货币的API
在Java 8引进了日期和时间的API之后, Java 9引入了新的货币API, 用以表示货币, 支持币种之间的转换和各种复杂运算. 关于这个项目的具体情况, 请访问https://github.com/JavaMoney,里面已经给出了使用说明和示例, 以下是几个重要的例子:





更多关于 JSR 354的内容

5. 改善锁争用机制
锁争用是限制许多Java多线程应用性能的瓶颈. 新的机制在改善Java对象监视器的性能方面已经得到了多种基准(benchmark)的验证, 其中包括Volano. 测试中通讯服务器开放了海量的进程来连接客户端, 其中有很多连接都申请同一个资源, 以此模拟重负荷日常应用.

通过诸如此类的压力测试我们可以估算JVM的极限吞吐量(每秒的消息数量). JEP在22种不同的测试中都得到了出色的成绩, 新的机制如果能在Java 9中得到应用的话, 应用程序的性能将会大大提升.
关于JEP 143的更多内容

6. 代码分段缓存
Java 9的另一个性能提升来自于JIT(Just-in-time)编译器. 当某段代码被大量重复执行的时候, 虚拟机会把这段代码编译成机器码(native code)并储存在代码缓存里面, 进而通过访问缓存中不同分段的代码来提升编译器的效率.

和原来的单一缓存区域不同的是, 新的代码缓存根据代码自身的生命周期而分为三种:
永驻代码(JVM 内置 / 非方法代码)
短期代码(仅在某些条件下适用的配置性(profiled)代码)
长期代码(非配置性代码)
缓存分段会在各个方面提升程序的性能, 比如做垃圾回收扫描的时候可以直接跳过非方法代码(永驻代码), 从而提升效率.
更多关于JEP 197的内容

7. 智能Java编译, 第二阶段
智能Java编译工具sjavac的第一阶段开始于JEP 139这个项目, 用于在多核处理器上提升JDK的编译速度. 现在这个项目已经进入第二阶段(JEP 199), 目的是改进sjavac并让其成为取代目前JDK编译工具javac的Java默认的通用编译工具.

其他值得期待的内容:
8. HTTP 2.0客户端
HTTP 2.0标准虽然还没正式发布, 但是已经进入了最终审查阶段, 预计可以在Java 9发布之前审查完毕. JEP 110将会重新定义并实现一个全新的Java HTTP客户端, 用来取代现在的HttpURLConnection, 同时也会实现HTTP 2.0和网络接口(原文websockets). 它现在还没被JEP正式认可但我们希望在Java 9中包含这一项目的内容.
官方的HTTP 2.0 RFC(Request for Comments, 官方技术讨论/会议记录等等的一系列文档记录)预订于2015年2月发布, 它是基于Google发布的SPDY(Speedy, 快速的)协议. 基于SPDY协议的网络相对于基于HTTP 1.1协议的网络有11.81%到47.7%之间的显著提速, 现在已经有浏览器实现了这个协议.

9. Kulla计划: Java的REPL实现
这个取名为Kulla的项目最近宣布将于2015年4月整合测试, 虽然已经不太有希望能赶上Java 9的发布, 但如果进度快的话或许刚好能赶上. 现在Java并没有来自官方的REPL(Read-Eval-Print-Loop)方式, 也就是说现在如果你想要跑几行Java代码做一个快速的测试, 你仍然需要把这几行代码封装在项目或者方法里面. 虽然在一些流行的IDE里面有Java REPL工具, 但它们并没有官方支持, 而Kulla项目或许就能成为Java官方发布的REPL解决方案.
更多关于Kulla计划的内容

这些新功能出自何处？

JEP和JSR并不是无中生有，下面就介绍一下Java发展的生态环境：
小组 - 对特定技术内容, 比如安全、网络、Swing、HotSpot、有共同兴趣的组织和个人。

项目 - 编写代码, 文档以及其他工作，至少由一个小组赞助和支持，比如最近的Lambda计划，Jigsaw计划和Sumatra计划。

JDK改进提案(JEP) - 每当需要有新的尝试的时候, JEP可以在JCP(Java Community Process)之前或者同时提出非正式的规范(specification)，被正是认可的JEP正式写进JDK的发展路线图并分配版本号。

Java规范提案(JSR) - 新特性的规范出现在这一个阶段，可以来自于小组 / 项目、JEP、 JCP成员或者Java社区(community)成员的提案，每个Java版本都由相应的JSR支持, Java 9暂时还没有。
该博文转自：http://huyumin.iteye.com/blog/2154441 