package today.news.refresh;

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
                    LayoutParams layoutParams =(LayoutParams) mHeadView.getLayoutParams();
                    //阻尼效果
                    int topMargin  = (int) Math.min(dy/1.8f + minHeadViewHeight,maxHeadViewHeight );
                    if (topMargin < 0){
                        //提示下拉刷新的一个状态

                    }else if (topMargin >= 0){
                        //提示释放刷新的一个状态
                    }
                    layoutParams.topMargin  = topMargin;
                    mHeadView.setLayoutParams(layoutParams);
                }
                return true;
            case MotionEvent.ACTION_POINTER_UP:
                break;
                default:break;
        }
        return super.onTouchEvent(event);
    }
}
