package cn.shuzilm.tools.cdid;

public class CDID {

    //校验设备ID
    public static boolean verifyDid(String did){
        if (did.length() != 48) {
            return false;
        }
        String pre = did.substring(0,2);
        if (!pre.equals("D2")) {
            return false;
        }
        String sub = did.substring(46);
        String dev = did.substring(2,46);
        String chk = checkValue(dev);
        return sub.equals(chk);
    }
    private static String checkValue(String did) {
        int checkValue = 0;
        for (int i = 0; i < did.length(); i++) {
            if (did.charAt(i) == ':') {
                continue;
            }
            checkValue += did.charAt(i);
        }
        String hexChecksum = Integer.toHexString(checkValue % 256);
        if (hexChecksum.length() < 2) {
            hexChecksum = "0" + hexChecksum;
        }
        return hexChecksum;
    }
}
