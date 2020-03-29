package today.news.com.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import today.news.com.mvp.view.LifeCircleMvpActivity;
import today.news.com.mvp.view.LifeCircleMvpFragment;

public abstract class BaseFragment extends LifeCircleMvpFragment {

    protected Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = null;
        //获取到注解类
        ViewInject annotation = this.getClass().getAnnotation(ViewInject.class);
        if (annotation != null){
            //获取到传进来的id
            int mainlayoutid = annotation.mainlayoutid();
            //设置读取layout
            if (mainlayoutid >0){
               mView =  initFragmentView(inflater,mainlayoutid);
                bindView(mView);
                afterBindView();
            }else {
                throw new RuntimeException("mainlayoutid < 0");
            }
        }else {
            throw new RuntimeException("annotation = null");
        }
        return mView;
    }

    private View initFragmentView(LayoutInflater inflater, int mainlayoutid) {
        return inflater.inflate(mainlayoutid,null);

    }


    //设计模式，模板方法
    public abstract void afterBindView() ;

    //  view的依赖注入绑定
    private void bindView(View view) {
        //生成获取id注解
        ButterKnife.bind(this,view);
    }
}
