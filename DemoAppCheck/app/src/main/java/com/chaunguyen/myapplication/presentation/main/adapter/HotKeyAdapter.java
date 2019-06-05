package com.chaunguyen.myapplication.presentation.main.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chaunguyen.myapplication.R;
import com.chaunguyen.myapplication.domain.model.HotKeyItemDTO;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created on 2019-06-05.
 */
public class HotKeyAdapter extends RecyclerView.Adapter<HotKeyAdapter.HotKeyViewHolder> {


    private List<HotKeyItemDTO> hotKeys;
    private OnClickItemHotKeyListener listener;

    public HotKeyAdapter() {
        this.hotKeys = new ArrayList<>();
    }

    @NonNull
    @Override
    public HotKeyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        return new HotKeyViewHolder(((LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                .inflate(R.layout.item_hot_key, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HotKeyViewHolder holder, int position) {
        HotKeyItemDTO item = hotKeys.get(position);
        holder.tv.setText(item.getContent());
        holder.setBackgroundColor(item.getColorId());
    }

    @Override
    public int getItemCount() {
        return hotKeys.size();
    }

    public void setData(List<HotKeyItemDTO> hotKeyItemList) {
        this.hotKeys = hotKeyItemList;
        notifyDataSetChanged();
    }

    public void setListener(OnClickItemHotKeyListener listener) {
        this.listener = listener;
    }

    class HotKeyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.card_root)
        CardView cardView;
        @BindView(R.id.tv_key)
        TextView tv;

        HotKeyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(view -> {
                if (listener != null) {
                    listener.onItemHotKeyClick(tv.getText().toString());
                }
            });
        }

        public void setBackgroundColor(int colorId){
            this.cardView.setCardBackgroundColor(colorId);
        }
    }

    public interface OnClickItemHotKeyListener {
        void onItemHotKeyClick(String hotKey);
    }
}
