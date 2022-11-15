package com.cmhi.tvparams;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author: fengjianwei
 * @time: 2022/9/15
 * Description:
 **/
public class Test {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Please input the flag");
        String str = s.next();
        System.out.println("Your input is");
        System.out.println(str);
        char[] stringArr = str.toCharArray();
        Encrypt(stringArr);
    }

    public static void Encrypt(char[] arr) {
        ArrayList<Integer> Resultlist = new ArrayList();

        for(int i = 0; i < arr.length; ++i) {
            int result = arr[i] + 64 ^ 32;
            Resultlist.add(result);
        }

        System.out.println(Resultlist);
        int[] KEY = new int[]{161, 147, 147, 132, 134, 147, 154, 152, 131, 129, 154, 154, 152, 147, 131, 154};
        ArrayList<Integer> KEYList = new ArrayList();

        for(int j = 0; j < KEY.length; ++j) {
            KEYList.add(KEY[j]);
        }

        System.out.println("Result:");
        if (Resultlist.equals(KEYList)) {
            System.out.println("Congratulations");
        } else {
            System.err.println("Error");
        }

    }
}
