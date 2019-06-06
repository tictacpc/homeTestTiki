package com.chaunguyen.myapplication;

import android.support.test.runner.AndroidJUnit4;

import com.chaunguyen.myapplication.common.logic.StringUtils;
import com.chaunguyen.myapplication.domain.usecases.GetHotKey;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class GetHotKeyApiTest {

    GetHotKey apiHotKey;

    @Before
    public void setUp() {
        this.apiHotKey = new GetHotKey();
    }


    @Test
    public void useAppContext() {
//        apiHotKey.listDataHotKey()
//                .flatMap(list -> {
//
//                    List<String> arrNewLine = new ArrayList<>();
//                    for (String data : list) {
//                        String result;
//                        if (data.contains(" ")) {
//
//                            String[] noSpace = data.split(" ");
//                            if (noSpace.length == 1) {
//                                result = noSpace[0];
//
//                            } else if (noSpace.length == 2) {
//                                result = noSpace[0] + "\n" + noSpace[1];
//
//                            } else {
//                                //- more 2 space
//
//                            }
//
//                        } else {
//                            result = data;
//                        }
//
////                        arrNewLine.add(result);
//
//
//                    }
//
//                })
//                .subscribeWith(new DisposableSingleObserver<List<String>>() {
//                    @Override
//                    public void onSuccess(List<String> strings) {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.e("TAG", "onError: " + e.getMessage());
//                    }
//                });

    }

    @Test
    public void checkNewLine() {
        ArrayList<String> list = new ArrayList<>();
//        list.add("harry potter");
//        list.add("tai nghe bluetooth");
//        list.add("nguyễn nhật ánh");
        list.add("anh chính là thanh xuân của em");


        List<String> arrNewLine = new ArrayList<>();
        for (String data : list) {
            String result;
            if (data.contains(" ")) {

                String[] noSpace = data.split(" ");
                if (noSpace.length == 1) {
                    result = noSpace[0];

                } else if (noSpace.length == 2) {
                    result = noSpace[0] + "\n" + noSpace[1];

                } else {
                    //- more 2 space
                    result = StringUtils.handleSetNewLine(noSpace);
                }
            } else {
                result = data;
            }
            arrNewLine.add(result);
        }

    }
}
