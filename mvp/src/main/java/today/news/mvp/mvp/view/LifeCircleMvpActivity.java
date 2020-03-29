package today.news.mvp.mvp.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import today.news.mvp.mvp.IMvpView;
import today.news.mvp.mvp.MvpControler;

public class LifeCircleMvpActivity extends AppCompatActivity implements IMvpView {
    private MvpControler mvpControler;

    @Override
    public MvpControler getMvpControler() {

        if (this.mvpControler == null){
            this.mvpControler = new MvpControler();
        }
        return this.mvpControler;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = this.getIntent();
        if (intent == null){
            intent = new Intent();
        }
        MvpControler mvpControler = this.getMvpControler();
        if (mvpControler != null){
            mvpControler.onCreate(savedInstanceState,intent,null);
            mvpControler.onActivityCreated(savedInstanceState,intent,null);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        MvpControler mvpControler = this.getMvpControler();
         if (mvpControler != null){
             mvpControler.onNewIntent(intent);
         }
    }

    @Override
    protected void onStart() {
        super.onStart();
        MvpControler mvpControler = this.getMvpControler();
        if (mvpControler != null){
            mvpControler.onStatr();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        MvpControler mvpControler = this.getMvpControler();
        if (mvpControler != null) {
            mvpControler.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        MvpControler mvpControler = this.getMvpControler();
        if (mvpControler != null) {
            mvpControler.onPause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        MvpControler mvpControler = this.getMvpControler();
        if (mvpControler != null) {
            mvpControler.onStop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MvpControler mvpControler = this.getMvpControler();
        if (mvpControler != null) {
            mvpControler.onDestroy();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        MvpControler mvpControler = this.getMvpControler();
        if (mvpControler != null) {
            mvpControler.onSaveInstanceState(outState);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        MvpControler mvpControler = this.getMvpControler();
        if (mvpControler != null) {
            mvpControler.onActivityResult(requestCode, resultCode, data);
        }
    }
}
