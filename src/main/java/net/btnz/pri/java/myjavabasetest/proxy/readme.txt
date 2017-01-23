source: http://www.jianshu.com/p/6f6bb2f0ece9

动态代理：即在运行时动态的生成代理类。

委托类和委托对象：委托类是一个类，委托对象是委托类的实例。
代理类和代理对象：代理类是一个类，代理对象是代理类的实例。

Java实现动态代理的大致步骤如下：
1、定义一个委托类和公共接口；
2、自己定义一个类（调用处理器类，即实现InvocationHandler接口，
    这个类的目的是指定运行时将生成的代理类需要完成的具体任务
    （包括Preprocess和Postprocess），即代理类调用任何方法都会
    经过这个调用处理；
3、生成代理对象（当然也会生成代理类），需要为他指定（1）委托
对象（2）实现的一系列接口（3）调用处理器类的实例。因此可以看
出一个代理对象对应一个委托对象，对应一个调用处理器实例。

Java实现动态代理主要涉及以下几个类：
java.lang.reflect.Proxy: 这是生成代理类的主类，通过Proxy类生
成的代理类都继承了Proxy类，即 DynamicProxyClass extends Proxy。
java.lang.reflect.InvocationHandler: 这里称他为“调用处理类”
他是一个接口，我们动态生成的代理类需要完成的具体内容需要自已
定义一个类，而这个类必须实现InvocationHandler接口

Proxy类主要方法为：
static Object newProxyInstance(ClassLoader loader, Class<?>[]
 interfaces, InvocationHandler h)
这个静态函数的第一个参数是类加载器对象（即哪个类加载器来加载
这个代理类到JVM的方法区），第二个参数是接口（表明你这个代理类
需要实现哪些接口），第三个参数是调用处理器类实例（指定代理类
中具体要干什么）。这个函数是JDK为了程序员方便创建代理对象而封
装的一个函数，因此你调用newProxyInstance()时真接创建了代理对
象（略去了创建代理类的代码）。其实他主要完成了以下几个工作：
static Object newProxyInstance(ClassLoader loader, Class<?>[]
 interfaces, InvocationHandler handler) {
     //1. 根据类加载器和接口创建代理类
     Class clazz = Proxy.getProxyClass(loader, interfaces);
     //2. 获得代理类的带参数的构造函数
     Constructor constructor = clazz.getConstructor(new Class[]
          {InvocationHandler.class});
     //3. 创建代理对象，并制定调用处理器实例为参数传入
     Interface Proxy = (Interface)constructor.newInstance(new
         Object[] {handler});
 }

 Proxy类还有一些静态方法，比如：
 InvocationHander getInvocationHandler(Object proxy): 获取代理
 对象对应的调用处理器对象。
 Class getProxyClass(ClassLoader loader, Class[] interfaces):
根据类加载器和实现的接口获得代理类。

Proxy类中有一个映射表，映射关系为：(<ClassLoader>, (<Interfaces>,
<ProxyClass>))，可以看出一级key为类加载器，根据一级key获得二级映
射表，二级key为接口数组，因此可以看出：一个类加载器对象和一个接
口数组确定了一个代理类。

我们写一个简单的例子来阐述Java实现动态代理的整个过程：
public class DynamicProxyDemo01 {
    public static void main(String[] args) {
        RealSubject realSubject = new RealSubject();    //1.创建委托对象
        ProxyHandler handler = new ProxyHandler(realSubject);    //2.创建调用处理器对象
        Subject proxySubject = (Subject)Proxy.newProxyInstance(RealSubject.class.getClassLoader(),
                                                        RealSubject.class.getInterfaces(), handler);    //3.动态生成代理对象
        proxySubject.request();    //4.通过代理对象调用方法
    }
}

/**
 * 接口
 */
interface Subject{
    void request();
}

/**
 * 委托类
 */
class RealSubject implements Subject{
    public void request(){
        System.out.println("====RealSubject Request====");
    }
}
/**
 * 代理类的调用处理器
 */
class ProxyHandler implements InvocationHandler{
    private Subject subject;
    public ProxyHandler(Subject subject){
        this.subject = subject;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        System.out.println("====before====");//定义预处理的工作，当然你也可以根据 method 的不同进行不同的预处理工作
        Object result = method.invoke(subject, args);
        System.out.println("====after====");
        return result;
    }
}

InvocationHandler接口中有方法：
invoke(Object proxy, Method method, Object[] args)
这个函数是在代理对象调用任何一个方法时都会调用的，方法不同会导致第二个参数method
不同，第一个参数是代理对象（表示哪个代理对象调用了method方法），第二个参数是
Method对象（表示哪个方法被调用了），第三个参数是指定方法的参数。

动态生成的代理类具有几个特点：
1、继承Proxy类，并实现了在Proxy.newProxyInstance()中提供的接口数组。
2、public final。
3、命名方式为$ProxyN，其中N会慢慢增加，一开始是$Proxy1，接下来是$Proxy2...
4、有一个参数为InvocationHandler的构造函数。这个从Proxy.newProxyInstance()函数
内部的clazz.getConstructor(new Class[] {InvocationHandler.class})可以看出。

Java实现动态代理的缺点：因为Java的单继承特性（每个代理类都继承了Proxy类），只能
针对接口创建代理类，不能针对类创建代理类。
（不能发现，代理类的实现是有很多共性的（重复代码），动态代理的好处在于避免了这些
重复代码，只需要关注操作。）

Java动态代理的内部实现：
现在我们就会有一个问题：Java是怎么保证代理对象调用的任何方法都会调用InvocationHandler
的invoke()方法的？
这就涉及到动态代理的内部实现。假设有一个接口Subject，且里面有int request(int i)
方法，则生成的代理类大致如下：
public final class $Proxy1 extends Proxy implements Subject {
    private InvocationHandler h;
    private $Proxy1(){}
    public $Proxy1(InvocationHandler h){
        this.h = h;
    }
    public int request(int i) {
        //创建method对象
        Method method = Subject.class.getMethod("request", new Class[]{int.class});
        //调用了invoke方法
        return (Integer)h.invoke(this,method,new Object[]{new Integer(i)});
    }
}

通过上面的方法就成功调用了invoke()方法。

元编程
到最后，我还想介绍一下元编程，我是从《Mac Talk 人生元编程》中了解到元编程这个词的：
    元编程就是能够操作代码的代码
Java支持元编程因为反射、动态代理、内省等特性。