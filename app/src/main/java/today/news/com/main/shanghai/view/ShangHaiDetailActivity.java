package today.news.com.main.shanghai.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;

import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
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

        initGetNetData();

    }
    /*
    * 发送网络请求
    * */
    private void initGetNetData() {
        OkHttpClient client = new OkHttpClient();//okhttp配置一些默认
        Request request = new Request.Builder().url("http://www.baidu.com").get().build();//建造者设计模式，是连点的，可以(.方法)
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            //请求失败
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("initGetNetData","失败" + e);
            }
            //请求成功
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e("initGetNetData","成功onResponse" + response.body().string());

            }
        });

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
