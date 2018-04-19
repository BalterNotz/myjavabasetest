package net.btnz.pri.java.myjavabasetest.concurrent.cache;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zhangsongwei on 2016/12/23.
 */
public abstract class ObjectPool2 extends Thread {
    private long expirationTime;
    protected ConcurrentHashMap<Object, Long> locked, unlocked;

    abstract Object create();

    //验证Object o对象是否是需要的
    abstract boolean validate(Object o);

    //毁灭Object o对象实例
    abstract void expire(Object o);

    ObjectPool2(long time) {
        expirationTime = time;
        locked = new ConcurrentHashMap();
        unlocked = new ConcurrentHashMap();
    }

    Object checkOut() {
        long now = System.currentTimeMillis();
        Object o = null;
        //如果unlocked队列不为空，则遍历
        for (Object obj : unlocked.keySet()) {
            //如果满足，将期处借
            if (!validate(obj)) {
                continue;
            }
            unlocked.remove(obj);
            locked.put(obj, new Long(now));
            return obj;
        }
        //若编历完，还未找到可用的对象，则创建新的对象实例
        o = create();
        locked.put(o, new Long(now));
        return o;
    }

    void checkIn(Object o) {
        locked.remove(o);
        unlocked.put(o, new Long(System.currentTimeMillis()));
    }


    void cleanUp() {
        Object o;
        long now = System.currentTimeMillis();
        for (Object obj : unlocked.keySet()) {
            if (now - unlocked.get(obj).longValue() > expirationTime) {
                unlocked.remove(obj);
                expire(obj);
                obj = null;
            }
        }
        System.gc();
    }

    public void run(){
        while (true){
            try {
                sleep(expirationTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cleanUp();
        }
    }
}
