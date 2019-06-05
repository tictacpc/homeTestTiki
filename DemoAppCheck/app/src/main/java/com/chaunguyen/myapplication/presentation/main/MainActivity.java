package com.chaunguyen.myapplication.presentation.main;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.chaunguyen.myapplication.R;
import com.chaunguyen.myapplication.common.constant.ErrCode;
import com.chaunguyen.myapplication.domain.model.HotKeyItemDTO;
import com.chaunguyen.myapplication.presentation.base.BaseActivity;
import com.chaunguyen.myapplication.presentation.main.adapter.HotKeyAdapter;
import com.chaunguyen.myapplication.presentation.main.adapter.ServiceAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements Main.View,
        ServiceAdapter.OnClickItemServiceListener,
        HotKeyAdapter.OnClickItemHotKeyListener{

    @BindView(R.id.rcvCategory)
    RecyclerView rcvCategory;
    @BindView(R.id.rcvService)
    RecyclerView rcvService;
    @BindView(R.id.rcvHotKey)
    RecyclerView rcvHotKey;

    private MainPresenter presenter;
    private ServiceAdapter serviceAdapter;
    private HotKeyAdapter hotKeyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        presenter = new MainPresenter();
        presenter.attach(this);

        //<editor-fold desc="Setup View">
        rcvService.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false));
        serviceAdapter = new ServiceAdapter();
        serviceAdapter.setListener(this);
        rcvService.setAdapter(serviceAdapter);
        serviceAdapter.setServiceList(presenter.getListService());

        rcvHotKey.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false));
        hotKeyAdapter = new HotKeyAdapter();
        hotKeyAdapter.setListener(this);
        rcvHotKey.setAdapter(hotKeyAdapter);
        presenter.listHotKey();
        //</editor-fold>

    }

    @Override
    protected void onDestroy() {
        presenter.detach();
        super.onDestroy();
    }

    @Override
    public void onItemClick(String service) {
        Toast.makeText(this, "Clicked " + service, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemHotKeyClick(String hotKey) {
        Toast.makeText(this, "Clicked Hot Key " + hotKey, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loadHotKeys(List<HotKeyItemDTO> listHotKey) {
        hotKeyAdapter.setData(listHotKey);
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
