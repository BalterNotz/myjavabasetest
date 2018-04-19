package net.btnz.pri.java.myjavabasetest.keyword.volatilekey;

/**
 * Created by zhangsongwei on 2016/12/26.
 */
public class VolatileBean {
    private volatile String firstName;
    private volatile String lastName;
    private volatile int age;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
