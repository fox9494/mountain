package org.chenll.util;

import java.net.Inet4Address;
import java.net.InetAddress;

/**
 * Created by chenlile on 17-3-14.
 */
public class IPUtils {


    /**
     * IPV4地址转Long
     *
     * @return
     */
    private static long getIp4LongValue() {
        try {
            InetAddress inetAddress = Inet4Address.getLocalHost();
            byte[] ip = inetAddress.getAddress();

            return Math.abs(((0L | ip[0]) << 24)
                    | ((0L | ip[1]) << 16)
                    | ((0L | ip[2]) << 8)
                    | (0L | ip[3]));

        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    public static void main(String[] args) {
        System.out.println("getIp4LongValue = [" + getIp4LongValue() + "]");
    }
}
