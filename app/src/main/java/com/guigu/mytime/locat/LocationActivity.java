package com.guigu.mytime.locat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.baidu.location.BDLocation;
import com.google.gson.Gson;
import com.guigu.mytime.MainActivity;
import com.guigu.mytime.R;
import com.guigu.mytime.buyticket.utils.LocationUtil;
import com.guigu.mytime.locat.adapter.GridCityAdaper;
import com.guigu.mytime.locat.adapter.GridItemAdapter;
import com.guigu.mytime.locat.adapter.ListCityAdapter;
import com.guigu.mytime.locat.adapter.SortAdapter;
import com.guigu.mytime.locat.bean.LoactionCityBean;
import com.guigu.mytime.locat.sortlist.CharacterParser;
import com.guigu.mytime.locat.sortlist.ClearEditText;
import com.guigu.mytime.locat.sortlist.PinyinComparator;
import com.guigu.mytime.locat.sortlist.SortModel;
import com.guigu.mytime.utils.CacheUtils;
import com.guigu.mytime.utils.ConstantUtils;
import com.guigu.mytime.utils.SpUtils;
import com.guigu.mytime.volley.VolleyManager;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LocationActivity extends Activity implements View.OnClickListener {

    private static final String TAG = LocationActivity.class.getSimpleName();
    public static final String CITY_NAME = "city_name";
    public static final String CITY_ID = "city_id";

    @ViewInject(R.id.et_location_city_input)
    private EditText et_location_city_input;

    @ViewInject(R.id.btn_location_cancel)
    private Button btn_location_cancel;

    @ViewInject(R.id.ib_location_back)
    private ImageButton ib_location_back;

    private GridCityAdaper mGridCityAdaper;

    @ViewInject(R.id.gv_city_default)
    private GridView gv_city_default;

    @ViewInject(R.id.lv_city_default)
    private ListView lv_city_default;


    private GridView gv_hot_city;

    @ViewInject(R.id.ll_default_pager)
    private LinearLayout ll_default_pager;

    @ViewInject(R.id.ll_city_ordered_page)
    private LinearLayout ll_city_ordered_page;

    /**
     * 城市列表
     */
    private List<LoactionCityBean.City> citys;
    private GridItemAdapter gridItemAdapter;
    private ListCityAdapter mListCityAdapter;
    private TextView tv_get_location;
    public static Activity locationActivity;
    private LocationUtil locationUtil;
    private String currentCity;
    private LocationUtil.OnReceiveCityNameListener mOnReceiveCityNameListener = new LocationUtil.OnReceiveCityNameListener() {
        @Override
        public void OnReceiveCityName(BDLocation bDLocation) {
            currentCity = bDLocation.getCity();
            SpUtils.saveString(LocationActivity.this, SpUtils.CURRENT_LOCATION_LONGITUDE, bDLocation.getLongitude() + "");
            SpUtils.saveString(LocationActivity.this, SpUtils.CURRENT_LOCATION_LATITUDE, bDLocation.getLatitude() + "");
            Log.e("TAG", "currentCity==" + currentCity);
            //设置当前城市
            if (!"".equals(currentCity)) {//当前currentCity=""
                if (currentCity.endsWith("市")) {
                    currentCity = currentCity.substring(0, currentCity.length() - 1);
                }
                Log.e("TAG", "bDLocation.getCity() ==" + bDLocation.getCity());
                tv_get_location.setText(currentCity);
                SpUtils.saveString(LocationActivity.this, SpUtils.CURRENT_LOCATION_CITYNAME, bDLocation.getCity() + "");
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        locationActivity = this;
        x.view().inject(this);

        getCityDataFromNet();

        setOnClickListener();

        locationUtil = new LocationUtil(locationActivity);
        locationUtil.setOnReceiveCityNameListener(mOnReceiveCityNameListener);

        View ll_top_default = View.inflate(this, R.layout.city_layout_default, null);//必须加载布局才能查找里边的视图
        gv_hot_city = (GridView) ll_top_default.findViewById(R.id.gv_hot_city);
        tv_get_location = (TextView) ll_top_default.findViewById(R.id.tv_get_location);
        //定位获取数据
        //http://blog.csdn.net/djun100/article/details/10950681 代码来源网址
        //setLocationByGPS();
//        Intent intent = new Intent(this,LocationMainActivity.class);
//        startActivity(intent);

        lv_city_default.addHeaderView(ll_top_default);
    }

    ///////////通过自动获取数据定位/////////////////////////////////////////////////////
//    public MyBaiduLotion myLotion;
//    public MyLocation myLocation;
//    String strlocation = "";
//
//    /**
//     * 通过定位获取数据
//     */
//    private void setLocationByGPS() {
//        myLotion = new MyBaiduLotion(LocationActivity.this);
//        myLocation = new MyLocation();
//        myLotion.opetateClient();
//        Log.e("aaa", "setLocationByGPS: 被调用了");
//        new LocationThread().start();
//
//    }
//
//    class LocationThread extends Thread {
//
//        @Override
//        public void run() {
//            // TODO Auto-generated method stub
//            super.run();
//            if (myLotion != null)
//                while (!myLotion.getIsFinish()) {
//                    try {
//                        sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            Log.e("aaa", "run000: 被调用了");
//            Log.e(TAG, "run: 1111" + myLotion.myBDcoordinate);
//            if (myLotion.myBDcoordinate != null) {
//                Log.e("aaa", "run: 被调用了");
//                strlocation = myLocation.getAddress(myLotion.getLatValue() + "", myLotion.getLongValue() + "");
//                myHandler.sendEmptyMessage(1);
//            }
//
//        }
//    }
//
//    Handler myHandler = new Handler() {
//
//        @Override
//        public void handleMessage(Message msg) {
//
//            super.handleMessage(msg);
//            Log.e(TAG, "handleMessage: " + strlocation);
//            tv_get_location.setText(strlocation);//设置获取的城市数据
//        }
//
//    };
    //////////通过自动获取数据定位/////////////////////////////////////////////////////

    /**
     * 网络获取城市数据
     */
    private void getCityDataFromNet() {

        StringRequest cityDataRequest = new StringRequest(Request.Method.GET, ConstantUtils.CITY_LOCATION_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Log.e(TAG, "城市页面加载成功" + s);
                processCityData(s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e(TAG, "城市页面联网失败......" + volleyError);
            }
        }) {
            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                try {
                    String jsonData = new String(response.data, "UTF-8");
                    return Response.success(jsonData, HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return super.parseNetworkResponse(response);
            }
        };
        VolleyManager.getRequestQueue().add(cityDataRequest);
    }

    /**
     * 解析和处理城市定位得到的数据
     */
    private List<LoactionCityBean.City> hotCitys;

    private void processCityData(String json) {
        Gson gson = new Gson();
        final LoactionCityBean loactionCityBean = gson.fromJson(json, LoactionCityBean.class);

        Log.e(TAG, "解析得到的数据为" + loactionCityBean.toString());

        citys = new ArrayList<>();
        citys = loactionCityBean.getP();

        //  默认界面
        hotCitys = new ArrayList<>();
        hotCitys = citys.subList(0, 12);
        Log.e(TAG, "热门城市数据: " + hotCitys.size() + hotCitys.toString());
        gridItemAdapter = new GridItemAdapter(this, hotCitys);
        gv_hot_city.setAdapter(gridItemAdapter);
        gv_hot_city.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                String cityName = hotCitys.get(position).getN();
                int cityId = hotCitys.get(position).getId();
                SpUtils.putString(LocationActivity.this, CITY_NAME, cityName);
                SpUtils.putInt(LocationActivity.this, CITY_ID, cityId);

                intent.putExtra(CITY_NAME, cityName);
                intent.putExtra(CITY_ID, cityId);

                intent.setAction(LOCATION_ACTION);
                sendBroadcast(intent);

                boolean isLocation = CacheUtils.getBoolean(LocationActivity.this, "isLocation");
                if(!isLocation) {
                    startActivity(new Intent(LocationActivity.this, MainActivity.class));
                }
                LocationActivity.this.finish();
            }
        });
        //按字母搜索名字
        searchName();
    }

    /**
     * 设置监听
     */
    private void setOnClickListener() {
        et_location_city_input.setOnClickListener(this);
        btn_location_cancel.setOnClickListener(this);
        ib_location_back.setOnClickListener(this);
    }

    /**
     * 点击事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_location_back:
                boolean isLocation = CacheUtils.getBoolean(LocationActivity.this, "isLocation");
                if(!isLocation) {
                    startActivity(new Intent(LocationActivity.this, MainActivity.class));
                }
                finish();
                break;
            case R.id.et_location_city_input:
                btn_location_cancel.setVisibility(View.VISIBLE);

                ll_city_ordered_page.setVisibility(View.VISIBLE);
                ll_default_pager.setVisibility(View.GONE);
                break;
            case R.id.btn_location_cancel:
                btn_location_cancel.setVisibility(View.GONE);

                ll_city_ordered_page.setVisibility(View.GONE);
                ll_default_pager.setVisibility(View.VISIBLE);
                break;
        }
    }

    /**
     * 汉字转换成拼音的类
     */
    private CharacterParser characterParser;
    private List<SortModel> SourceDateList;
    private List<SortModel> gSourceDateList;


    /**
     * 根据拼音来排列ListView里面的数据类
     */
    private PinyinComparator pinyinComparator;

    private ListView sortListView;

    private SortAdapter sortAdapter;

    private ClearEditText mClearEditText;

    public static final String LOCATION_ACTION = "com.atguigu.location";

    /**
     * 搜索名字
     */
    public void searchName() {

        //实例化汉字转拼音类
        characterParser = CharacterParser.getInstance();
        pinyinComparator = new PinyinComparator();

        sortListView = (ListView) findViewById(R.id.lv_city_orderd_first_letter);

        sortListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //这里要利用adapter.getItem(position)来获取当前position所对应的对象
                String cityName = ((SortModel) sortAdapter.getItem(position)).getName();
                int cityId = ((SortModel) sortAdapter.getItem(position)).getId();
                SpUtils.putString(LocationActivity.this, CITY_NAME, cityName);
                SpUtils.putInt(LocationActivity.this, CITY_ID, cityId);

                Intent intent = new Intent();
                intent.setAction(LOCATION_ACTION);
                intent.putExtra(CITY_NAME, cityName);
                intent.putExtra(CITY_ID, cityId);
                sendBroadcast(intent);

                boolean isLocation = CacheUtils.getBoolean(LocationActivity.this, "isLocation");
                if(!isLocation) {
                    startActivity(new Intent(LocationActivity.this, MainActivity.class));
                }
                finish();
            }
        });

        SourceDateList = filledData();

        gSourceDateList = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            char letter = (char) ('A' + i);
            gSourceDateList.add(new SortModel(-i, letter + "", letter + ""));
        }
        gSourceDateList.addAll(SourceDateList);

        // 根据a-z进行排序源数据
        Collections.sort(SourceDateList, pinyinComparator);
        Collections.sort(gSourceDateList, pinyinComparator);
        ArrayList<Integer> PosRec = new ArrayList<>();
        for (int i = 0; i < gSourceDateList.size(); i++) {
            if (gSourceDateList.get(i).getId() <= 0) {
                PosRec.add(i);
                Log.e(TAG, "记录个数为" + PosRec.size());
            }
        }
        for (int i = 0; i < PosRec.size(); i++) {
            Log.e(TAG, "记录位置为----------" + PosRec.get(i));
        }
        mListCityAdapter = new ListCityAdapter(this, gSourceDateList, PosRec);
        lv_city_default.setAdapter(mListCityAdapter);

//        mGridCityAdaper = new GridCityAdaper(this,gSourceDateList);
//        gv_city_default.setAdapter(mGridCityAdaper);


        //查询界面的listview
        sortAdapter = new SortAdapter(this, SourceDateList);
        sortListView.setAdapter(sortAdapter);

        mClearEditText = (ClearEditText) findViewById(R.id.et_location_city_input);
        ///////////////要///////////////
        //根据输入框输入值的改变来过滤搜索
        mClearEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //当输入框里面的值为空，更新为原来的列表，否则为过滤数据列表
                filterData(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

    }

    /**
     * 为ListView填充数据 ///////////////要///////////////
     *
     * @param
     * @return
     */
    private List<SortModel> filledData() {
        List<SortModel> mSortList = new ArrayList<SortModel>();

        for (int i = 0; i < citys.size(); i++) {
            SortModel sortModel = new SortModel();
            sortModel.setSortLetters(citys.get(i).getPinyinShort());
            sortModel.setName(citys.get(i).getN());
            sortModel.setId(citys.get(i).getId());
            //汉字转换成拼音
            String pinyin = characterParser.getSelling(citys.get(i).getN());
            String sortString = pinyin.substring(0, 1).toUpperCase();

            // 正则表达式，判断首字母是否是英文字母
            if (sortString.matches("[A-Z]")) {
                sortModel.setSortLetters(sortString.toUpperCase());
            } else {
                sortModel.setSortLetters("#");
            }

            mSortList.add(sortModel);
        }

        return mSortList;
    }

    /**
     * 根据输入框中的值来过滤数据并更新ListView
     *
     * @param filterStr
     */
    private void filterData(String filterStr) {
        List<SortModel> filterDateList = new ArrayList<SortModel>();

        if (TextUtils.isEmpty(filterStr)) {
            filterDateList = SourceDateList;
        } else {
            filterDateList.clear();
            for (SortModel sortModel : SourceDateList) {
                String name = sortModel.getName();
                if (name.indexOf(filterStr.toString()) != -1 || characterParser.getSelling(name).startsWith(filterStr.toString())) {
                    filterDateList.add(sortModel);
                }
            }
        }

        // 根据a-z进行排序
        Collections.sort(filterDateList, pinyinComparator);
        sortAdapter.updateListView(filterDateList);
    }
}
