package today.news.com.main.shanghai.lf;

import today.news.mvp.mvp.ILifeCircle;
import today.news.mvp.mvp.IMvpView;
import today.news.mvp.mvp.MvpControler;

public interface IShangHaiDetailContract {

    interface Iview extends IMvpView {


       
    }

    interface IPresenter extends ILifeCircle {


        void getNetData();
    }
    IShangHaiDetailContract.Iview emptyView = new IShangHaiDetailContract.Iview() {


        

        @Override
        public MvpControler getMvpControler() {
            return null;
        }
    };
}
