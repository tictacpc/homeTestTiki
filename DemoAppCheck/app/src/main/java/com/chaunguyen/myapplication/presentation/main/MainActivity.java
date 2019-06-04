package com.chaunguyen.myapplication.presentation.main;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.chaunguyen.myapplication.R;
import com.chaunguyen.myapplication.common.constant.ErrCode;
import com.chaunguyen.myapplication.presentation.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements Main.View, ServiceAdapter.OnClickItemServiceListener{

    @BindView(R.id.rcvService)
    RecyclerView rcvService;
    @BindView(R.id.rcvCategory)
    RecyclerView rcvCategory;

    private MainPresenter presenter;
    private ServiceAdapter serviceAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        presenter = new MainPresenter();
        presenter.attach(this);

        //<editor-fold desc="Setup View">
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false);
        rcvService.setLayoutManager(linearLayoutManager);
        serviceAdapter = new ServiceAdapter();
        serviceAdapter.setListener(this);
        rcvService.setAdapter(serviceAdapter);
        serviceAdapter.setServiceList(presenter.getListService());
        //</editor-fold>



//        presenter.listHotKey();
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
    public void showErrorDialog(ErrCode errCode, String message) {

    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void hideProgressDialog() {

    }
}
