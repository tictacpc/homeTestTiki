package com.chaunguyen.myapplication.presentation.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.chaunguyen.myapplication.R;
import com.chaunguyen.myapplication.common.constant.ErrCode;

public class MainActivity extends AppCompatActivity implements Main.View{

    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainPresenter();
        presenter.attach(this);
        presenter.listHotKey();
    }

    @Override
    protected void onDestroy() {
        presenter.detach();
        super.onDestroy();
    }

    @Override
    public void showErrorDialog(ErrCode errCode, String message) {

    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void hideProgressDialog() {

    }
}
