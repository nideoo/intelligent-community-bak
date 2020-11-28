package com.enc;

/**
 * Created by 马晓光 on 2018/12/18.
 */
public class Factory {

    public static DependencyBase get(Nullable dependencyBase){
        if (dependencyBase == null){
            return new NullObject();
        }
        return new Dependency();
    }
}
