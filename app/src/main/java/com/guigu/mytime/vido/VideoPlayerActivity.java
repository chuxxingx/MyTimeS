package com.guigu.mytime.vido;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.guigu.mytime.R;
import com.guigu.mytime.base.BaseActivity;
import com.guigu.mytime.buyticket.bean.MediaItem;
import com.guigu.mytime.utils.Utils;
import com.guigu.mytime.view.VideoView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Administrator on 2016/1/22.
 */
public class VideoPlayerActivity extends BaseActivity {
    private VideoView videoplayer;
    private LinearLayout ll_video_loading;
    private LinearLayout ll_top;
    private TextView tv_videoname;
    private ImageView iv_controller_battery;
    private TextView tv_controller_time;
    private ImageView iv_controller_voice;
    private SeekBar sb_controller_voice;
    private ImageView iv_controller_switch;
    private LinearLayout ll_bottom;
    private TextView tv_controller_nowtime;
    private SeekBar sb_controller_progress;
    private TextView tv_controller_duration;
    private ImageView iv_controller_back;
    private ImageView iv_controller_pre;
    private ImageView iv_controller_play;
    private ImageView iv_controller_next;
    private ImageView iv_controller_full;
    private TextView iv_controller_clarity;
    private Uri uri;
    private int position;
    private ArrayList<MediaItem> data;
    private int lastx;
    private int downx;
    private int lasty;
    private float downy;
    private Utils utils = new Utils();
    //时间进度的消息
    private final int Progress=1;
    private final int InVisiable=5;
    private boolean isActivityDestory;
    private MyReceiver receiver;
    private int batteryStatus;
    private int systemTime;
    private GestureDetector detector;
    /**
     * 屏幕的宽和高
     */
    private int screenWidth;
    private int screenHeight;
    /**
     * 真正的视频宽和高
     */
    private int videoWidth;
    private int videoHeight;

    //系统默认的播放尺寸
    private int SCREEN_DEFAULT = 9;
    //全屏
    private int SCREEN_FULL = 10;
    //默认是全屏
    private boolean isScreenFull = true;
    private AudioManager am;
    /**
     * 最大音量
     * 当前的音量
     */
    private int maxVolume;
    private int volume;
    /**
     * 滑动的区域
     */
    private float touchRang;
    private int mVol;
    //滑动后的音量
    private int currentVolume;
    private MediaItem mediaItem;
    private int preCurrentPosition;
    //播放器切换完成
    private final int FINISH = 6;
    private WindowManager wm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //加载视图
        findView();
        //得到数据
        getData();
        //加载数据
        initData();
        //设置监听
        setListener();


    }

    private boolean isNetVideo = false;//判断是否是网络视频，默认不是
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case FINISH:
                    finish();
                    break;
                case InVisiable :
                    ll_bottom.setVisibility(View.GONE);
                    ll_top.setVisibility(View.GONE);
                    break;
                case Progress:
                    //获取当前的播放进度
                    int position = videoplayer.getCurrentPosition();
                    //当前的进度-前一秒的进度<500:则为卡顿，相比系统的监听更加灵敏
                    int buffering = Math.abs(position - preCurrentPosition);
                    if(buffering<500 && videoplayer.isPlaying() &&
                            isNetVideo) {
                        ll_video_loading.setVisibility(View.VISIBLE);
                    }else {
                        ll_video_loading.setVisibility(View.GONE);
                    }
                    //得到上一秒的缓冲进度
                    preCurrentPosition = position;


                    tv_controller_nowtime.setText(utils.stringForTime(position));
                    //seekbar播放进度的改变
                    sb_controller_progress.setProgress(position);

                    ////更新系统时间
                    tv_controller_time.setText(getSystemTime());

                    //设置视频的缓冲
                    //得到当前的缓冲进度
                    if(isNetVideo) {
                        int percentage = videoplayer.getBufferPercentage();//0~100
                        int secondaryProgress = percentage * sb_controller_progress.getMax() / 100;
                        sb_controller_progress.setSecondaryProgress(secondaryProgress);
                    }else {
                        //不是网络的
                        sb_controller_progress.setSecondaryProgress(0);
                    }


                    if(!isActivityDestory) {
                        handler.removeMessages(Progress);
                        handler.sendEmptyMessageDelayed(Progress, 1000);
                    }
                    break;
            }
        }
    };
    @Override
    public boolean onTouchEvent(MotionEvent event) {
       detector.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN :
                downy = event.getY();
                //得到哪个是为高
                touchRang = Math.min(screenWidth, screenHeight);
                //获取当前的音量
                mVol = am.getStreamVolume(AudioManager.STREAM_MUSIC);
                handler.removeMessages(InVisiable);
                break;
            case MotionEvent.ACTION_MOVE :
                float dy = downy- event.getY();

                //计算出要改变的音量值
                float volume1 = (dy / touchRang) * maxVolume;
                Log.e("TAG", "volume1="+volume1);
                //计算最终的声音
                int finalVol = (int) Math.min(Math.max(mVol + volume1, 0), maxVolume);
                Log.e("TAG", "finalVol="+finalVol);
                if(volume1!=0) {
                    updatavolumeProgress(finalVol);
                }
                break;
            case MotionEvent.ACTION_UP :
                handler.sendEmptyMessageDelayed(InVisiable,5000);
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_VOLUME_DOWN) {
            currentVolume--;
            updatavolumeProgress(currentVolume);
            //五秒钟后隐藏控制面板
            handler.removeMessages(InVisiable);
            handler.sendEmptyMessageDelayed(InVisiable,5000);
        }else if(keyCode==KeyEvent.KEYCODE_VOLUME_UP) {
            currentVolume++;
            updatavolumeProgress(currentVolume);
            handler.removeMessages(InVisiable);
            handler.sendEmptyMessageDelayed(InVisiable, 5000);
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     *隐藏控制面板
     */
    private void hideMediaControl(){
        ll_top.setVisibility(View.GONE);
        ll_bottom.setVisibility(View.GONE);
        handler.removeCallbacksAndMessages(null);
        isShowMediaControl=true;
    }
    //判断控制面板是否显示：默认不显示
    private boolean isShowMediaControl =false;
    /**
     *显示控制面板
     */
    private void showMediaControl(){
        ll_top.setVisibility(View.VISIBLE);
        ll_bottom.setVisibility(View.VISIBLE);
        isShowMediaControl=false;
    }

    /**
     * 加载播放的数据和电池广播的注册
     */
    private void initData() {
        //设置控制面板:使用自定义的控制面板
       // videoplayer.setMediaController(new MediaController(this));不使用系统的自定义控制面板
            if(data!=null && data.size()>0) {
                //设置视频播放地址
                mediaItem = data.get(position);
                tv_videoname.setText(mediaItem.getName());
                videoplayer.setVideoPath(mediaItem.getData());
                isNetVideo = utils.isNetUrl(mediaItem.getData());
            }else if(uri!=null) {//来源于网络
                videoplayer.setVideoURI(uri);
                tv_videoname.setText(uri.toString());
                isNetVideo = utils.isNetUrl(uri.toString());
            }

        //获取播放器的尺寸
        wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        //得到屏幕的宽高
        screenWidth = wm.getDefaultDisplay().getWidth();
        screenHeight = wm.getDefaultDisplay().getHeight();

        //获取手机的音量
        am = (AudioManager) getSystemService(AUDIO_SERVICE);
        //当前的音量等级
        volume = am.getStreamVolume(AudioManager.STREAM_MUSIC);
        //获取最大音量
        maxVolume = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        //设置最大音量和当前的音量
        sb_controller_voice.setMax(maxVolume);
        sb_controller_voice.setProgress(volume);

        //手势识别器
        detector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            //单击
            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                if(isShowMediaControl) {
                    showMediaControl();
                }else {
                    hideMediaControl();
                }
                return super.onSingleTapConfirmed(e);
            }
            //双击
            @Override
            public boolean onDoubleTap(MotionEvent e) {
                //改变播放器的尺寸
                switchVideoSize();
                return super.onDoubleTap(e);
            }
            //长按
            @Override
            public void onLongPress(MotionEvent e) {
                //改变播放状态：播放和暂停
                startAndpause();
            }
        });

        //设置按钮的状态
        setButtonStatue();

        //注册手机电量的广播
        IntentFilter filter = new IntentFilter();
        //电量改变的广播接收
        filter.addAction(Intent.ACTION_BATTERY_CHANGED);
        receiver = new MyReceiver();
        registerReceiver(receiver, filter);
    }

    /**
     * 改变屏幕的尺寸
     */

    private void switchVideoSize() {
        if(isScreenFull) {//是全屏的时候，改变成默认的尺寸
            setVideoType(SCREEN_DEFAULT);
        }else{
            setVideoType(SCREEN_FULL);
        }
    }

    /**
     * 设置按钮的状态
     */
    private void setButtonStatue(){
        if(position==0) {
            iv_controller_pre.setClickable(false);
            iv_controller_pre.setBackgroundResource(R.drawable.btn_pre_gray);
        }else if(position==(data.size()-1)) {
            iv_controller_next.setClickable(false);
            iv_controller_next.setBackgroundResource(R.drawable.btn_next_gray);
        }else {
            iv_controller_pre.setBackgroundResource(R.drawable.btn_pre_selector);
            iv_controller_next.setBackgroundResource(R.drawable.btn_mext_selector);
        }
        if(uri!=null) {//来源于网络或者本地的视频资源
            iv_controller_pre.setClickable(false);
            iv_controller_pre.setBackgroundResource(R.drawable.btn_pre_gray);
            iv_controller_next.setClickable(false);
            iv_controller_next.setBackgroundResource(R.drawable.btn_next_gray);
        }
    }
    @Override
    protected void onDestroy() {
        isActivityDestory = true;
        //移除所有消息
        handler.removeCallbacksAndMessages(null);
        if(receiver!=null) {
            unregisterReceiver(receiver);
            receiver = null;
        }
        super.onDestroy();
    }

    private void findView() {
        videoplayer = (VideoView)findViewById(R.id.vv_videoplayer);
        ll_video_loading = (LinearLayout) findViewById(R.id.ll_video_loading);
        ll_top = (LinearLayout)findViewById(R.id.ll_top);
        tv_videoname = (TextView)findViewById(R.id.tv_videoname);
        iv_controller_battery = (ImageView)findViewById(R.id.iv_controller_battery);
        tv_controller_time = (TextView)findViewById(R.id.tv_controller_time);
        iv_controller_voice = (ImageView)findViewById(R.id.iv_controller_voice);
        iv_controller_voice.setOnClickListener(this);
        sb_controller_voice = (SeekBar)findViewById(R.id.sb_controller_voice);

        iv_controller_switch = (ImageView)findViewById(R.id.iv_controller_switch);
        iv_controller_switch.setOnClickListener(this);

        ll_bottom = (LinearLayout)findViewById(R.id.ll_bottom);
        tv_controller_nowtime = (TextView)findViewById(R.id.tv_controller_nowtime);
        sb_controller_progress = (SeekBar)findViewById(R.id.sb_controller_progress);

        tv_controller_duration = (TextView)findViewById(R.id.tv_controller_duration);
        iv_controller_back = (ImageView)findViewById(R.id.iv_controller_back);
        iv_controller_back.setOnClickListener(this);
        iv_controller_pre = (ImageView)findViewById(R.id.iv_controller_pre);
        iv_controller_pre.setOnClickListener(this);
        iv_controller_play = (ImageView)findViewById(R.id.iv_controller_play);
        iv_controller_play.setOnClickListener(this);
        iv_controller_next = (ImageView)findViewById(R.id.iv_controller_next);
        iv_controller_next.setOnClickListener(this);
        iv_controller_full = (ImageView)findViewById(R.id.iv_controller_full);
        iv_controller_full.setOnClickListener(this);
        iv_controller_clarity = (TextView)findViewById(R.id.iv_controller_clarity);
        iv_controller_clarity.setOnClickListener(this);
    }

    /**
     * d按钮的点击监听
     * @param view
     */
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.iv_controller_voice:
                isVolume=!isVolume;
                setVideoVoice();
                break;
            case R.id.iv_controller_back :
                finish();
                break;
            case R.id.iv_controller_pre :
                playPreVideo();
                break;
            case R.id.iv_controller_play :
                startAndpause();
                break;
            case R.id.iv_controller_next :
                playNextVideo();
                break;
            case R.id.iv_controller_full :
                switchVideoSize();
                break;
            case R.id.iv_controller_clarity :
                switchVideoClarity();
                break;
            case R.id.iv_controller_switch:
                switchVideoPlayer();
                break;
        }
    }

    /**
     * 切换播放器
     */
    private void switchVideoPlayer() {
    //从系统播放器切换到万能播放器
    AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("切换播放器")
                .setMessage("当前为系统播放器")
                .setNegativeButton("取消", null)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startVitamioVideoPlayer();
                    }
                })
                .show();


    }


    private boolean isHeightClarity = false;//默认不是高清

    /**
     * 清晰度的切换
     */
    private void switchVideoClarity() {
        if(isNetVideo) {//网络端才可以切换
            ll_video_loading.setVisibility(View.VISIBLE);
            //切换清晰度
            if(!isHeightClarity) {
                //获取高清url
                String heightUrl = mediaItem.getHeightUrl();
                videoplayer.setVideoPath(heightUrl);
                Toast.makeText(this,"切换为高清",Toast.LENGTH_SHORT).show();
                isHeightClarity = true;
                iv_controller_clarity.setText("高清");
            }else{
                //获取标清url
                String uri = mediaItem.getData();
                videoplayer.setVideoPath(uri);
                Toast.makeText(this,"切换为标清",Toast.LENGTH_SHORT).show();
                isHeightClarity = false;
                iv_controller_clarity.setText("标清");
            }
        }
    }

    /**
     * 改变当前的音量
     */
    private boolean isVolume = false;//默认声音
    private void setVideoVoice() {
        if(isVolume) {
            isVolume=true;
            sb_controller_voice.setProgress(0);
            //设置没有音量
            am.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 1);
        }else {
            isVolume=false;
            sb_controller_voice.setProgress(currentVolume);
            am.setStreamVolume(AudioManager.STREAM_MUSIC, currentVolume, 1);
        }
    }

    /**
     * 设置当前的音量
     */
    private void updatavolumeProgress(int vol) {
        sb_controller_voice.setProgress(vol);
        am.setStreamVolume(AudioManager.STREAM_MUSIC, vol, 0);
        currentVolume = vol;
    }

    private void startAndpause(){
        if(videoplayer.isPlaying()) {
            iv_controller_play.setBackgroundResource(R.drawable.btn_play_selector);
            videoplayer.pause();
        }else{
            videoplayer.start();
            iv_controller_play.setBackgroundResource(R.drawable.btn_pause_selector);
        }
    }
    /**
     * 设置监听的方法
     */
    private void setListener() {
        /**
         * 播放进度的seekbar的改变的监听
         */
        sb_controller_progress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            /**
             * 当是手动的拖动改变进度的话fromUser为true；默认为false
             * @param seekBar
             * @param progress
             * @param fromUser
             */
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser) {//如果为true，说明是手动拖动
                    videoplayer.seekTo(progress);
                }
            }
            //当手触摸时
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                handler.removeMessages(InVisiable);
            }
            //手没有默默离开后
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                handler.sendEmptyMessageDelayed(InVisiable,5000);
            }
        });

        sb_controller_voice.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //设置当前的音量
                if(fromUser) {
                    updatavolumeProgress(progress);
                }
            }
                //当手指触摸的时候
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                handler.removeMessages(InVisiable);
            }
            //当手指离开的时候
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                handler.sendEmptyMessageDelayed(InVisiable,5000);
            }
        });

        //设置准备好了的监听
        videoplayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                //获取真正播放的视频宽和高
                videoHeight = mp.getVideoHeight();
                videoWidth = mp.getVideoWidth();
                ll_video_loading.setVisibility(View.GONE);
                //开始播放
                videoplayer.start();

                //获取时长
                int duration = videoplayer.getDuration();
                String time = utils.stringForTime(duration);
                tv_controller_duration.setText(time);
                //设置进度
                sb_controller_progress.setMax(duration);
                //设置时间
                tv_controller_time.setText(getSystemTime());
                handler.sendEmptyMessage(Progress);

            }
        });
        //播放完成监听
        videoplayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                //播放下一个视频
               playNextVideo();
            }
        });
        //播放出错的监听
        videoplayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
               startVitamioVideoPlayer();
                return true;
            }
        });
        //播放卡的监听
        videoplayer.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
//                switch (what) {
//                    case MediaPlayer.MEDIA_INFO_BUFFERING_START ://开始卡顿
//                        ll_video_loading.setVisibility(View.VISIBLE);
//                        break;
//                    case MediaPlayer.MEDIA_INFO_BUFFERING_END ://结束卡顿
//                        ll_video_loading.setVisibility(View.GONE);
//                        break;
//                }

                return true;
            }
        });
    }

    /**
     * 启动万能播放器
     */
    private void startVitamioVideoPlayer() {
        //使用万能播放器
        Intent intent = new Intent(VideoPlayerActivity.this,VitamioVideoPlayerActivity.class);
        if(data != null && data.size()>0) {
            Bundle bundle = new Bundle();
            bundle.putSerializable("videolist", data);//传递集合
            //传递点击的位置
            intent.putExtra("position", position);
            intent.putExtras(bundle);
        }else if(uri != null) {
            intent.setData(uri);
        }
        startActivity(intent);

        handler.sendEmptyMessageDelayed(FINISH,1500);
    }

    /**
     * 播放下一个视频
     */
    private void playNextVideo() {
        if(data!=null&&data.size()>0) {
            position++;
            if (position < data.size()) {
                MediaItem mediaItem = data.get(position);
                videoplayer.setVideoPath(mediaItem.getData());
                //设置文件的名称
                tv_videoname.setText(mediaItem.getName());
                if(position==data.size()-1) {
                    Toast.makeText(VideoPlayerActivity.this, "已是最后一个视频", Toast.LENGTH_SHORT).show();
                }
            } else {
                finish();
            }
        }
    }
    /**
     * 播放上一个视频
     */
    private void playPreVideo() {
        if(data!=null&&data.size()>0) {
            position--;
            if (position >= 0) {
                MediaItem mediaItem = data.get(position);
                videoplayer.setVideoPath(mediaItem.getData());
                //设置文件的名称
                tv_videoname.setText(mediaItem.getName());

            } else {
                position=0;
            }
        }
    }


    @Override
    public View getContentView() {
        return View.inflate(this, R.layout.activity_videoplayer,null);
    }

    public void getData() {
        uri = getIntent().getData();//为null
        Log.e("TAG", "uri="+uri);
        position = getIntent().getIntExtra("position", 0);
        data = (ArrayList<MediaItem>) getIntent().getSerializableExtra("videolist");

    }

    /**
     * 设置电量
     * @param level
     */
    public void setBatteryStatus(int level) {
        if(level <=0){
            iv_controller_battery.setImageResource(R.drawable.ic_battery_0);
        }else if(level <=10){
            iv_controller_battery.setImageResource(R.drawable.ic_battery_10);
        }else if(level <=20){
            iv_controller_battery.setImageResource(R.drawable.ic_battery_20);
        }else if(level <=40){
            iv_controller_battery.setImageResource(R.drawable.ic_battery_40);
        }else if(level <=60){
            iv_controller_battery.setImageResource(R.drawable.ic_battery_60);
        }else if(level <=80){
            iv_controller_battery.setImageResource(R.drawable.ic_battery_80);
        }else if(level <=100){
            iv_controller_battery.setImageResource(R.drawable.ic_battery_100);
        }else{
            iv_controller_battery.setImageResource(R.drawable.ic_battery_100);
        }
    }

    /**
     * 获取系统时间
     * @return
     */
    public String getSystemTime() {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        return format.format(new Date());
    }

    /**
     * 设置播放器的屏幕的类型：全屏和默认
     * @param videoType
     */
    public void setVideoType(int videoType) {
        switch (videoType) {
            case 9 ://默认的类型
                //真正视频的宽和高
                int mVideoWidth = videoWidth;
                int mVideoHeight = videoHeight;
                //屏幕的宽和高
                int height = screenHeight;
                int width = screenWidth;
                if (mVideoWidth > 0 && mVideoHeight > 0) {
                    if (mVideoWidth * height < width * mVideoHeight) {
                        //Log.i("@@@", "image too wide, correcting");
                        width = height * mVideoWidth / mVideoHeight;
                    } else if (mVideoWidth * height > width * mVideoHeight) {
                        //Log.i("@@@", "image too tall, correcting");
                        height = width * mVideoHeight / mVideoWidth;
                    }
                }
                //进行设置
                videoplayer.setVideoSize(width,height);
                isScreenFull=false;//默认
                iv_controller_full.setBackgroundResource(R.drawable.btn_full_screen_selector);
                break;
            case 10 ://全屏的类型
                screenWidth = wm.getDefaultDisplay().getWidth();
                screenHeight = wm.getDefaultDisplay().getHeight();
                videoplayer.setVideoSize(screenWidth,screenHeight);
                isScreenFull=true;//全屏
                iv_controller_full.setBackgroundResource(R.drawable.btn_default_screen_selector);
                break;

        }
    }

    private class MyReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            //获取当前的电量
            int level = intent.getIntExtra("level", 0);
            setBatteryStatus(level);
        }
    }
}
