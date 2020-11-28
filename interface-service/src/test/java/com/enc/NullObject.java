package com.enc;

/**
 * Created by 马晓光 on 2018/12/18.
 */
public class NullObject implements DependencyBase {

    @Override
    public boolean isNull() {
        return false;
    }

    @Override
    public void Operation() {
        System.out.println(" this is test");
    }
}
