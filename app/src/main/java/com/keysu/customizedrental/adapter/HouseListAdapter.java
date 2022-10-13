package com.keysu.customizedrental.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.keysu.customizedrental.R;
import com.keysu.customizedrental.entity.HouseList;
import com.keysu.customizedrental.view.OnItemClickListener;

import java.util.List;

public class HouseListAdapter extends RecyclerView.Adapter<HouseListAdapter.ViewHolder> {

    private List<HouseList> mHouseLists;

    private OnItemClickListener mClickListener;

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView ivHouseHead;
        TextView tvHouseAddress;
        TextView tvHousearceage;
        TextView tvHouseIntro;
        TextView tvRentalYears;
        TextView tvPrice;
        TextView tvIsAgency;

        private OnItemClickListener mListener;// 声明自定义的接口


        public ViewHolder(@NonNull View view, OnItemClickListener  listener) {
            super(view);
            mListener = listener;
            ivHouseHead = view.findViewById(R.id.iv_house_head);
            tvHouseAddress = view.findViewById(R.id.tv_house_address);
            tvHousearceage = view.findViewById(R.id.tv_house_acreage);
            tvHouseIntro = view.findViewById(R.id.tv_house_intro);
            tvRentalYears = view.findViewById(R.id.tv_rental_years);
            tvPrice = view.findViewById(R.id.tv_price);
            tvIsAgency = view.findViewById(R.id.tv_is_agency);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            // getpostion()为Viewholder自带的一个方法，用来获取RecyclerView当前的位置，将此作为参数，传出去
            mListener.onItemClick(v, getPosition());
        }
    }











    public HouseListAdapter(List<HouseList> houseLists){
        mHouseLists = houseLists;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.house_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view, mClickListener);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        /**
         * ivHouseHead = view.findViewById(R.id.iv_house_head);
         *             tvHouseAddress = view.findViewById(R.id.tv_house_address);
         *             tvHousearceage = view.findViewById(R.id.tv_house_acreage);
         *             tvHouseIntro = view.findViewById(R.id.tv_house_intro);
         *             tvRentalYears = view.findViewById(R.id.tv_rental_years);
         *             tvPrice = view.findViewById(R.id.tv_price);
         *             tvIsAgency = view.findViewById(R.id.tv_is_agency);
         */
        HouseList houseList = mHouseLists.get(position);
        holder.ivHouseHead.setImageResource(houseList.getHeadImage());
        holder.tvHouseAddress.setText(houseList.getHouseAddress());
        holder.tvHousearceage.setText(houseList.getHouseAcreage());
        holder.tvHouseIntro.setText(houseList.getHouseIntro());
        holder.tvRentalYears.setText(houseList.getRentalYears());
        holder.tvPrice.setText(String.valueOf(houseList.getPrice()));
        if (houseList.getIsAgency()){
            holder.tvIsAgency.setText("是");
        }else
            holder.tvIsAgency.setText("否");
    }

    @Override
    public int getItemCount() {
        return mHouseLists.size();
    }


    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mClickListener = listener;
    }

}
