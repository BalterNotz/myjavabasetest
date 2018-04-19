package net.btnz.pri.java.myjavabasetest.fastjson;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by zhangsongwei on 2017/1/4.
 */
public class ToJsonString {
    public static void main(String[] args){
        User user = new User();
        user.setName("abc");
        user.setPawd("def");
        String jsonStr = JSON.toJSONString(user);
        System.out.println(jsonStr);
    }
}

class User {
    private String name;
    private String pawd;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPawd() {
        return pawd;
    }

    public void setPawd(String pawd) {
        this.pawd = pawd;
    }

    public String toString(){
        return ToStringBuilder.reflectionToString(this);
    }
}