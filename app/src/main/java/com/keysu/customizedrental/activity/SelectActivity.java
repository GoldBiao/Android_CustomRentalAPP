package com.keysu.customizedrental.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.keysu.customizedrental.R;
import com.keysu.customizedrental.adapter.HistoryDataAdapter;
import com.keysu.customizedrental.entity.HistoryData;

import java.util.ArrayList;
import java.util.List;
//搜索框
public class  SelectActivity extends AppCompatActivity implements View.OnClickListener{

    private List<HistoryData> historyDataList = new ArrayList<>();

    Button btnSelect;

    TextView tvClean;

    EditText etSelect;

    String selectEtData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        Intent intent= getIntent();
        selectEtData = intent.getStringExtra("selectEtData");
        initRecyclerViewHistoryData();
        initRecyclerView();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        StaggeredGridLayoutManager layoutManager =
                new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        HistoryDataAdapter adapter = new HistoryDataAdapter(historyDataList);
        recyclerView.setAdapter(adapter);
        btnSelect = findViewById(R.id.btn_select);
        btnSelect.setOnClickListener(this);
        tvClean = findViewById(R.id.tv_clean);
        tvClean.setOnClickListener(this);
    }

    private void initRecyclerViewHistoryData() {

        ArrayList<String> list = new ArrayList<>();
        list.add("地铁三号线");
        list.add("地铁四号线");
        list.add("地铁六号线");
        list.add("地铁十三号线");
        list.add("地铁二十一号线");
        list.add("地铁广佛线");
        list.add("科学城");
        list.add("大学城");
        list.add("萝岗");
        list.add("增城");
        list.add("花都");
        list.add("广州南站");
        list.add("广州东站");
        list.add("低涌");
        list.add("大沙地");
        list.add("猎德");
        list.add("珠村");

        for (int i = 0; i < list.size(); i++) {
            historyDataList.add(new HistoryData(list.get(i)));

        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_select:
                startActivity(new Intent(SelectActivity.this, ConditionSelectActivity.class));
                break;

            case R.id.tv_clean:
                historyDataList.clear();
                break;
        }
    }
}
