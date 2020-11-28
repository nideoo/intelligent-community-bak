package com.enc;

/**
 * Created by 马晓光 on 2018/12/18.
 */
public class Client {
    public void test(DependencyBase dependencyBase){
        Factory.get(dependencyBase).Operation();
    }
}
