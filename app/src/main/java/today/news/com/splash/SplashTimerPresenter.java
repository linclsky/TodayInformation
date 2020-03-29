package today.news.com.splash;

import today.news.mvp.mvp.base.BaseMvpPresenter;

public class SplashTimerPresenter extends BaseMvpPresenter<ISplashActivityContract.Iview> implements ISplashActivityContract.IPresenter {

    private CustomCountDowmTimer mTimer;

    public SplashTimerPresenter(ISplashActivityContract.Iview view) {
        super(view);
    }


    public void initTimer() {
        mTimer = new CustomCountDowmTimer(1, new CustomCountDowmTimer.ICountDownHandler() {
            @Override
            public void onTicker(int time) {
               getView().setTvTimer(time + "秒");


            }

            @Override
            public void onFinish() {
                getView().setTvTimer("跳过");

            }
        });
        mTimer.start();
    }

    public void cancel() {
        mTimer.cancel();

    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        cancel();

    }
/*
* 防止空指针针异常
*/
    @Override
    protected ISplashActivityContract.Iview getEmptyView() {
        return ISplashActivityContract.emptyView;
    }
}
