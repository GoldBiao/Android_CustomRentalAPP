package com.keysu.customizedrental.entity;

public class HistoryData {

    private String data;

    public HistoryData(String data){
        this.data = data;
    }

    public String getData() {
        return "  "+data+"  ";
    }
}
