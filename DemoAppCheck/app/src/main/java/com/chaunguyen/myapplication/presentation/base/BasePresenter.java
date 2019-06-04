package com.chaunguyen.myapplication.presentation.base;


public interface BasePresenter<View> {
    void attach(View view);
    void detach();
}