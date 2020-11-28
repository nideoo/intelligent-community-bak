package com.enc;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * Created by 马晓光 on 2018/12/18.
 */
@Slf4j
public class T {


    public static void main(String[] args) {

        Optional<Integer> optional1 = Optional.ofNullable(1);
        Optional<Integer> optional2 = Optional.ofNullable(null);

        // 如果不是null,调用Consumer
        optional1.ifPresent(new Consumer<Integer>() {
            @Override
            public void accept(Integer t) {
                System.out.println("value is " + t);
            }
        });

        List<String> list = new ArrayList<String>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add(null);
       // list.add("d");
        Optional<List<String>> optional= Optional.ofNullable(list);

        optional.ifPresent(new Consumer<List<String>>() {
            @Override
            public void accept(List<String> stringList) {
                System.out.println(stringList);
            }
        });






        // null,不调用Consumer
        optional2.ifPresent(new Consumer<Integer>() {
            @Override
            public void accept(Integer t) {
                System.out.println("value is " + t);
            }
        });
    }
}
