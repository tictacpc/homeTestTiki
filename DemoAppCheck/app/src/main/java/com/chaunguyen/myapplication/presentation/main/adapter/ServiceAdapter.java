package com.chaunguyen.myapplication.presentation.main.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chaunguyen.myapplication.R;
import com.chaunguyen.myapplication.domain.model.ServiceItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created on 2019-06-05.
 */
public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ServiceViewHolder> {


    private List<ServiceItem> serviceItemList;
    private OnClickItemServiceListener listener;

    public ServiceAdapter() {
        this.serviceItemList = new ArrayList<>();
    }

    @NonNull
    @Override
    public ServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        return new ServiceViewHolder(((LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                .inflate(R.layout.item_service, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceViewHolder holder, int position) {
        ServiceItem item = serviceItemList.get(position);
        holder.tv.setText(item.getContent());
        holder.img.setImageResource(item.getImgID());
    }

    @Override
    public int getItemCount() {
        return serviceItemList.size();
    }

    public void setServiceList(List<ServiceItem> serviceItemList) {
        this.serviceItemList = serviceItemList;
        notifyDataSetChanged();
    }

    public void setListener(OnClickItemServiceListener listener) {
        this.listener = listener;
    }

    class ServiceViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_service)
        ImageView img;
        @BindView(R.id.tv_service)
        TextView tv;

        ServiceViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(view -> {
                if(listener != null){
                    listener.onItemClick(tv.getText().toString());
                }
            });

        }
    }

    public interface OnClickItemServiceListener {
        void onItemClick(String service);
    }
}
