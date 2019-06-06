package com.chaunguyen.myapplication.common.logic;

import android.util.Log;

import com.chaunguyen.myapplication.domain.model.SortResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created on 2019-06-06.
 */
public class StringUtils {
    private void getText(String[] data, int index1, int index2){
        String s1 = "";
        String s2 = "";
        String s3 = "";
        String s4 = "";

        for (int i = 0; i < index1; i++) {
            s1 += " " + data[i];
        }
        s1 = s1.trim();

        for (int t = index1; t < data.length; t++){
            s2 += " " + data[t];
        }
        s2 = s2.trim();
        Log.d("TAG", "getText1: " + s1);
        Log.d("TAG", "getText2: " + s2);
        int smallest = Math.max(s1.length(), s2.length()) - Math.min(s1.length(), s2.length());
        Log.d("TAG", "getTextTotal: " + smallest);

        for (int i = 0; i < index2; i++) {
            s3 += " " + data[i];
        }
        s3 = s3.trim();

        for (int t = index2; t < data.length; t++){
            s4 += " " + data[t];
        }
        s4 = s4.trim();
        Log.d("TAG", "getText3: " + s3);
        Log.d("TAG", "getText4: " + s4);
        smallest = Math.max(s3.length(), s4.length()) - Math.min(s3.length(), s4.length());
        Log.d("TAG", "getTextTotal: " + smallest);

    }

    public static SortResult getTextV2(String[] data, int index1){
        String s1 = "";
        String s2 = "";

        for (int i = 0; i < index1; i++) {
            s1 += " " + data[i];
        }
        s1 = s1.trim();

        for (int t = index1; t < data.length; t++){
            s2 += " " + data[t];
        }
        s2 = s2.trim();
        Log.d("TAG", "Line1: " + s1);
        Log.d("TAG", "Line2: " + s2);
        int smallest = Math.max(s1.length(), s2.length()) - Math.min(s1.length(), s2.length());
        Log.d("TAG", "LineShow: " + smallest);
        String dataNewLine = s1 + "\n" + s2;
        return new SortResult(smallest, dataNewLine);
    }

    public static String handleSetNewLine(String[] data){
        ArrayList<SortResult> arr = new ArrayList<>();

        for (int i = 1; i < data.length; i++) {
            arr.add(getTextV2(data, i));
        }

        Collections.sort(arr, new Comparator<SortResult>() {
            @Override
            public int compare(SortResult t1, SortResult t2) {
                return t1.lengthCheck - t2.lengthCheck;
            }
        });

        String showText = "";
        if(arr.size() > 0){
            showText = arr.get(0).data;
        }

        Log.d("TAG", "ResultNewLine: " + showText);
        return showText;
    }
}
