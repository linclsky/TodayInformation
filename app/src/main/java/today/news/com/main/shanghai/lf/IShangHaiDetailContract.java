package today.news.com.main.shanghai.lf;

import today.news.com.main.shanghai.dto.ShangHaiDetailBean;
import today.news.mvp.mvp.ILifeCircle;
import today.news.mvp.mvp.IMvpView;
import today.news.mvp.mvp.MvpControler;

public interface IShangHaiDetailContract {

    interface Iview extends IMvpView {


        void showData(ShangHaiDetailBean data);
    }

    interface IPresenter extends ILifeCircle {


        void getNetData(int pagesize);
    }
    IShangHaiDetailContract.Iview emptyView = new IShangHaiDetailContract.Iview() {


        @Override
        public void showData(ShangHaiDetailBean data) {

        }

        @Override
        public MvpControler getMvpControler() {
            return null;
        }
    };
}
