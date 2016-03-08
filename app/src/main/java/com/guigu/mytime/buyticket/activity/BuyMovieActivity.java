package com.guigu.mytime.buyticket.activity;

import android.app.Activity;
import android.os.Bundle;

import com.guigu.mytime.R;
import com.guigu.mytime.buyticket.bean.SeatInfo;

import java.util.ArrayList;

public class BuyMovieActivity extends Activity {
    private static final int ROW = 20;
    private static final int EACH_ROW_COUNT = 30;

    private ArrayList<SeatInfo> list_seatInfos = new ArrayList<SeatInfo>();
    private ArrayList<ArrayList<Integer>> list_seat_conditions = new ArrayList<ArrayList<Integer>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // init();
    }

}

