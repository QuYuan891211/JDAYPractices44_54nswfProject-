package cn.qy.nswf.info.entity;

import javax.management.monitor.StringMonitor;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by qy on 2017/4/26.
 */
public class Info implements Serializable{
    private String infoId;
    private String type;
    private String source;
    private String title;
    private String Content;
    private String memo;
    private String creator;
    private Timestamp createTime;
    private String state;

    public static String INFO_STATE_PUBLIC="public";
    public static String INFO_STATE_STOP="stop";

    public static String INFO_TYPE_TZGG="TZGG";
    public static String INFO_TYPE_ZCSD="zcsd";
    public static String INFO_TYPE_NSZD="nszd";

    public static Map<String,String> INFO_TYPE_MAP;
    static {
        INFO_TYPE_MAP = new HashMap<String,String>();
        INFO_TYPE_MAP.put(INFO_TYPE_TZGG, "TZGG");
        INFO_TYPE_MAP.put(INFO_TYPE_ZCSD, "ZCSD");
        INFO_TYPE_MAP.put(INFO_TYPE_NSZD, "NSZD");
    }

    public Info() {
    }

    public Info(String infoId, String type, String source, String title, String content, String memo, String creator, Timestamp createTime, String state) {
        this.infoId = infoId;
        this.type = type;
        this.source = source;
        this.title = title;
        Content = content;
        this.memo = memo;
        this.creator = creator;
        this.createTime = createTime;
        this.state = state;
    }

    public String getInfoId() {
        return infoId;
    }

    public void setInfoId(String infoId) {
        this.infoId = infoId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
