package today.news.com.main.shanghai.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;

import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import today.news.com.R;
import today.news.com.base.BaseActivity;
import today.news.com.base.ViewInject;

@ViewInject(mainlayoutid = R.layout.activity_shanghai_detail)
public class ShangHaiDetailActivity extends BaseActivity {
    @BindView(R.id.iv_shanghai_detail)
    ImageView mIvShanghaiDetail;
    public static String mActivityOptionsCompat = "ShangHaiDetailActivity";

    @Override
    public void afterBindView() {
        initAnima();

    }

    private void initAnima() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            //设置转场动画
            ViewCompat.setTransitionName(mIvShanghaiDetail,mActivityOptionsCompat);
            //开启转场动画
            startPostponedEnterTransition();
        }
    }

    /*
    * 用于android5.0系统的界面转声动画 ：共享元素动画,启动动画
    */
    public static void start_5_0(Activity activity, View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Pair pair = new Pair(view,mActivityOptionsCompat);
            Intent intent = new Intent(activity,ShangHaiDetailActivity.class);

            ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(activity,pair);
            ActivityCompat.startActivity(activity,intent,optionsCompat.toBundle());
        }

    }
}
