package org.example.bigevent.utils;

import com.auth0.jwt.interfaces.Claim;

import java.util.Map;

public class ThreadLocalUtil {
    private static final ThreadLocal<Map<String, Object>>tl = new ThreadLocal<>();

    public static void set(Map<String, Object> maps){
        tl.set(maps);
    }

    public static Map<String, Object> get(){
        return tl.get();
    }

    public static void remove(){
        tl.remove();
    }
}
