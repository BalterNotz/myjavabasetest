package net.btnz.pri.java.myjavabasetest.keyword.volatilekey;

/**
 * Created by zhangsongwei on 2016/12/26.
 */

/**
 * 如果 theFlooble 引用不是 volatile 类型，doWork() 中的代码在解除对 theFlooble 的引用时，将会得到一个不完全构造的 Flooble。
 * 该模式的一个必要条件是：被发布的对象必须是线程安全的，或者是有效的不可变对象（有效不可变意味着对象的状态在发布之后永远不会被修改）。volatile 类型的引用可以确保对象的发布形式的可见性，但是如果对象的状态在发布后将发生更改，那么就需要额外的同步。
 */
public class OneTimeSafePublication {
    private BackgroundFloobleLoader floobleLoader;

    class Flooble {
    }

    class BackgroundFloobleLoader {
        public volatile Flooble theFlooble;

        public void initInBackground() {
            // do lots of stuff
            theFlooble = new Flooble(); // this is the only write to the Flooble
        }
    }

    class SomeOtherClass {
        public void doWork() {
            while (true) {
                // do some stuff...
                // use the Flooble, but only if it is ready
                if (floobleLoader.theFlooble != null) {
                    doSomething(floobleLoader.theFlooble);
                }
            }
        }

        public void doSomething(Flooble flooble) {

        }
    }
}
