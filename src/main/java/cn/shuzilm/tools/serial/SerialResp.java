package cn.shuzilm.tools.serial;

import java.util.ArrayList;

public class SerialResp {
    //是否合法
    public boolean isLegal;
    //时间戳，ms级
    public long timestamp;
    //规则号
    public ArrayList<String> rules;

    public boolean isLegal() {
        return isLegal;
    }

    public void setLegal(boolean legal) {
        isLegal = legal;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public ArrayList<String> getRules() {
        return rules;
    }

    public void setRules(ArrayList<String> rules) {
        this.rules = rules;
    }

    @Override
    public String toString() {
        return "SerialResp{" +
                "isLegal=" + isLegal +
                ", timestamp=" + timestamp +
                ", rules=" + rules +
                '}';
    }
}
