package com.guigu.mytime.Store;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.guigu.mytime.R;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;


/**
 * Created by Administrator on 2016/2/29.
 */
public class StoreReflashListView extends ListView {

    @ViewInject(R.id.store_reflash_foot_container)
    private FrameLayout store_reflash_foot_container;

    @ViewInject(R.id.ll_reflash_store_indicator)
    private LinearLayout ll_reflash_store_indicator;

    @ViewInject(R.id.iv_reflash_arrow_store)
    private ImageView iv_reflash_arrow_store;


    @ViewInject(R.id.tv_reflash_text_store)
    private TextView tv_reflash_text_store;

    @ViewInject(R.id.tv_reflash_tips_store)
    private TextView tv_reflash_tips_store;

    /**
     * 下拉刷新的高度
     */
    private int mReflashHeight;


    /**
     * 三种状态
     */
    public static final int MODE_PULL_REFLESH = 1;
    public static final int MODE_RELEASH_REFLESH = 2;
    public static final int MODE_REFLESHING = 3;

    private int currMode = MODE_PULL_REFLESH;
    private Context mContext;
    private Animation mPullModeAnimation;
    private Animation mReleaseModeAnimation;
    private RotateAnimation mReflashingAnimation;
    private StoreListFooter mFootView;


    public StoreReflashListView(Context context) {
        super(context);
        init(context);
    }

    public StoreReflashListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public StoreReflashListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    /**
     * 加载视图
     *
     * @param context 上下文
     */
    private void init(Context context) {
        mContext = context;

        View view = View.inflate(context, R.layout.store_reflash_listview, null);
        x.view().inject(this, view);
        addFooterView(view);
        ll_reflash_store_indicator.measure(0, 0);
        mReflashHeight = ll_reflash_store_indicator.getMeasuredHeight();
        Log.e("TAG", "mReflashHeight = " + mReflashHeight);
        setPadding(mReflashHeight);

        initAnimation();
    }

    private void setPadding(int height) {
        ll_reflash_store_indicator.setPadding(10, 10, 10, 10 - height);
    }

    /**
     * 添加第二个底部视图
     *
     * @param view      需要添加到底部的视图
     * @param mListFoot
     */
    public void addSecondaryFootView(View view, StoreListFooter mListFoot) {
        mFootView = mListFoot;
        store_reflash_foot_container.addView(view);
    }

    private int downY = -1;

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (!isEnabled()) {
            return isClickable() || isLongClickable();
        }
        int eventY = (int) ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (downY == -1)
                    downY = eventY;
                break;
            case MotionEvent.ACTION_MOVE:
                if (downY == -1)
                    downY = eventY;

                //需要判断最后一个视图已经完全显示了
                if (noMorePages) {
                    return super.onTouchEvent(ev);
                }
                boolean isBottom = isBottom();
                if (!isBottom) {
                    return super.onTouchEvent(ev);
                }

                if (currMode == MODE_REFLESHING) {
                    return super.onTouchEvent(ev);
                }

                int totalDY = downY - eventY;//向上拉大于0
                Log.e("TAG", "totalDY = " + totalDY);
                if (totalDY > 0) {
                    int totalPadding = mReflashHeight - totalDY;
                    if (currMode != MODE_PULL_REFLESH && totalPadding > 0) {
                        currMode = MODE_PULL_REFLESH;
                        reflashListView();
                    } else if (currMode != MODE_RELEASH_REFLESH && totalPadding <= 0) {
                        currMode = MODE_RELEASH_REFLESH;
                        reflashListView();
                    }
                    setPadding(totalPadding);
                }
                break;
            case MotionEvent.ACTION_UP:
                if (currMode == MODE_PULL_REFLESH) {
                    setPadding(mReflashHeight);
                } else if (currMode == MODE_RELEASH_REFLESH) {
                    currMode = MODE_REFLESHING;
                    setPadding(10);//全显示
                    reflashListView();
                    if (mOnPullToReflash != null) {
                        mOnPullToReflash.onReflash();
                    }
                }
                break;
            default:
                break;
        }
        return super.onTouchEvent(ev);
    }

    /**
     * 判断是否已经到底部了
     *
     * @return
     */
    private boolean isBottom() {
//        int footViewCoordinateY = mFootView.gridViewBottomCoordinateY();
        int[] location = new int[2];
        tv_reflash_tips_store.getLocationOnScreen(location);
        int tipsHightY = location[1];

        getLocationOnScreen(location);
        int listViewBottomY = getHeight() + location[1];
        Log.e("TAG", "listViewBottomY+5 = " + listViewBottomY + ",tipsHightY" + tipsHightY);
        return tipsHightY != 0 && listViewBottomY >= tipsHightY;
    }


    public void setmOnPullToReflash(OnPullToReflash mOnPullToReflash) {
        this.mOnPullToReflash = mOnPullToReflash;
    }

    private OnPullToReflash mOnPullToReflash;

    public void initAnimation() {
        mPullModeAnimation = new RotateAnimation(180, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        mPullModeAnimation.setDuration(500);
        mPullModeAnimation.setFillAfter(true);

        mReleaseModeAnimation = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        mReleaseModeAnimation.setDuration(500);
        mReleaseModeAnimation.setFillAfter(true);

        mReflashingAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        mReflashingAnimation.setDuration(500);
        mReflashingAnimation.setRepeatCount(Animation.INFINITE);
        mReflashingAnimation.setRepeatMode(Animation.INFINITE);
        mReflashingAnimation.setInterpolator(new LinearInterpolator());
    }

    private boolean noMorePages = false;

    /**
     * 更新完成
     */
    public void finishLoadMore(boolean noMore) {
        if (noMore) {
            noMorePages = noMore;
            tv_reflash_tips_store.setText("貌似已经没有了！");
        }
        currMode = MODE_PULL_REFLESH;
        iv_reflash_arrow_store.clearAnimation();
        iv_reflash_arrow_store.setImageResource(R.drawable.default_ptr_flip);
        setPadding(mReflashHeight);
    }

    /**
     * 更新ListView
     */
    private void reflashListView() {//更新视图
        switch (currMode) {
            case MODE_PULL_REFLESH:
                //文字 和 图片的动画
                tv_reflash_text_store.setText("下拉刷新...");
                iv_reflash_arrow_store.setImageResource(R.drawable.default_ptr_flip);
                iv_reflash_arrow_store.startAnimation(mPullModeAnimation);
                break;
            case MODE_RELEASH_REFLESH:
                tv_reflash_text_store.setText("释放刷新...");
                iv_reflash_arrow_store.setImageResource(R.drawable.default_ptr_flip);
                iv_reflash_arrow_store.startAnimation(mReleaseModeAnimation);
                break;
            case MODE_REFLESHING:
                tv_reflash_text_store.setText("奋力加载中...");
                iv_reflash_arrow_store.setImageResource(R.drawable.default_ptr_rotate);
                iv_reflash_arrow_store.startAnimation(mReflashingAnimation);
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (downY == -1 && ev.getAction() == MotionEvent.ACTION_DOWN) {
            downY = (int) ev.getY();
        }
        return super.onInterceptTouchEvent(ev);
    }

    public interface OnPullToReflash {
        void onReflash();
    }

    @Override
    public boolean removeFooterView(View v) {
        store_reflash_foot_container.removeAllViews();
        return super.removeFooterView(v);
    }
}
