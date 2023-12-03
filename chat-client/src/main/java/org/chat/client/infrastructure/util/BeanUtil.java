package org.chat.client.infrastructure.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 将系统中常用的单例存储在map中，要用的时候直接get过来
 * @author XiaoRed
 * @date 2023/12/1 19:13
 */
public class BeanUtil {

    public static Map<String , Object> cacheMap = new ConcurrentHashMap<>();

    public static synchronized void addBean(String name, Object obj) {cacheMap.put(name, obj);}

    public static<T> T  getBean(String name) {return (T) cacheMap.get(name);}

    public static <T> T getBean(String name, Class<T> t) {
        return (T) cacheMap.get(name);
    }
}
