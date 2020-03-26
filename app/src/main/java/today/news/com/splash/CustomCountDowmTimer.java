package today.news.com.splash;


import android.os.Handler;

public class CustomCountDowmTimer implements Runnable{
    private  int time;
    private final ICountDownHandler countDownHandler;
    private final Handler mHandler;
    private boolean isRun;
    private int countDowntime;


    //1,时时去回调，这个时候是什么时间，倒计时到几秒 ,观察者设计模式
    //2,支持动态传入总时间
    //3,每过一秒，总秒数减一
    //总时间倒计时为0时，要回调完成的状态


    public CustomCountDowmTimer(int time, ICountDownHandler countDownHandler) {
        mHandler = new Handler();
        this.time = time;
        this.countDowntime = time;
        this.countDownHandler = countDownHandler;

    }
//实现的具体逻辑
    @Override
    public void run() {
        if (isRun){
            if (countDownHandler != null){
                countDownHandler.onTicker(countDowntime);
            }
            if (countDowntime == 0){
                cancel();
                countDownHandler.onFinish();
            }
            else {
                countDowntime =  time--;
                mHandler.postDelayed(this,1000);
            }
        }

    }
    //开启倒计时
    public void start(){
        isRun = true;
        mHandler.post(this);
    }
    //跳出循环 终止
    public void cancel(){
        isRun = false;
        mHandler.removeCallbacks(this);
    }

//观察者回调接口 （IOC 数据回调）
    public interface ICountDownHandler{
        //倒计时回调
        void  onTicker(int time);
        //完成时回调
        void onFinish();
    }
}
