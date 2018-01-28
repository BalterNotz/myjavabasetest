package net.btnz.pri.java.myjavabasetest.reference;

/**
 * Java里面的引用，分为StrongReference,WeekReference,SoftReference以及hantomReference
 这四种引用与GC有密切的关系。

 详细介绍：
 （一）强引用（默认存在）
 强引用，是在实际开发中最为普遍的引用。有时候你开发的时候，申请一个内存空间的时候，就已经是强引用了。例如：

 Object obj =new Object();  // 强引用

 在强引用中，如果不让该对象指向为空，垃圾回收器绝对不会回收它。除非当出现内存空间不足的时候。
 jvm抛出oom导致程序异常发生的时候，才会回收具有强引用的对象来解决内存空间不足问题。

 Object obj =new Object();  // 强引用
 obj = null;//这时候为垃圾回收器回收这个对象，至于什么时候回收，取决于垃圾回收器的算法

 （二）软引用（SoftReference ）
 软引用对象也比较好理解，它是一个比较特殊的存在，拥有强引用的属性，又更加安全。如果有一个对象具有软引用。在内存空间足够的情况下，除非内存空间接近临界值、jvm即将抛出oom的时候，垃圾回收器才会将该引用对象进行回收，避免了系统内存溢出的情况。（前提也是对象指向不为空）因此，SoftReference 引用对象非常适合实现内存敏感的缓存，例如加载图片的时候，bitmap缓存机制。

 String value = new String(“sy”);
 SoftReference sfRefer = new SoftReference (value );

 sfRefer .get();//可以获得引用对象值

 （三）弱引用（WeakReference）
 顾名思义，一个具有弱引用的对象，与软引用对比来说，前者的生命周期更短。当垃圾回收器扫描到弱引用的对象的时候，不管内存空间是否足够，都会直接被垃圾回收器回收。不过也不用特别担心，垃圾回收器是一个优先级比较低的现场，因此不一定很快可以发现弱引用的对象。
 另外，google官方是推荐Android开发者使用WeakReference，而不建议SoftReference 引用，Android环境下与纯Java有所略同。下面待会说明情况。

 String value = new String(“sy”);
 WeakReference weakRefer = new WeakReference(value );

 System.gc();

 weakRefer.get();//null

 作者：DevSiven
 链接：https://www.jianshu.com/p/b56731447179
 來源：简书
 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。


 */
public class Reference {

    /**
     * 强引用是Java的默认引用实现，它会引可能长时间存在JVM中，当没有任务对象指向它时GC执行后将被回收。
     */
    public void strongReference(){

    }
}
