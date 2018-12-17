package com.demo.chatting.chattingwebsocketB.util;

import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;

/**
 * @Author: Little Rookies
 * @Date: 2018/10/17 15:26
 */
public class ResourceUtil {
    /**
     * 读取login.properties配置文件
     */

    public static String getKey(String key) {
        String result = null;
        Properties prop = new Properties();
        try {
            ClassLoader classLoader = ResourceUtil.class.getClassLoader();// 读取属性文件xxxxx.properties
            InputStream in = classLoader.getResourceAsStream("application.properties");
            prop.load(in); /// 加载属性列表
            Iterator<String> it = prop.stringPropertyNames().iterator();
            while (it.hasNext()) {
                if (it.next().equals(key)) {
                    result = prop.getProperty(key);
                }
            }
            in.close();
        } catch (Exception e) {

        }
        return result;
    }


}
