package today.news.refresh;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class GodRefreshLayout extends LinearLayout {

    private BaseRefreshManager mDefaultRefreshManager;
    private Context mContext;
    private BaseRefreshManager mDefaultRefreshManager1;
    private View mHeadView;
    private int mHeadViewHight;


    private int minHeadViewHeight;// 头部布局最小的一个高度
    private int maxHeadViewHeight;// 头部布局最大的一个高度
    private RefreshingListener mRefreshingListener;//正在刷新回调接口

    public GodRefreshLayout(Context context) {
        super(context);
        initView(context);
    }



    public GodRefreshLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public GodRefreshLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }


    private void initView(Context context) {
        mContext = context;
    }

    /*
    * 开启下拉刷新 下拉刷新的效果是默认的 */
    public void setRefreshManager(){

        mDefaultRefreshManager = new DefaultRefreshManager(mContext);
        intHeaderView();
    }
    //刷新完成后的操作
    public void refreshOver(){
        hideHeadView(getHeadViewLayoutParams());
    }

    public interface RefreshingListener{
        void onRefreshing();
    }
    //自定义回调接口
    public void setRefreshListener(RefreshingListener refreshListener){
        this.mRefreshingListener = refreshListener;
    }

    /*
    * 开启下拉刷新，使用用户自定义的下拉刷新效果*/
    public void setRefreshManager(BaseRefreshManager manager){

        mDefaultRefreshManager = manager;
        intHeaderView();
    }

    private void intHeaderView() {
        setOrientation(VERTICAL);
        mHeadView = mDefaultRefreshManager.getHeaderView();
        mHeadView.measure(0,0);
        mHeadViewHight = mHeadView.getMeasuredHeight();
        minHeadViewHeight =  -mHeadViewHight;
        maxHeadViewHeight = (int) (mHeadViewHight * 0.3f);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mHeadViewHight);
        params.topMargin = minHeadViewHeight;
        addView(mHeadView,0,params);


    }
    private int mDownY;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mDownY = (int) event.getY();
                return true;
            case MotionEvent.ACTION_MOVE:
                int MoveY = (int) event.getY();
                int dy = MoveY - mDownY;
                if (dy > 0){
                    LayoutParams layoutParams = getHeadViewLayoutParams();
                    //阻尼效果
                    int topMargin  = (int) Math.min(dy/1.8f + minHeadViewHeight,maxHeadViewHeight );

                    if (topMargin < 0 && mCurrentRefreshState != RefreshState.DOWNREFRESH){

                        mCurrentRefreshState = RefreshState.DOWNREFRESH;

                        //提示下拉刷新的一个状态
                        handleRefreshState(mCurrentRefreshState);
                    }else if (topMargin >= 0 && mCurrentRefreshState != RefreshState.RELEASEREFRESH){
                        mCurrentRefreshState = RefreshState.RELEASEREFRESH;
                        //提示释放刷新的一个状态
                        handleRefreshState(mCurrentRefreshState);
                    }
                    layoutParams.topMargin  = topMargin;
                    mHeadView.setLayoutParams(layoutParams);
                }
                return true;
            case MotionEvent.ACTION_UP:
                if (handleEventUp(event)){
                    return true;
                }
                break;
                default:break;
        }
        return super.onTouchEvent(event);
    }

    private boolean handleEventUp(MotionEvent event) {
        final LayoutParams layoutParams = getHeadViewLayoutParams();
        if (mCurrentRefreshState == RefreshState.DOWNREFRESH){

            //设置属性动画
            hideHeadView(layoutParams);
        }else if (mCurrentRefreshState == RefreshState.RELEASEREFRESH){
            //保持刷新的一个状态
            layoutParams.topMargin = 0;
            mHeadView.setLayoutParams(layoutParams);
            mCurrentRefreshState = RefreshState.REFRESHING;
            handleRefreshState(mCurrentRefreshState);
            if (mRefreshingListener != null){
                mRefreshingListener.onRefreshing();
            }
        }

        return layoutParams.topMargin > minHeadViewHeight;
    }

    private void hideHeadView(final LayoutParams layoutParams) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(layoutParams.topMargin, minHeadViewHeight);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int  animatedValue = (int) animation.getAnimatedValue();
                layoutParams.topMargin = animatedValue;
                mHeadView.setLayoutParams(layoutParams);

            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mCurrentRefreshState = RefreshState.IDDLE;
                handleRefreshState(mCurrentRefreshState);
            }
        });
        //设置动画停留时间
        valueAnimator.setDuration(500);
        valueAnimator.start();
    }

    private LayoutParams getHeadViewLayoutParams() {
        return (LayoutParams) mHeadView.getLayoutParams();
    }

    private void handleRefreshState(RefreshState currentRefreshState) {
        switch (currentRefreshState){
            case DOWNREFRESH:
                mDefaultRefreshManager.downRefresh();
                break;
            case RELEASEREFRESH:
                mDefaultRefreshManager.releaseRefresh();
                break;
            case IDDLE:
                mDefaultRefreshManager.iddleRefresh();
                break;
            case REFRESHING:
                mDefaultRefreshManager.refreshing();
                break;
                default:
                    break;
        }
    }

    private RefreshState mCurrentRefreshState = RefreshState.IDDLE;
    //定义下拉刷新的状态 ，依次为  静止、下拉刷新、释放刷新、正在刷新、刷新完成
    private enum RefreshState{
        IDDLE, DOWNREFRESH,RELEASEREFRESH,REFRESHING,REFRESHOVER
    }
}
