package com.keysu.customizedrental.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.keysu.customizedrental.R;
import com.keysu.customizedrental.entity.HistoryData;
import com.keysu.customizedrental.utils.ToastUtil;

import java.util.List;

public class HistoryDataAdapter extends RecyclerView.Adapter<HistoryDataAdapter.ViewHolder>{

    private List<HistoryData> mHistoryDataList;


    static class ViewHolder extends RecyclerView.ViewHolder{

        View historyDataView;
        TextView historyDataName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            historyDataView = itemView;
            historyDataName = itemView.findViewById(R.id.tv_historydate);
        }
    }

    public HistoryDataAdapter(List<HistoryData> historyDataList){
        mHistoryDataList = historyDataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history_data,
                parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.historyDataView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                HistoryData historyData = mHistoryDataList.get(position);
                ToastUtil.show("a你点击了"+historyData.getData());
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HistoryData historyData = mHistoryDataList.get(position);
        holder.historyDataName.setText(historyData.getData());

    }

    @Override
    public int getItemCount() {
        return mHistoryDataList.size();
    }


}
