package today.news.com.main.beijing;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import today.news.com.R;
import today.news.com.base.BaseFragment;
import today.news.com.base.ViewInject;
import today.news.com.main.shanghai.view.ShangHaiDetailActivity;

@ViewInject(mainlayoutid = R.layout.fragment_bejing)
public class BeiJingFragment extends BaseFragment {


    @BindView(R.id.bt_paly)
    Button tvClick;
//    private processDataReceiver mProcessDataReceiver;

    @Override
    public void afterBindView() {
        mContext.startService(new Intent(mContext,MainProcessService.class));

        tvClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //去上海
                ProcessDataTest.getInstance().setProcessDec("你好，我来自北京");
                ShangHaiDetailActivity.start_5_0(getActivity(),tvClick);
            }
        });
//        mProcessDataReceiver = new processDataReceiver();
//        getActivity().registerReceiver(mProcessDataReceiver,new IntentFilter("shanghai_get_process_data"));

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mContext.stopService(new Intent(mContext,MainProcessService.class));
//        getActivity().unregisterReceiver(mProcessDataReceiver);
    }

//    private class processDataReceiver extends BroadcastReceiver{
//
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            String processDec = ProcessDataTest.getInstance().getProcessDec();
//            Intent postIntent = new Intent("beijing_post_process_data");
//            postIntent.putExtra("processDec",processDec);
//            getActivity().sendBroadcast(postIntent);
//        }
//    }


}
