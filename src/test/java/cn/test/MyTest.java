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
        keyMap.put("2","y3hbnr8d4s2ztjbca1wgxk6mqktf9pxr");
        //流水号
        String sid = "Tt5ZsyeYIe3v_9nIV-Sce5W73_x_ubl3QWbz1bLCV32-qv2HP4ygPz8NVgF-Pzt48W78hhNmUPQpAfbODQWM3A_1"; //命中109规则
        //获取秘钥
        String key = keyMap.get(Serial.getVersion(sid));
        if (key != null) {
            SerialResp resp = Serial.Decode(sid, key);
            System.out.println(resp);
            System.out.println(resp.isLegal); //是否合规
            System.out.println(resp.timestamp); //时间戳ms级
            System.out.println(resp.rules); //命中规则
            System.out.println(resp.statusCode); //状态码
        }

        //流水号
        String sid2 = "srugr2EKSLGaIY2PuCvKidwYAgym7pkBTzSHz1Eg_9ljI2Y44ibctixVvuJ_RvrR5haYavGhL4Z7-fKh0vI1UA_2"; //命中2规则
        //获取秘钥
        String key2 = keyMap.get(Serial.getVersion(sid2));
        if (key2 != null) {
            SerialResp resp = Serial.Decode(sid2, key2);
            System.out.println(resp);
            System.out.println(resp.isLegal); //是否合规
            System.out.println(resp.timestamp); //时间戳ms级
            System.out.println(resp.rules); //命中规则
            System.out.println(resp.statusCode); //状态码
        }
    }
}
