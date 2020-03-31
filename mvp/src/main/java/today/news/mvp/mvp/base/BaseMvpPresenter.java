package today.news.mvp.mvp.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import today.news.mvp.mvp.IMvpView;
import today.news.mvp.mvp.presenter.LifeCircleMvpPresenter;
import today.news.task.LfTask;

/*
* p层中间类
*
* */
public abstract class BaseMvpPresenter<T extends IMvpView> extends LifeCircleMvpPresenter<T> {
    public BaseMvpPresenter(T view) {
        super(view);
    }

    @Override
    public void onCreate(Bundle savedInstanceState, Intent intent, Bundle getArguments) {

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState, Intent intent, Bundle getArguments) {

    }

    @Override
    public void onStatr() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void destroyView() {

    }

    @Override
    public void onViewDestroy() {

    }

    @Override
    public void onNewIntent(Intent intent) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {

    }


}
