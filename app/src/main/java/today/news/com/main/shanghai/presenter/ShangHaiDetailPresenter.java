package today.news.com.main.shanghai.presenter;

import today.news.com.base.BasePresenter;
import today.news.com.main.shanghai.lf.IShangHaiDetailContract;
import today.news.com.main.shanghai.module.ShangHaiDetailHttpTask;
import today.news.mvp.mvp.base.BaseMvpPresenter;
import today.news.task.LfTask;

public class ShangHaiDetailPresenter extends BasePresenter<IShangHaiDetailContract.Iview> implements IShangHaiDetailContract.IPresenter {
    public ShangHaiDetailPresenter(IShangHaiDetailContract.Iview view) {
        super(view);
    }


    @Override
    protected IShangHaiDetailContract.Iview getEmptyView() {
        return IShangHaiDetailContract.emptyView;
    }

    @Override
    public void getNetData() {
        submitTask(new LfTask() {
            //一定要回调到主线程
            @Override
            public void onSuccess(Object o) {
                //获取网络结果


            }

            @Override
            public void onException(Throwable throwable) {

            }

            //  运行时子线程
            @Override
            public Object onBackground() {
                return new ShangHaiDetailHttpTask().getXiaoHuaList("desc","1","1");
            }
        });
    }
}

