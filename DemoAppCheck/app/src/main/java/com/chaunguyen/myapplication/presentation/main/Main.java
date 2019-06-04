package com.chaunguyen.myapplication.presentation.main;

import com.chaunguyen.myapplication.common.constant.ErrCode;
import com.chaunguyen.myapplication.presentation.base.BasePresenter;

public interface Main {

    interface View {

        void showErrorDialog(ErrCode errCode, String message);

        void showProgressDialog();

        void hideProgressDialog();
    }

    interface Presenter extends BasePresenter<View> {

        void listHotKey();
    }

}
