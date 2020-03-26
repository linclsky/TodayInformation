package today.news.com.splash;

import today.news.com.mvp.ILifeCircle;
import today.news.com.mvp.IMvpView;
import today.news.com.mvp.MvpControler;

public interface ISplashActivityContract {

    interface Iview extends IMvpView {
        void setTvTimer(String timer);

    }

    interface IPresenter extends ILifeCircle {
        void initTimer();

    }
    Iview emptyView = new Iview() {
        @Override
        public void setTvTimer(String timer) {

        }

        @Override
        public MvpControler getMvpControler() {
            return null;
        }
    };
}
