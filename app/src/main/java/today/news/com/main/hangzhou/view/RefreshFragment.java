package today.news.com.main.hangzhou.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import today.news.com.R;
import today.news.com.base.BaseFragment;
import today.news.com.base.ViewInject;
import today.news.com.main.hangzhou.adpter.ZhiHuAdapter;
import today.news.com.main.hangzhou.refresh.MeiTuanRefreshManager;
import today.news.com.main.shanghai.dto.ShangHaiDetailBean;
import today.news.com.main.shanghai.lf.IShangHaiDetailContract;
import today.news.com.main.shanghai.presenter.ShangHaiDetailPresenter;
import today.news.refresh.GodRefreshLayout;

@ViewInject(mainlayoutid = R.layout.fragment_refresh)
public class RefreshFragment extends BaseFragment implements IShangHaiDetailContract.Iview {
    IShangHaiDetailContract.IPresenter mIPresenter = new ShangHaiDetailPresenter(this);
    @BindView(R.id.god_refresh)
    GodRefreshLayout mGodRefresh;
    @BindView(R.id.refresh_recyclerview)
    RecyclerView mRefreshRecyclerview;

    @Override
    public void afterBindView() {

        initRecyclerView();
        initRefreshLayout();
    }

    private void initRefreshLayout() {
        //1.采用默认的方式
        //mGodRefresh.setRefreshManager();
        //2.是支持用户自定义
        mGodRefresh.setRefreshManager(new MeiTuanRefreshManager(mContext));
        mGodRefresh.setRefreshListener(new GodRefreshLayout.RefreshingListener() {
            @Override
            public void onRefreshing() {
                mIPresenter.getNetData(20);
//                mGodRefresh.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        mGodRefresh.refreshOver();
//                    }
//                }, 2000);
            }
        });
    }

    private void initRecyclerView() {
        mRefreshRecyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        mIPresenter.getNetData(20);
    }

    @Override
    public void showData(ShangHaiDetailBean data) {
        if (mRefreshRecyclerview.getAdapter() == null) {
            ZhiHuAdapter adapter = new ZhiHuAdapter(data.result.data);
            mRefreshRecyclerview.setAdapter(adapter);
        }
        mGodRefresh.refreshOver();
    }
}

