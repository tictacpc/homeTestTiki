package com.chaunguyen.myapplication.presentation.main;

import com.chaunguyen.myapplication.common.constant.ErrCode;
import com.chaunguyen.myapplication.domain.model.HotKeyItemDTO;
import com.chaunguyen.myapplication.domain.model.ServiceItem;
import com.chaunguyen.myapplication.presentation.base.BasePresenter;

import java.util.List;

public interface Main {

    interface View {

        void showErrorDialog(ErrCode errCode, String message);

        void showProgressDialog();

        void hideProgressDialog();

        void loadHotKeys(List<HotKeyItemDTO> listHotKey);
    }

    interface Presenter extends BasePresenter<View> {

        void listHotKey();

        List<ServiceItem> getListService();
    }

}
