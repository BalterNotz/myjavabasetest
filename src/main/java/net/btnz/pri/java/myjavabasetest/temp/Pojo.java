package net.btnz.pri.java.myjavabasetest.temp;

import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

public class Pojo {
    public Long i;
    public Long ii = new Long(0);
    public AtomicLong iii = new AtomicLong();
    public AtomicReference<String> strRef = new AtomicReference<>();
    public AtomicReference<Set<String>> setRef = new AtomicReference<>();
}
