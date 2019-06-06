package com.chaunguyen.myapplication.presentation.main;

import android.text.TextUtils;

import com.chaunguyen.myapplication.App;
import com.chaunguyen.myapplication.R;
import com.chaunguyen.myapplication.common.constant.ErrCode;
import com.chaunguyen.myapplication.common.logic.StringUtils;
import com.chaunguyen.myapplication.domain.model.HotKeyItemDTO;
import com.chaunguyen.myapplication.domain.model.ServiceItem;
import com.chaunguyen.myapplication.domain.usecases.GetHotKey;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter implements Main.Presenter {

    private CompositeDisposable dispose;
    private Main.View view;
    private GetHotKey apiHotKey;
    private int[] androidColors;

    public MainPresenter() {

        dispose = new CompositeDisposable();
        apiHotKey = new GetHotKey();
        androidColors = App.getInstance().getResources().getIntArray(R.array.androidcolors);

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
    public List<ServiceItem> getListService() {
        ArrayList<ServiceItem> arr = new ArrayList<>();
        arr.add(new ServiceItem(R.drawable.ic_utils_air_ticket, App.getInstance().getString(R.string.serviceAir)));
        arr.add(new ServiceItem(R.drawable.ic_utils_protect, App.getInstance().getString(R.string.serviceProtect)));
        arr.add(new ServiceItem(R.drawable.ic_utils_card_number, App.getInstance().getString(R.string.serviceCard)));
        arr.add(new ServiceItem(R.drawable.ic_utils_air_ticket, App.getInstance().getString(R.string.serviceAir)));
        return arr;
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
                        .flatMap(listData -> {

                            List<String> arrNewLine = new ArrayList<>();

                            for (String data : listData) {
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

                            return Single.just(arrNewLine);
                        })
                        .flatMap(hotKeys ->{
                            List<HotKeyItemDTO> arr = new ArrayList<>();
                            for (String content : hotKeys) {
                                int randomColor = androidColors[new Random().nextInt(androidColors.length)];
                                arr.add(new HotKeyItemDTO(randomColor, content));
                            }
                            return Single.just(arr);
                        })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new GetListHotKeyObserver())
        );

    }

    private final class GetListHotKeyObserver extends DisposableSingleObserver<List<HotKeyItemDTO>> {
        @Override
        public void onSuccess(List<HotKeyItemDTO> listHotKey) {
            if (view == null) return;
            view.hideProgressDialog();
            view.loadHotKeys(listHotKey);
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

    @Override
    public List<String> getListCategory() {
        List<String> list = new ArrayList<>();
        list.add("Mẹ & Bé");
        list.add("Sức khoẻ");
        list.add("Dụng ");
        list.add("Dịch vụ");
        return list;
    }
}
