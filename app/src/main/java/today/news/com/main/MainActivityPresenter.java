package today.news.com.main;

import android.support.v4.app.Fragment;

import today.news.com.R;
import today.news.com.main.beijing.BeiJingFragment;
import today.news.com.main.hangzhou.HangZhouFragment;
import today.news.com.main.shanghai.ShangHaiFragment;
import today.news.com.main.shenzhen.ShenZhenFragment;
import today.news.com.mvp.base.BaseMvpPresenter;

public class MainActivityPresenter extends BaseMvpPresenter<IMainActivityContract.Iview> implements IMainActivityContract.IPresenter {
    
    
    //当前fragment的角标
    private int mCurrentFragmentIndex;
    private Fragment[] mFragments = new Fragment[4];

    private int mTopPosition;
    private int mCurrentCheckedId;
    private int mBottomPositon;

    public MainActivityPresenter(IMainActivityContract.Iview view) {
        super(view);
    }

    @Override
    public IMainActivityContract.Iview getEmptyView() {
        return IMainActivityContract.emptyView;
    }

    @Override
    public void initHomeFragment() {
        mCurrentFragmentIndex = 0;
        replaceFragment(mCurrentFragmentIndex);
    }



    //切换Fragment的方法
    public void replaceFragment(int mCurrentFragmentIndex) {
        for (int i = 0; i <mFragments.length ; i++) {
            if (mCurrentFragmentIndex != i){
                if (mFragments[i] != null){
                    hideFragment(mFragments[i]);
                }
            }
        }
        Fragment mFragment = mFragments[mCurrentFragmentIndex];
        if (mFragment != null){
            addAndShowFragment(mFragment);
            setCurchecked(mCurrentFragmentIndex);
        }else {
            newCurrentFragment(mCurrentFragmentIndex);
            setCurchecked(mCurrentFragmentIndex);
        }
    }
    @Override
    public int getCurrentCheckedIndex() {
        return mCurrentFragmentIndex;
    }

    @Override
    public int getCurrentCheckedId() {
        return mCurrentCheckedId;
    }

    @Override
    public int getTopPosition() {
        return mTopPosition;
    }

    @Override
    public int getBottomPosition() {
        return mBottomPositon;
    }


    //记录当前角标
    private void setCurchecked(int mCurrentFragmentIndex) {
        this.mCurrentFragmentIndex = mCurrentFragmentIndex;
        switch (mCurrentFragmentIndex){
            case 0:
                mCurrentCheckedId = R.id.rb_main_shanghai;
                mTopPosition = 0;
                break;
            case 1:
                mCurrentCheckedId = R.id.rb_main_hangzhou;
                mTopPosition = 1;
                break;
            case 2:
                mCurrentCheckedId = R.id.rb_main_bejing;
                mBottomPositon = 2;
                break;
            case 3:
                mCurrentCheckedId = R.id.rb_main_shenzhen;
                mBottomPositon = 3;
                break;
        }
    }

    //创建当前fragment
    private void newCurrentFragment(int mCurrentFragmentIndex) {
       Fragment fragment = null;
        switch (mCurrentFragmentIndex){
            case 0:
                fragment = new ShangHaiFragment();
                break;
            case 1:
                fragment = new HangZhouFragment();
                break;
            case 2:
                fragment = new BeiJingFragment();
                break;
            case 3:
                fragment = new ShenZhenFragment();
                break;
        }
        mFragments[mCurrentFragmentIndex] = fragment;
        addAndShowFragment(fragment);
    }

    //显示fragment
    private void addAndShowFragment(Fragment mFragment) {
        if (mFragment.isAdded()){
            getView().showFragment(mFragment);
        }else {
            getView().addFragment(mFragment);
        }

    }

    //隐藏fragment
    private void hideFragment(Fragment mFragment) {
        if (mFragment != null && mFragment.isVisible()){
            getView().hideFragment(mFragment);
        }
    }
}
