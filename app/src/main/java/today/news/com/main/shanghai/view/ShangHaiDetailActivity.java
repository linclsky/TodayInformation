package today.news.com.main.shanghai.view;

import android.app.Activity;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Build;

import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
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
import today.news.com.main.beijing.MainProcessService;
import today.news.com.main.shanghai.dto.ShangHaiDetailBean;
import today.news.com.main.shanghai.lf.IShangHaiDetailContract;
import today.news.com.main.shanghai.manager.GetXiaoHuaTask;
import today.news.com.main.shanghai.module.ShangHaiDetailHttpTask;
import today.news.com.main.shanghai.presenter.ShangHaiDetailPresenter;

@ViewInject(mainlayoutid = R.layout.activity_shanghai_detail)
public class ShangHaiDetailActivity extends BaseActivity implements IShangHaiDetailContract.Iview {

    IShangHaiDetailContract.IPresenter mIPresenter = new ShangHaiDetailPresenter(this);
    @BindView(R.id.iv_shanghai_detail)
    ImageView mIvShanghaiDetail;
    public static String mActivityOptionsCompat = "ShangHaiDetailActivity";
//    private GetProcessReceiver mGetProcessReceiver;
    private ServiceConnection mServiceConnection = new ServiceConnection() {

    private Messenger mMessenger;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Bundle data = msg.getData();
            Log.e(mActivityOptionsCompat, "processDec = " + data.getString("process"));
        }
    };
    private Messenger mMessengerClient = new Messenger(mHandler);

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        mMessenger = new Messenger(service);
        Message message = new Message();
        message.what = MainProcessService.SHANGHAI_DETAIL;
        message.replyTo = mMessengerClient;
        try {
            mMessenger.send(message);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }
};

    @Override
    public void afterBindView() {
        initAnima();
//        initReceiver();
//        initProcessData();
        initGetNetData();
//        initPostNetData();
//        initProviderData();
        initProcessService();

    }

    private void initProcessService() {
        Intent intent = new Intent(this, MainProcessService.class);
        bindService(intent,mServiceConnection, Service.BIND_AUTO_CREATE);

    }

//    private void initProviderData() {
//        Uri insert = getContentResolver().insert(Uri.parse("content://com.news.today.process.data"), new ContentValues());
//      Log.e(mActivityOptionsCompat, "processDec = " + insert.toString());
//    }

//    private void initReceiver() {
//        mGetProcessReceiver = new GetProcessReceiver();
//        registerReceiver(mGetProcessReceiver,new IntentFilter("beijing_post_process_data"));
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        unregisterReceiver(mGetProcessReceiver);
    }

//    private void initProcessData() {
//        Intent intent = new Intent("shanghai_get_process_data");
//        sendBroadcast(intent);
//    }

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

//        GetXiaoHuaTask task = new GetXiaoHuaTask();
//        task.execute("desc","1","1");
        mIvShanghaiDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIPresenter.getNetData(20);
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

    @Override
    public void showData(ShangHaiDetailBean data) {

    }

//
//private class GetProcessReceiver extends BroadcastReceiver{
//
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            String processDec = intent.getStringExtra("processDec");
//           Log.e(mActivityOptionsCompat, "processDec = " + processDec);
//        }
//    }
}
