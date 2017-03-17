package org.chenll.thread;

import org.chenll.util.ListComparatorUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

/**
 * Created by chenlile on 17-3-13.
 */
public class Test {

    public static void main(String[] args) {
        System.err.println("time:"+System.currentTimeMillis());

        List<Site> siteList = new ArrayList<Site>();
        siteList.add(new Site(9862.22));
        siteList.add(new Site(100023.99));
        siteList.add(new Site(12.56));
        siteList.add(new Site(562.22));
        siteList.add(new Site(0));




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

        Collections.sort(siteList);


        for (Site site : siteList) {
            System.out.println("site = [" + site.getDistance() + "]");
        }
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
