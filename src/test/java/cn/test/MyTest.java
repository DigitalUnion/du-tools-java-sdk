package cn.test;

import cn.shuzilm.tools.cdid.CDID;
import cn.shuzilm.tools.serial.Serial;
import cn.shuzilm.tools.serial.SerialResp;

import java.util.HashMap;

public class MyTest {
    public static void main(String[] args) {
        //校验设备ID
        boolean ok = CDID.verifyDid("D2zvJQbvKX0dCXopl3z07Jg39Y9srgCL7z9nCItahceakX2b");
        System.out.println(ok);
        //密钥字典
        HashMap<String,String > keyMap = new HashMap<>();
        keyMap.put("1","1d39d9f148ea636e151f0eec7bda9c37bbaf1ff9466b13ad0ffc1677c23610c7");
        keyMap.put("2","1d39d9f148ea636e151f0eec7bda9c37bbaf1ff9466b13ad0ffc1677c23610c7");
        keyMap.put("3","1d39d9f148ea636e151f0eec7bda9c37bbaf1ff9466b13ad0ffc1677c23610c7");
        //流水号
        String sid = "PhJM0lDLt1lF7ODIXxSD7W2GKJ1w9MTWcQf4L1KcXAY_1"; //命中2规则
        //获取秘钥
        String key = keyMap.get(Serial.getVersion(sid));
        if (key != null) {
            SerialResp resp = Serial.Decode(sid, key);
            System.out.println(resp);
            System.out.println(resp.isLegal); //是否合规
            System.out.println(resp.timestamp); //时间戳ms级
            System.out.println(resp.rules); //命中规则
        }
    }
}
