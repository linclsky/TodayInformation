package today.news.com.main.hangzhou;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import today.news.com.R;
import today.news.com.base.BaseFragment;
import today.news.com.base.ViewInject;
import today.news.com.main.shenzhen.ShenZhenFragment;

@ViewInject(mainlayoutid = R.layout.fragment_hangzhou)
public class HangZhouFragment extends BaseFragment {

    @BindView(R.id.tl_tablayout)
    TabLayout mTlTablayout;
    @BindView(R.id.vp_viewpager)
    ViewPager mVpViewpager;
//   ArrayList<String> mArrayList = new ArrayList<>();

    @Override
    public void afterBindView() {

//        for (int i = 0; i <5 ; i++) {
//            mArrayList.add("深圳");
//        }
        mTlTablayout.setupWithViewPager(mVpViewpager);
        mVpViewpager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                Log.d("HangZhouFragment", "position" + i);
                return new ShenZhenFragment();
            }

            @Override
            public int getCount() {
                return 5;
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return "深圳";
            }
        });

//        mVpViewpager.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                mArrayList.add("深圳");
//                mVpViewpager.getAdapter().notifyDataSetChanged();
//            }
//        },5000);
        // 预加载页面
        //mVpViewpager.setOffscreenPageLimit(4);
    }
}
