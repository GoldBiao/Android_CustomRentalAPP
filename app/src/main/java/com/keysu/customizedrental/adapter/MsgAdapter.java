package com.keysu.customizedrental.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.keysu.customizedrental.R;
import com.keysu.customizedrental.entity.Msg;

public class MsgAdapter extends RecyclerView.Adapter<MsgAdapter.ViewHolder> {
    List<Msg> msgs = new ArrayList<>();
    Context context;
    LayoutInflater inflater;
    Integer whoseChat;
    private final int[] layout_list = {R.layout.fragment_msg_item, R.layout.fragment_msg_item2,
            R.layout.fragment_msg_item3, R.layout.fragment_msg_item4,
            R.layout.fragment_msg_item5, R.layout.fragment_msg_item6};

    public MsgAdapter(List<Msg> msgs, Context context, Integer whoseChat) {
        this.msgs = msgs;
        this.context = context;
        this.whoseChat = whoseChat;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MsgAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = inflater.inflate(layout_list[whoseChat - 1], null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MsgAdapter.ViewHolder holder, int position) {
        Msg msg = msgs.get(position);
        if (msg.getType() == Msg.type_received) {
            holder.left_layout.setVisibility(View.VISIBLE);
            holder.left_msg.setText(msg.getContent());
            toolVisible(holder);

            holder.right_layout.setVisibility(View.GONE);
            holder.head_layout.setVisibility(View.GONE);

        } else if (msg.getType() == Msg.type_sent) {
            holder.right_layout.setVisibility(View.VISIBLE);
            holder.head_layout.setVisibility(View.VISIBLE);

           toolGone(holder);
            holder.left_layout.setVisibility(View.GONE);
            holder.right_msg.setText(msg.getContent());

        }
    }




    @Override
    public int getItemCount() {
        return msgs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView left_msg;
        TextView right_msg;
        LinearLayout left_layout;
        LinearLayout right_layout;
        RelativeLayout head_layout;
        ImageView house_m1;
        ImageView house_m2;
        ImageView house_m3;
        ImageView house_m4;
        ImageView house_m5;
        ImageView house_m6;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            left_msg = itemView.findViewById(R.id.left_msg);
            right_msg = itemView.findViewById(R.id.right_msg);
            left_layout = itemView.findViewById(R.id.left_layout);
            right_layout = itemView.findViewById(R.id.right_layout);
            head_layout = itemView.findViewById(R.id.head_layout);
            house_m1 = itemView.findViewById(R.id.house_m1);
            house_m2 = itemView.findViewById(R.id.house_m2);
            house_m3 = itemView.findViewById(R.id.house_m3);
            house_m4 = itemView.findViewById(R.id.house_m4);
            house_m5 = itemView.findViewById(R.id.house_m5);
            house_m6 = itemView.findViewById(R.id.house_m6);
        }
    }


    public void toolVisible(MsgAdapter.ViewHolder holder) {
        if (holder.house_m1 != null) {
            holder.house_m1.setVisibility(View.VISIBLE);
        } else if (holder.house_m2 != null) {
            holder.house_m2.setVisibility(View.VISIBLE);
        } else if (holder.house_m3 != null) {
            holder.house_m3.setVisibility(View.VISIBLE);
        } else if (holder.house_m4 != null) {
            holder.house_m4.setVisibility(View.VISIBLE);
        } else if (holder.house_m5 != null) {
            holder.house_m5.setVisibility(View.VISIBLE);
        }else if (holder.house_m6 != null) {
            holder.house_m6.setVisibility(View.VISIBLE);
        }
    }

    public void toolGone(MsgAdapter.ViewHolder holder) {
        if (holder.house_m1 != null) {
            holder.house_m1.setVisibility(View.GONE);
        } else if (holder.house_m2 != null) {
            holder.house_m2.setVisibility(View.GONE);
        } else if (holder.house_m3 != null) {
            holder.house_m3.setVisibility(View.GONE);
        } else if (holder.house_m4 != null) {
            holder.house_m4.setVisibility(View.GONE);
        } else if (holder.house_m5 != null) {
            holder.house_m5.setVisibility(View.GONE);
        }else if (holder.house_m6 != null) {
            holder.house_m6.setVisibility(View.GONE);
        }
    }
}