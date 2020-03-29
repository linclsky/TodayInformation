package today.news.mvp.mvp.presenter;

import java.lang.ref.WeakReference;

import today.news.mvp.mvp.ILifeCircle;
import today.news.mvp.mvp.IMvpView;
import today.news.mvp.mvp.MvpControler;

public abstract class LifeCircleMvpPresenter<T extends IMvpView> implements ILifeCircle {
    protected WeakReference<T> mWeakReference;

    protected LifeCircleMvpPresenter(){
        super();

    }
    public LifeCircleMvpPresenter(IMvpView iMvpView){
        super();
        attachView(iMvpView);
        MvpControler mvpControler = iMvpView.getMvpControler();
        mvpControler.savePresenter(this);
    }

    @Override
    public void attachView(IMvpView iMvpView) {
        if (mWeakReference == null){
            mWeakReference = new WeakReference(iMvpView);
        }else {
            T view = (T) mWeakReference.get();
            if (view != iMvpView){
                mWeakReference = new WeakReference(iMvpView);
            }
        }

    }

    @Override
    public void onDestroy() {
        mWeakReference = null;
    }

    protected T getView(){
        T view = mWeakReference != null ? (T) mWeakReference.get() : null;

        if (view == null){
            return getEmptyView();
        }
        return view;
    }

    protected abstract T getEmptyView();


}
