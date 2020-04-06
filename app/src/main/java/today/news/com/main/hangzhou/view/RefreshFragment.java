package today.news.com.main.hangzhou.view;

import butterknife.BindView;
import today.news.com.R;
import today.news.com.base.BaseFragment;
import today.news.com.base.ViewInject;
import today.news.refresh.GodRefreshLayout;

@ViewInject(mainlayoutid = R.layout.fragment_refresh)
public class RefreshFragment extends BaseFragment {
    @BindView(R.id.god_refresh)
    GodRefreshLayout mGodRefresh;

    @Override
    public void afterBindView() {
        mGodRefresh.setRefreshManager();
        mGodRefresh.setRefreshListener(new GodRefreshLayout.RefreshingListener() {
            @Override
            public void onRefreshing() {
                mGodRefresh.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mGodRefresh.refreshOver();
                    }
                },2000);
            }
        });

    }
}
