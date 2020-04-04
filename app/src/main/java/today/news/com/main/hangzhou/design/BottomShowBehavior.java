package today.news.com.main.hangzhou.design;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

public class BottomShowBehavior extends CoordinatorLayout.Behavior<TextView> {
    public BottomShowBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    // 这个方法的回调时机 ： 即将发生嵌套滚动时 nestedScrollAxes  用于判断滑动的方向

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull TextView child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }
    //发生嵌套滚动的时候 回调


    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull TextView child, @NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
        //向下滑动
        if (dyConsumed + dyUnconsumed >0){
            //隐藏child
            if (child.getVisibility() == View.VISIBLE){
                BottomAnim.hide(child);
            }
            //向上滑动
        }else {
            //展示child
            if (child.getVisibility() != View.VISIBLE){
                BottomAnim.show(child);
            }
        }


    }
}
