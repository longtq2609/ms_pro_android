package com.example.ms_pro.base.event;

public class SelectCurrentTabBottomEvent {

    public int position;
    public Integer scrollToView;

    public SelectCurrentTabBottomEvent(int position) {
        this.position = position;
    }

    public SelectCurrentTabBottomEvent(int position, int scrollToView) {
        this.position = position;
        this.scrollToView = scrollToView;
    }
}
