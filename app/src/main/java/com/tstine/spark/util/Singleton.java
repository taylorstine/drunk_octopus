package com.tstine.spark.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by taylorstine on 5/29/14.
 */
public class Singleton {
    private static Singleton sInstance;
    private Map<Class, Singleton> registry;
    public static Singleton getInstance(Class clazz){
        if (sInstance == null){
            sInstance = new Singleton();
        }
        if (sInstance.registry == null){
            sInstance.registry = new HashMap<Class, Singleton>();
        }

        return sInstance.getRegistry().get(clazz);
    }

    public static void register(Class clazz, Singleton singleton){
        singleton.getRegistry().put(clazz, singleton);
    }

    public Map<Class, Singleton> getRegistry() {
        return registry;
    }
}
