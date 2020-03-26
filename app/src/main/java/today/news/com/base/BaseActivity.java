package today.news.com.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import butterknife.ButterKnife;
import today.news.com.mvp.view.LifeCircleMvpActivity;

public abstract class BaseActivity extends LifeCircleMvpActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //获取到注解类
        ViewInject annotation = this.getClass().getAnnotation(ViewInject.class);
        if (annotation != null){
            //获取到传进来的id
            int mainlayoutid = annotation.mainlayoutid();
            //设置读取layout
            if (mainlayoutid >0){
                setContentView(mainlayoutid);
                bindView();
                afterBindView();
            }else {
                throw new RuntimeException("mainlayoutid < 0");
            }
        }else {
            throw new RuntimeException("annotation = null");
        }

    }
    //设计模式，模板方法
    public abstract void afterBindView() ;

    //  view的依赖注入绑定
    private void bindView() {
        //生成获取id注解
        ButterKnife.bind(this);
    }
}
