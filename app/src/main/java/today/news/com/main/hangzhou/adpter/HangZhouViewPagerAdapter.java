package today.news.com.main.hangzhou.adpter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

import today.news.com.main.hangzhou.view.JiKeFragment;
import today.news.com.main.hangzhou.view.ZHiHuFragment;

public class HangZhouViewPagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<String> titleList = new ArrayList<>();
    public HangZhouViewPagerAdapter(FragmentManager fm) {
        super(fm);
        titleList.add("知乎");
        titleList.add("即刻");
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:return new ZHiHuFragment();
            case 1:return new JiKeFragment();
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return titleList.size();
    }
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }

}
