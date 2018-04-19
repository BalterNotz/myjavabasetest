package net.btnz.pri.java.myjavabasetest.stream;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

public class StreamTest {

    public static void main(String... args) {
        String str = "name=\t\r\nenabled";
        List<String> list = new ArrayList();
        list.add(str);
        list.add("  #asdfsdf=jjjj");
        list.add(" \t jjj=kkkk#aaaaa");
        list.add("      #     ");

        Map<String, String> doNamesNeedSendMQ = new ConcurrentHashMap();

        list.stream()
                .map(line -> line.substring(0, line.indexOf("#") == -1 ? line.length() : line.indexOf("#")))
                .map(line -> StringUtils.replaceEach(line, new String[]{" ", "\t", "\r", "\n"}, new String[]{"", "", "", ""}))
                .filter(StringUtils::isNotBlank)
                .map(line -> line.split("="))
                .filter(arr -> arr.length > 1)
                .forEach(arr -> doNamesNeedSendMQ.put(arr[0], arr[1]));

        Set<String> innerSet = new ConcurrentSkipListSet<>();
        innerSet.addAll(doNamesNeedSendMQ.keySet());
        innerSet.add("def");

        System.out.println(innerSet);

        for (String innerStr : innerSet) {
            if(innerStr.equals("name")){
                innerSet.remove(innerStr);
            }
            if(innerStr.equals("def")){
                innerSet.remove(innerStr);
            }
        }
        System.out.println(innerSet);

        System.out.println(JSON.toJSONString(doNamesNeedSendMQ));
    }
}
