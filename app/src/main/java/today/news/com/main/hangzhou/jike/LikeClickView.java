package today.news.com.main.hangzhou.jike;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.support.annotation.Nullable;

import today.news.com.R;
import today.news.com.main.tools.SystemUtil;

public class LikeClickView extends View {

    private boolean isLike;
    private Bitmap mLikeBitmap;
    private Bitmap mUnLikeBitmap;
    private Bitmap mShiningBitmap;
    private Paint mBitmapPaint;
    private int mLeft;
    private int mTop;
    private float handScale
            ;
    private float mCeterX;
    private float mCeterY;

    public LikeClickView(Context context) {
       this(context,null,0);
       init();
    }

    public LikeClickView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
        init();
    }

    public LikeClickView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.JiKeLikeView);
        isLike = typedArray.getBoolean(R.styleable.JiKeLikeView_is_like, false);
        typedArray.recycle();
        init();
    }

    private void init() {
        Resources resources = getResources();
        mLikeBitmap = BitmapFactory.decodeResource(resources, R.mipmap.ic_message_like);
        mUnLikeBitmap = BitmapFactory.decodeResource(resources, R.mipmap.ic_message_unlike);
        mShiningBitmap = BitmapFactory.decodeResource(resources, R.mipmap.ic_message_like_shining);
        mBitmapPaint = new Paint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int measureWidth = 0;
        int measureHeight = 0;
        int maxHeight = mUnLikeBitmap.getHeight() + SystemUtil.dp2px(getContext(),20);
        int maxWidth = mUnLikeBitmap.getHeight() + SystemUtil.dp2px(getContext(),30);
        //拿到当前控件的测量模式
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int widthsize = MeasureSpec.getSize(widthMeasureSpec);
        int heightsize = MeasureSpec.getSize(heightMeasureSpec);

        if (mode != MeasureSpec.EXACTLY){
            //测量模式未指定，如果有背景，背景多大，我们这个控件就有多大
            int suggestedMinimumWidth = getSuggestedMinimumWidth();
            int suggestedMinimumHeight = getSuggestedMinimumHeight();
            if (suggestedMinimumWidth == 0){
                measureWidth = maxWidth;
            }else{
                measureWidth = Math.min(suggestedMinimumWidth,maxWidth);
            }
            if (suggestedMinimumHeight == 0){
                measureHeight = maxHeight;
            }else {
                measureHeight = Math.min(suggestedMinimumHeight,maxHeight);
            }
        }else {
            //测量模式指定，根据用户定义大小来判断
            measureWidth = Math.min(maxWidth,widthsize);
            measureHeight = Math.min(maxHeight,heightsize);
        }
        setMeasuredDimension(measureWidth,measureHeight);
        getpading(measureWidth,measureHeight);

    }

    private void getpading(int measureWidth, int measureHeight) {


        int bitmapWidth = mLikeBitmap.getWidth();
        int bitmapHight = mLikeBitmap.getHeight();
        mLeft = (measureWidth - bitmapWidth)/2;
        mTop = (measureHeight - bitmapHight)/2;
        int measuredHeight = getMeasuredHeight();
        int measuredWidth = getMeasuredWidth();
        mCeterX = measuredWidth / 2;
        mCeterY = measuredHeight/2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Bitmap handBitmap = isLike ? mLikeBitmap : mUnLikeBitmap;

        //使用canvas scale及其他的效果方法时，必须先调用save 然后再调用 restore(这两个方法是成对出现的 )
        canvas.save();
        canvas.scale(handScale,handScale,mCeterX,mCeterX);

        canvas.drawBitmap(handBitmap,mLeft,mTop,mBitmapPaint);
        canvas.restore();
        if (isLike){
            canvas.drawBitmap(mShiningBitmap,mLeft + 10,0,mBitmapPaint);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mLikeBitmap.recycle();
        mUnLikeBitmap.recycle();
        mShiningBitmap.recycle();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:

                onClick();
                break;

                default:
                    break;
        }
        return super.onTouchEvent(event);
    }


    private void onClick() {

        isLike =! isLike;
//        ObjectAnimator handScale = ObjectAnimator.ofFloat(this, "handScale", 1.0f, 0.8f, 1.0f);
//        handScale.setDuration(250);
//        handScale.start();
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(1.0f, 0.8f, 1.0f);
        valueAnimator.setDuration(250);
        valueAnimator.start();
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedValue = (float) animation.getAnimatedValue();
                handScale = animatedValue;
                invalidate();
            }
        });


    }

    public void setHandScale(float value){
        this.handScale = value;
        invalidate();
    }
}
