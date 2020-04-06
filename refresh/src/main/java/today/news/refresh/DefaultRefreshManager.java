package today.news.refresh;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

public class DefaultRefreshManager extends BaseRefreshManager {

    private View mView;
    private TextView mTvRefresh;

    public DefaultRefreshManager(Context context) {
        super(context);
    }

    @Override
    public View getHeaderView() {
        mView = mLayoutInflater.inflate(R.layout.ulti_header_layout, null, false);
        mTvRefresh = mView.findViewById(R.id.header_text);
        return mView;
    }

    @Override
    public void downRefresh() {
        mTvRefresh.setText("下拉刷新");
    }

    @Override
    public void releaseRefresh() {
        mTvRefresh.setText("释放刷新");


    }

    @Override
    public void iddleRefresh() {
        mTvRefresh.setText("下拉刷新");
    }

    @Override
    public void refreshing() {
        mTvRefresh.setText("正在刷新");
    }


}
