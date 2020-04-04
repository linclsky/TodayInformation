package today.news.com.main.hangzhou.view;

import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import today.news.com.R;
import today.news.com.base.BaseFragment;
import today.news.com.base.ViewInject;
import today.news.com.main.hangzhou.adpter.ZhiHuAdapter;
import today.news.com.main.shanghai.dto.ShangHaiDetailBean;
import today.news.com.main.shanghai.lf.IShangHaiDetailContract;
import today.news.com.main.shanghai.presenter.ShangHaiDetailPresenter;


@ViewInject(mainlayoutid = R.layout.fragment_zhihu)
public class ZHiHuFragment extends BaseFragment implements IShangHaiDetailContract.Iview {

    IShangHaiDetailContract.IPresenter mIPresenter = new ShangHaiDetailPresenter(this);
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.zhihu_app_barlayout)
    AppBarLayout mZhihuAppBarlayout;
    @BindView(R.id.zhihu_recyclerview)
    RecyclerView mZhihuRecyclerview;

    @Override
    public void afterBindView() {

        mZhihuRecyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        mIPresenter.getNetData(20);
    }

    @Override
    public void showData(ShangHaiDetailBean data) {

        if (mZhihuRecyclerview.getAdapter() == null){
            ZhiHuAdapter adapter = new ZhiHuAdapter(data.result.data);
            mZhihuRecyclerview.setAdapter(adapter);
        }

    }
}
