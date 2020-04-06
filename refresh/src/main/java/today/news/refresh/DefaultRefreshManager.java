package today.news.refresh;

import android.content.Context;
import android.view.View;

public class DefaultRefreshManager extends BaseRefreshManager {

    private View mView;
    private View mTvRefresh;

    public DefaultRefreshManager(Context context) {
        super(context);
    }

    @Override
    public View getHeaderView() {
        mView = mLayoutInflater.inflate(R.layout.ulti_header_layout, null, false);
        mTvRefresh = mView.findViewById(R.id.header_text);
        return mView;
    }

}
