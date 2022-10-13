package com.keysu.customizedrental.utils;

import android.widget.Toast;

import com.keysu.customizedrental.Config;

public class ToastUtil {

    public static void show(String msg){
        Toast.makeText(Config.CONTEXT, msg, Toast.LENGTH_SHORT).show();
    }

}
