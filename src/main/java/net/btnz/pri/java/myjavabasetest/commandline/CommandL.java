package net.btnz.pri.java.myjavabasetest.commandline;

//import com.alibaba.fastjson.JSONObject;
//import com.taobao.tair.ResultCode;
//import com.taobao.tair.impl.DefaultTairManager;
import org.apache.commons.cli.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandL {
    static List prodTair = Arrays.asList("tair-m1.pajkdc.com:5198", "tair-m2.pajkdc.com:5198");
    static List preTair = Arrays.asList("tair.pre.pajkdc.com:5198");
    static List testTair = Arrays.asList("tair-m1.test.pajkdc.com:5198", "tair-m2.test.pajkdc.com:5198");
    static List devTair = Arrays.asList("tair-m1.dev.pajkdc.com:5198");
    static Map envMap = new HashMap();
    static List tairAddr = null;
//    static DefaultTairManager tairManager = null;

    static Long[] userIds = null;
    static Integer accDay = null;
    static Integer conDay = null;
    static Integer upDay = null;

    static {
        envMap.put("dev", devTair);
        envMap.put("test", testTair);
        envMap.put("pre", preTair);
        envMap.put("prod", prodTair);
    }

    public static void main(String[] args) {
        HelpFormatter helpFormatter = new HelpFormatter();
        CommandLineParser commandLineParser = new DefaultParser();
        CommandLine commandLine = null;
        String formatHelpStr = "java -jar xxx.jar [-e dev/test/pre/prod] [-w i/d] [-u yyyyMMdd] [-a int] [-c int] [-p ids] [-h]";
        Options opts = new Options();

        try {
            opts.addOption("e", true, "environment");
            opts.addOption("w", true, "work");
            opts.addOption("u", true, "upDate");
            opts.addOption("a", true, "accDay");
            opts.addOption("c", true, "conDay");
            opts.addOption("p", true, "userIds");
            opts.addOption("h", false, "help");

            commandLine = commandLineParser.parse(opts, args);
            if(commandLine.hasOption('h')){
                helpFormatter.printHelp(formatHelpStr, opts);
                return;
            }
            init(commandLine);
            work(commandLine);
        } catch (Exception e) {
            helpFormatter.printHelp(formatHelpStr, opts);
            e.printStackTrace();
        } finally {
            close();
            System.exit(0);
        }
    }

    static void init(CommandLine commandLine) {
        String env =  commandLine.getOptionValue('e');
        tairAddr = (List) envMap.get(env);
        if(null == tairAddr){
            throw  new IllegalArgumentException("lost -e args");
        }
        upDay = Integer.parseInt(commandLine.getOptionValue('u'));
        accDay = Integer.parseInt(commandLine.getOptionValue('a'));
        conDay = Integer.parseInt(commandLine.getOptionValue('c'));
        String[] userIdStrs = commandLine.getOptionValues('p');
        userIds = new Long[userIdStrs.length];
        for(int i = 0; i < userIds.length; i++){
            userIds[i] = Long.parseLong(userIdStrs[i]);
        }

//        tairManager = new DefaultTairManager();
//        tairManager.setConfigServerList(tairAddr);
//        tairManager.setTimeout(5000);
//        tairManager.setGroupName("p_group1");
//        tairManager.init();
    }

    static void close() {
//        if (null == tairManager) {
//            return;
//        }
//        tairManager.close();
    }

    static void ins(CommandLine commandLine) {
        if(!commandLine.hasOption('w') || !"i".equals(commandLine.getOptionValue('w'))){
            return;
        }
//        JSONObject jsonObject = new JSONObject();
//
//        for (Long userId : userIds) {
//
//            jsonObject.put("RobotCourseTairPrefixAcc", Integer.valueOf(accDay));
//            jsonObject.put("RobotCourseTairPrefixContinue", Integer.valueOf(conDay));
//            jsonObject.put("RobotCourseUpdateTairTorF", Integer.valueOf(1));
//            jsonObject.put("RobotCourseLastClassDay", Integer.valueOf(upDay));
//
//            ResultCode resultCode = tairManager.put(255, "JsumoRobotCourseActivityTairKey" + userId, jsonObject, 0, 3 * 24 * 3600);
//            System.out.println(userId + " put result: " + resultCode);
//        }
    }

    static void del(CommandLine commandLine) {
        if(!commandLine.hasOption('w') || !"d".equals(commandLine.getOptionValue('w'))){
            return;
        }
//        for (Long userId : userIds) {
//            ResultCode resultCode = tairManager.delete(255, "JsumoRobotCourseActivityTairKey" + userId);
//            System.out.println(userId + " del result: " + resultCode);
//        }
    }

    static void work(CommandLine commandLine) {
        ins(commandLine);
        del(commandLine);
    }
}