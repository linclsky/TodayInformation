package today.news.com.main;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import butterknife.BindView;
import butterknife.OnClick;
import today.news.com.R;
import today.news.com.base.BaseActivity;
import today.news.com.base.ViewInject;
import today.news.com.main.tools.MainConstantTool;

@ViewInject(mainlayoutid = R.layout.activity_main)
public class MainActivity extends BaseActivity implements IMainActivityContract.Iview {
    IMainActivityContract.IPresenter mPresenter = new MainActivityPresenter(this);
    private boolean isChangeTopOrBottom;

    @BindView(R.id.fac_main_home)
    FloatingActionButton mFacMainHome;
    @BindView(R.id.rb_main_shanghai)
    RadioButton mRbMainShanghai;
    @BindView(R.id.rb_main_hangzhou)
    RadioButton mRbMainHangzhou;
    @BindView(R.id.rg_main_top)
    RadioGroup mRgMainTop;
    @BindView(R.id.fl_main_bottom)
    FrameLayout mFlMainBottom;
    @BindView(R.id.rb_main_bejing)
    RadioButton mRbMainBejing;
    @BindView(R.id.rb_main_shenzhen)
    RadioButton mRbMainShenzhen;
    @BindView(R.id.rg_main_bottom)
    RadioGroup mRgMainBottom;



    @Override
    public void afterBindView() {
        initHomeFragment();
        changeAnima(mRgMainBottom,mRgMainTop);
        initCheckListener();
    }



        private void initCheckListener() {
            mRbMainShanghai.setChecked(true);
            mRgMainTop.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    if (checkedId == mPresenter.getCurrentCheckedId()) {
                        return;
                    }
                    switch (checkedId) {
                        case R.id.rb_main_shanghai:
                            mPresenter.replaceFragment(MainConstantTool.SHANGHAI);
                            break;
                        case R.id.rb_main_hangzhou:
                            mPresenter.replaceFragment(MainConstantTool.HANGZHOU);
                            break;
                    }
                }
            });

            mRgMainBottom.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    if (checkedId == mPresenter.getCurrentCheckedId()) {
                        return;
                    }
                    switch (checkedId) {
                        case R.id.rb_main_bejing:
                            mPresenter.replaceFragment(MainConstantTool.BEIJING);
                            break;
                        case R.id.rb_main_shenzhen:
                            mPresenter.replaceFragment(MainConstantTool.SHENZHEN);
                            break;
                    }
                }
            });
        }


    //初始化fragment
    private void initHomeFragment() {
        mPresenter.initHomeFragment();
    }




    @OnClick(R.id.fac_main_home)
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fac_main_home:
                isChangeTopOrBottom = !isChangeTopOrBottom;
                if (isChangeTopOrBottom){
                    changeAnima(mRgMainTop,mRgMainBottom);
                    handleTopPosition();
                }else {
                    changeAnima(mRgMainBottom,mRgMainTop);
                    handleBottomPosition();
                }

                break;
        }
    }
    //北京 深圳
    private void handleBottomPosition() {
        if(mPresenter.getTopPosition() != 1){
            mPresenter.replaceFragment(0);
            mRbMainShanghai.setChecked(true);
        }else {
            mPresenter.replaceFragment(1);
            mRbMainShenzhen.setChecked(true);
        }

    }
    //上海 杭州
    private void handleTopPosition() {
        if (mPresenter.getBottomPosition() != 3){
            mPresenter.replaceFragment(2);
            mRbMainBejing.setChecked(true);
        }else {
            mPresenter.replaceFragment(3);
            mRbMainHangzhou.setChecked(true);
        }


    }

    private void changeAnima(RadioGroup gone, RadioGroup show) {
        //消失的动画
        gone.clearAnimation();//清除自身动画
        Animation animationGone = AnimationUtils.loadAnimation(this,R.anim.main_tab_translate_hide);
        gone.startAnimation(animationGone);
        gone.setVisibility(View.GONE);
        //展示的动画
        show.clearAnimation();//清除自身动画
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.main_tab_translate_show);
        show.startAnimation(animation);
        show.setVisibility(View.VISIBLE);

    }
    //  显示fragment
    @Override
    public void showFragment(Fragment mFragment) {
        getSupportFragmentManager().beginTransaction().show(mFragment).commit();
    }
    //添加fragment
    @Override
    public void addFragment(Fragment mFragment) {
        getSupportFragmentManager().beginTransaction().add(R.id.fl_main_content,mFragment).commit();

    }

    @Override
    public void hideFragment(Fragment mFragment) {
        getSupportFragmentManager().beginTransaction().hide(mFragment).commit();
    }
}
