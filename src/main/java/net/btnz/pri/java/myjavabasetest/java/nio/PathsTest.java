package net.btnz.pri.java.myjavabasetest.java.nio;

import org.apache.commons.lang3.StringUtils;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.atomic.AtomicReference;

public class PathsTest {
    private static AtomicReference<Set<String>> doNamesNeedSendMQ = new AtomicReference<>();

    public static void main(String... args) {
        try {
            Set<String> innerSet = new ConcurrentSkipListSet();
            Files.readAllLines(Paths.get("do_names_need_send_mq.txt")).stream().map((line) -> {
                return line.substring(0, line.indexOf("#") == -1 ? line.length() : line.indexOf("#"));
            }).map((line) -> {
                return StringUtils.replaceEach(line, new String[]{" ", "\t", "\r", "\n"}, new String[]{"", "", "", ""});
            }).filter(StringUtils::isNotBlank).map((line) -> {
                return line.toUpperCase();
            }).forEach((doName) -> {
                innerSet.add(doName);
            });
            doNamesNeedSendMQ.getAndSet(innerSet);
            System.out.println(doNamesNeedSendMQ);
        } catch (Exception var1) {
            System.out.println(var1);
        }
    }

}
