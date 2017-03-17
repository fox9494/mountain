package org.chenll.thread;

/**
 * Created by chenlile on 17-3-14.
 */
public class StringFormatTest {


    public static void main(String[] args) {
        ;

        System.out.println(String.format("This is my %s and %d and %c","what",18,'v'));

        System.out.println(String.format("This is my %d",12));
        System.out.println(String.format("This is my %c",'p'));

        System.out.println(String.format("This is my %f",12.2));

    }
}
