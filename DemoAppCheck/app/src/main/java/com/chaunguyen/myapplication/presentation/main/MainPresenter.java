package com.chaunguyen.myapplication.presentation.main;

import android.text.TextUtils;

import com.chaunguyen.myapplication.App;
import com.chaunguyen.myapplication.common.constant.ErrCode;
import com.chaunguyen.myapplication.domain.usecases.GetHotKey;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter implements Main.Presenter {

    private CompositeDisposable dispose;
    private Main.View view;
    private GetHotKey apiHotKey;

    public MainPresenter() {

        apiHotKey = new GetHotKey();


    }

    @Override
    public void attach(Main.View view) {
        this.view = view;
    }

    @Override
    public void detach() {
        dispose.dispose();
        view = null;
    }

    @Override
    public void listHotKey() {
        if (!App.getInstance().hasNetworkConnection()) {
            view.showErrorDialog(ErrCode.CONNECTIVITY_PROBLEM, null);
            return;
        }
        view.showProgressDialog();
        dispose.add(
                apiHotKey.listDataHotKey()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new GetListHotKeyObserver())
        );

    }

    private final class GetListHotKeyObserver extends DisposableSingleObserver<List<String>[]> {
        @Override
        public void onSuccess(List<String>[] listHotKey) {
            if (view == null) return;
            view.hideProgressDialog();

            dispose();
        }

        @Override
        public void onError(Throwable e) {
            if (view == null) return;
            view.hideProgressDialog();
            String error = (e == null || TextUtils.isEmpty(e.getMessage())) ? "" : e.getMessage();
            view.showErrorDialog(ErrCode.UNKNOWN_ERROR, error);
            dispose();
        }
    }

}
