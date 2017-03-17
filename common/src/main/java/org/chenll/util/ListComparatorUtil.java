package org.chenll.util;

import java.util.Comparator;

/**
 * Created by chenlile on 17-3-8.
 * 对象比较排序工具
 * 此工具类需要new
 * 调用Example-- new ListComparatorUtil(true,"propertyName")
 */
public class ListComparatorUtil implements Comparator{


    /***
     * 是否转化为Int之后再比较
     */
    private boolean isConvertInteger;
    /***
     * 对对象的哪个属性列进行排序
     */
    private String comparedProperty;

    public ListComparatorUtil(boolean isConvertInteger, String comparedProperty) {
        this.isConvertInteger = isConvertInteger;
        this.comparedProperty=comparedProperty;
    }


    @Override
    public int compare(Object o1, Object o2) {
        if(null!=o1&&null!=o2)
        {
            try {
                Object obj1=ReflectionUtil.getFieldValue(o1, comparedProperty);
                Object obj2=ReflectionUtil.getFieldValue(o2, comparedProperty);
                if (obj1==null && obj2!=null){
                    return -1;
                }
                if (obj1!=null && obj2==null){
                    return 1;
                }
                if (obj1==null && obj2==null){
                    return 0;
                }
                if(isConvertInteger){
                    int num1;
                    int num2;
                    if(obj1 instanceof Integer){
                        num1=(Integer)obj1;
                        num2=(Integer)obj2;
                    }else{
                        num1=Integer.parseInt(obj1.toString());
                        num2=Integer.parseInt(obj2.toString());
                    }
                    if(num1>num2){
                        return 1;
                    }else if(num1<num2){
                        return -1;
                    }else{
                        return 0;
                    }
                }else{
                    return obj1.toString().compareTo(obj2.toString());
                }
            }catch (SecurityException e) {
                e.printStackTrace();
            }catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        return 0/*等于*/;
    }
}
