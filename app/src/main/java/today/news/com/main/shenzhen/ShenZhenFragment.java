package today.news.com.main.shenzhen;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.TextView;

import today.news.com.R;
import today.news.com.base.BaseFragment;
import today.news.com.base.ViewInject;

@ViewInject(mainlayoutid = R.layout.fragment_shengzhen)
public class ShenZhenFragment extends BaseFragment {



    @Override
    public void afterBindView() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d("ShenZhenFragment", "onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("ShenZhenFragment", "onCreate");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("ShenZhenFragment", "onPause");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("ShenZhenFragment", "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("ShenZhenFragment", "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("ShenZhenFragment", "onDetach");
    }

    //FragmentPagerAdapter 会走 onPause onDestroyView
    //FragmentStatePagerAdapter 会走 onPause onDestroyView onDestroy onDetach
}
