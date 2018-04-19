package net.btnz.pri.java.myjavabasetest.concurrent.cache;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zhangsongwei on 2016/12/23.
 */
public abstract class ObjectPool {
    private long expirationTime;
    //必须保证key不为null
    protected ConcurrentHashMap<Object, Long> locked, unlocked;

    abstract Object create();

    //验证Object o对象是否是需要的
    abstract boolean validate(Object o);

    //毁灭Object o对象实例
    abstract void expire(Object o);

    ObjectPool(long time) {
        expirationTime = time;
        locked = new ConcurrentHashMap();
        unlocked = new ConcurrentHashMap();
    }

    Object checkOut() {
        long now = System.currentTimeMillis();
        Object o = null;
        //若unlocked队列为空，则创建对象实例
        //如果unlocked队列不为空，则遍历
        for (Object obj : unlocked.keySet()) {
            //如果满足，将其外借
            if (null == o && validate(obj)) {
                unlocked.remove(obj);
                locked.put(obj, new Long(now));
                o = obj;
            }
            if (o == obj) {
                continue;
            }
            //如果当前对象超出“消亡期限”，则将其消灭
            if (now - unlocked.get(obj).longValue() > expirationTime) {
                unlocked.remove(obj);
                expire(obj);
                //将Object obj的引用赋值为null是为了触发垃圾收集器对其回收
                obj = null;
            }
        }
        //若编历完，还未找到可用的对象，则创建新的对象实例
        if (null == o) {
            o = create();
        }
        locked.put(o, new Long(now));
        return o;
    }

    void checkIn(Object o) {
        locked.remove(o);
        unlocked.put(o, new Long(System.currentTimeMillis()));
    }
}
