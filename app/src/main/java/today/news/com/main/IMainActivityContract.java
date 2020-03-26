package today.news.com.main;

import android.support.v4.app.Fragment;

import today.news.com.mvp.ILifeCircle;
import today.news.com.mvp.IMvpView;
import today.news.com.mvp.MvpControler;

public interface IMainActivityContract {

    interface Iview extends IMvpView {


        void showFragment(Fragment mFragment);

        void addFragment(Fragment mFragment);

        void hideFragment(Fragment mFragment);
    }

    interface IPresenter extends ILifeCircle {


        void initHomeFragment();
        void replaceFragment(int mCurrentFragmentIndex);
        int getCurrentCheckedIndex();

        int getCurrentCheckedId();
        int getTopPosition();
        int getBottomPosition();
    }
    Iview emptyView = new Iview() {


        @Override
        public void showFragment(Fragment mFragment) {

        }

        @Override
        public void addFragment(Fragment mFragment) {

        }

        @Override
        public void hideFragment(Fragment mFragment) {

        }

        @Override
        public MvpControler getMvpControler() {
            return null;
        }
    };
}
