package cn.qy.core.constant;

import java.util.HashMap;

/**
 * Created by qy on 2017/3/6.
 */
public class Constant {
    public static String PRIVILEGE_XZGL="xzgl";
    public static String PRIVILEGE_HQFW="hqfw";
    public static String PRIVILEGE_ZXXX="zxxx";
    public static String PRIVILEGE_NSFW="nsfw";
    public static String PRIVILEGE_SPACE="space";
    public static HashMap<String ,String> PRIVILEGE_MAP;
    static {
        PRIVILEGE_MAP.put(PRIVILEGE_XZGL,"行政管理");
        PRIVILEGE_MAP.put(PRIVILEGE_HQFW,"后勤服务");
        PRIVILEGE_MAP.put(PRIVILEGE_ZXXX,"在线学习");
        PRIVILEGE_MAP.put(PRIVILEGE_NSFW,"纳税服务");
        PRIVILEGE_MAP.put(PRIVILEGE_SPACE,"我的空间");
    }
}