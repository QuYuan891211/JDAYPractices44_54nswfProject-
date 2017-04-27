package cn.qy.core.constant;

import java.util.HashMap;

/**
 * Created by qy on 2017/3/6.
 */
public class Constant {
    //系统中用户在session中的标识符
    public static String User = "SYS_USER";

    public static String PRIVILEGE_XZGL="xzgl";
    public static String PRIVILEGE_HQFW="hqfw";
    public static String PRIVILEGE_ZXXX="zxxx";
    public static String PRIVILEGE_NSFW="nsfw";
    public static String PRIVILEGE_SPACE="space";
    public static HashMap<String ,String> PRIVILEGE_MAP;
    static {
        PRIVILEGE_MAP = new HashMap<String,String>();
        PRIVILEGE_MAP.put(PRIVILEGE_XZGL,"administrative management");
        PRIVILEGE_MAP.put(PRIVILEGE_HQFW,"rear services");
        PRIVILEGE_MAP.put(PRIVILEGE_ZXXX,"Online Study");
        PRIVILEGE_MAP.put(PRIVILEGE_NSFW,"Tax services");
        PRIVILEGE_MAP.put(PRIVILEGE_SPACE,"My Space");
    }
}