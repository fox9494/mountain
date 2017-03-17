package org.chenll.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by chenlile on 17-3-9.
 * 读取properties的文件工具类
 */
public class PropertiesUtils {

    /**
     * 获取整个配置文件中的属性
     * @param filePath 文件路径，即文件所在包的路径，例如：java/util/config.properties
     */
    public static Properties readData(String filePath) {
        filePath = getRealPath(filePath);
        Properties props = new Properties();
        try {
            InputStream in = new BufferedInputStream(new FileInputStream(filePath));
            props.load(in);
            in.close();
            return props;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String getRealPath(String filePath) {
        //获取绝对路径 并截掉路径的”file:/“前缀
        return PropertiesUtils.class.getResource("/" + filePath).toString().substring(6);
    }
}
