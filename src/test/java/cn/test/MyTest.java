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
        keyMap.put("1","89efda9f868e286f747bd48ea1ed6074af2bbe45bec2a6d6768f4b199ba4f4e1");
        keyMap.put("2","89efda9f868e286f747bd48ea1ed6074af2bbe45bec2a6d6768f4b199ba4f4e1");
        keyMap.put("3","89efda9f868e286f747bd48ea1ed6074af2bbe45bec2a6d6768f4b199ba4f4e1");
        //流水号
        String sid = "gCoQVLM0lfvhq17Y2iOTNo3r2aLEOqXxjU6zqV-k6N-M5DKUqqjb93ZqKg02_lQR_1"; //命中2规则
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
