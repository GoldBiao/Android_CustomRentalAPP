package com.keysu.customizedrental.activity;

//聊天界面

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.keysu.customizedrental.R;
import com.keysu.customizedrental.adapter.MsgAdapter;
import com.keysu.customizedrental.entity.Msg;

import java.util.ArrayList;
import java.util.List;

public class ItemDetailActivity extends AppCompatActivity {

    Toolbar toolbar;
    List<Msg> list = new ArrayList<>();
    Integer whoseChat;
    RecyclerView recyclerView;
    MsgAdapter msgAdapter;
    Context context = ItemDetailActivity.this;
    EditText msg_say;
    Button emoji;
    Button send;
    LinearLayout linearLayout;

//    int lastx = 0;
//    int lasty = 0;

    private final String[] messageList = {"什么时候过来看看？", "来我这！便宜又实惠！",
            "付一压三！至少租三个月！", "不要算了，我这不愁客人。", "别砍价，租不起赶紧滚蛋。",
            "可以给你便宜点！"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        setStatusBar();
        recyclerView = findViewById(R.id.msg_list);
        toolbar = findViewById(R.id.msg_toolbar);
        msg_say = findViewById(R.id.msg_say);
        emoji = findViewById(R.id.msg_emoji);
        send = findViewById(R.id.msg_send);
        linearLayout = findViewById(R.id.linear);
        initData();
        initView();

    }



    private void initData() {
        this.whoseChat = getIntent().getIntExtra("whose", 1);
        toolbar.setTitle(getIntent().getStringExtra("who"));
        Msg msg = new Msg(messageList[this.whoseChat - 1], Msg.type_received);
        list.add(msg);
        Msg msg1 = new Msg("算了算了，我换一个！", Msg.type_sent);
        list.add(msg1);
    }


    private void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        msgAdapter = new MsgAdapter(list, context, whoseChat);
        recyclerView.setAdapter(msgAdapter);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        send.getLayoutParams().width = emoji.getLayoutParams().width;
        msg_say.addTextChangedListener(textWatcher);

        send.setOnClickListener(new View.OnClickListener() {//发送按钮监听
            @Override
            public void onClick(View v) {
                if (msg_say.getEditableText().length() >= 1) {
                    String content = msg_say.getText().toString();
                    if (!"".equals(content)) {
                        Msg msg = new Msg(content, Msg.type_sent);
                        list.add(msg);
                        Msg msg1 = new Msg(content, Msg.type_received);
                        list.add(msg1);
                        msgAdapter.notifyItemInserted(list.size() - 1);
                        recyclerView.scrollToPosition(list.size() - 1);
                        msg_say.setText("");

                    }
                } else {
                    Toast.makeText(context, "点击add", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @SuppressLint("ResourceAsColor")
        @Override
        public void afterTextChanged(Editable s) {//发送按钮和加号的切换
            if (msg_say.getEditableText().length() >= 1) {
                send.setBackgroundResource(R.drawable.button_background);
                send.setText("发送");
                send.getLayoutParams().height = emoji.getLayoutParams().height;
                send.getLayoutParams().width = emoji.getLayoutParams().width + 30;
                send.setTextColor(context.getResources().getColor(R.color.white));
            } else {
                send.setBackgroundResource(R.drawable.ic_add_24);
                send.getLayoutParams().height = emoji.getLayoutParams().height;
                send.getLayoutParams().width = emoji.getLayoutParams().width;
                send.setText("");
            }
        }
    };

    protected void setStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

//    @Override
//    public boolean dispatchTouchEvent(MotionEvent event) {
//        int x = (int) event.getX();
//        int y = (int) event.getY();
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                lastx = x;
//                lasty = y;
//                break;
//            case MotionEvent.ACTION_UP:
//                int curx = x;
//                int cury = y;
//
//                if (curx-lastx>300&&Math.abs(cury-lasty)<200)
//                    finish();
//                break;
//        }
//        return super.dispatchTouchEvent(event);
//    }

}