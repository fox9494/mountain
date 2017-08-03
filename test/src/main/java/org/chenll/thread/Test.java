package org.chenll.thread;

import org.chenll.util.DateTimeUtils;
import org.chenll.util.ListComparatorUtil;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by chenlile on 17-3-13.
 */
public class Test {

    public static void main(String[] args) {


        long cur = System.currentTimeMillis();
        while(System.currentTimeMillis() - cur <=10000){
            long send = cur / 1000;
            System.out.println(String.format("the cur:%s,the send:%s",System.currentTimeMillis(),send));
        }

    }




}
