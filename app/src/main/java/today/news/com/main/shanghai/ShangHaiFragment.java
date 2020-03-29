package today.news.com.main.shanghai;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import today.news.com.R;
import today.news.com.base.BaseFragment;
import today.news.com.base.ViewInject;
import today.news.com.main.shanghai.adapter.ShangHaiAdapter;
import today.news.com.main.shanghai.dto.ShangHaiDataManager;

@ViewInject(mainlayoutid = R.layout.fragment_shanghai)
public class ShangHaiFragment extends BaseFragment {
    public static final String TAG = ShangHaiFragment.class.getSimpleName();

    @BindView(R.id.tv_shanghai_welcome)
    TextView mTvShanghaiWelcome;
    @BindView(R.id.shanghai_collapsingtoolbarlayout)
    CollapsingToolbarLayout mShanghaiCollapsingtoolbarlayout;
    @BindView(R.id.shanghai_app_barlayout)
    AppBarLayout mShanghaiAppBarlayout;
    @BindView(R.id.shanghai_recyclerview)
    RecyclerView mShanghaiRecyclerview;

    @Override
    public void afterBindView() {
        initRecyclerView();
        initListener();

    }

    private void initRecyclerView() {
        mShanghaiRecyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        //传入适配器
        mShanghaiRecyclerview.setAdapter(new ShangHaiAdapter(getActivity(), ShangHaiDataManager.getData(),false));

    }

    private void initListener() {
        mShanghaiAppBarlayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                //Log.e(TAG, "verticaloffset=" + i + "appBarLayout=" + appBarLayout);
                //滑动到appbarlayout高度的一半是显示出标题
                if (-i < appBarLayout.getMeasuredHeight() / 2) {
                    mTvShanghaiWelcome.setVisibility(View.INVISIBLE);
                } else {
                    mTvShanghaiWelcome.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
