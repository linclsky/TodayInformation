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
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import today.news.com.R;
import today.news.com.base.BaseActivity;
import today.news.com.base.ViewInject;
import today.news.com.main.shanghai.manager.GetXiaoHuaTask;
import today.news.com.main.shanghai.module.ShangHaiDetailHttpTask;

@ViewInject(mainlayoutid = R.layout.activity_shanghai_detail)
public class ShangHaiDetailActivity extends BaseActivity {
    @BindView(R.id.iv_shanghai_detail)
    ImageView mIvShanghaiDetail;
    public static String mActivityOptionsCompat = "ShangHaiDetailActivity";

    @Override
    public void afterBindView() {
        initAnima();

        initGetNetData();
//        initPostNetData();

    }

    private void initPostNetData() {

        /*=---------------------POST请求---------------------------------*/
        OkHttpClient client = new OkHttpClient();
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("key", "0f08cd674792667feb5ce236ea028747");
        Request request = new Request.Builder()
                .url("http://apis.juhe.cn/lottery/types")
                .post(builder.build())
                .build(); //建造者设计模式
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

    /*
    * 发送网络请求
    * */
    private void initGetNetData() {
//        OkHttpClient client = new OkHttpClient();//okhttp配置一些默认
//        /*-------------------GET请求---------------*/
//       // Request request = new Request.Builder().url("http://www.baidu.com").get().build();//建造者设计模式，是连点的，可以(.方法)
//        HttpUrl.Builder builder = HttpUrl.parse("http://v.juhe.cn/joke/content/list.php").newBuilder();
//        builder.addQueryParameter("sort", "desc");
//        builder.addQueryParameter("page", "1");
//        builder.addQueryParameter("pagesize", "2");
//        builder.addQueryParameter("time", "" + System.currentTimeMillis()/1000);//时间戳
//        builder.addQueryParameter("key", "bbc57dd5e4f05991aff09eafd2e667e0");
//        Request request = new Request.Builder()
//                .url(builder.build())
//                .get()
//                .build();//建造者设计模式
//
//        Object desc = new ShangHaiDetailHttpTask().getXiaoHuaList("desc","1","1");
//        Response response = (Response) desc;
//        Log.e("initGetNetData", response.body().toString());

        GetXiaoHuaTask task = new GetXiaoHuaTask();
        task.execute("desc","1","1");
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
