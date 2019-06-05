package com.chaunguyen.myapplication;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.chaunguyen.myapplication.domain.model.HotKeyItemDTO;
import com.chaunguyen.myapplication.domain.usecases.GetHotKey;
import com.chaunguyen.myapplication.presentation.main.MainPresenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

import static org.junit.Assert.*;

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
        apiHotKey.listDataHotKey()
                .flatMap(list -> {

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

                            }

                        } else {
                            result = data;
                        }

//                        arrNewLine.add(result);


                    }

                })
                .subscribeWith(new DisposableSingleObserver<List<String>>() {
                    @Override
                    public void onSuccess(List<String> strings) {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("TAG", "onError: " + e.getMessage());
                    }
                });

    }

    @Test
    public void checkNewLine() {
        ArrayList<String> list = new ArrayList<>();
        list.add("harry potter");
        list.add("tai nghe bluetooth");
        list.add("nguyễn nhật ánh");
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
                    int lastMinChange = 0;
                    int positionAddLine = 0;
//
//                    for (int i = 0; i < noSpace.length - 1; i++) {
//
//                        int posCurrent = noSpace[i].length();
//
//                        int totalNext = 0;
//                        StringBuilder textRight = new StringBuilder();
//                        //next space
//                        int t;
//                        while ( t = i + 1 < noSpace.length){
//                            totalNext += noSpace[t].length();
//                            textRight.append(" ").append(noSpace[t]);
//                            t++;
//                        }
//
//                        int rsChange = Math.max(posCurrent,totalNext) - Math.min(posCurrent,totalNext);
//                        if(rsChange < lastMinChange){
//                            lastMinChange = rsChange;
//                            positionAddLine = posCurrent;
//                        }//else continue check
//                    }

//                    if(positionAddLine != 0){
//                        result = textRight.;
//                    }else {
//                        result = data;
//                    }
                }
            } else {
                result = data;
            }

            arrNewLine.add(result);


        }

    }


}
