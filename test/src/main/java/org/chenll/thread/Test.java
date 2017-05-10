package org.chenll.thread;

import org.chenll.util.DateTimeUtils;
import org.chenll.util.ListComparatorUtil;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;

/**
 * Created by chenlile on 17-3-13.
 */
public class Test {

    public static void main(String[] args) {

        System.out.println(DateTimeUtils.str2Date("2017-05-07 00:00:00","yyyy-MM-dd HH:mm:ss").getTime());

        System.out.println(System.currentTimeMillis());
//        Collections.sort(siteList, new Comparator<Site>() {
//            @Override
//            public int compare(Site o1, Site o2) {
//                if(o1.getDistance()>o2.getDistance()){
//                    return 1;
//                }
//                if(o1.getDistance()<o2.getDistance()){
//                    return -1;
//                }
//                return 0;
//            }
//        });



    }




    public static class Site implements Comparable<Site>{
        private double distance;


        public Site(double distance) {
            this.distance = distance;
        }

        public double getDistance() {
            return distance;
        }

        public void setDistance(double distance) {
            this.distance = distance;
        }

        @Override
        public int compareTo(Site o) {
            if(this.getDistance()>o.getDistance()){
                return 1;
            }
            if(this.getDistance()<o.getDistance()){
                return -1;
            }
            return 0;
        }
    }
}
