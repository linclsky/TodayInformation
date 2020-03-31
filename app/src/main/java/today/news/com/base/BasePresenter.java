package today.news.com.base;

import today.news.mvp.mvp.IMvpView;
import today.news.mvp.mvp.base.BaseMvpPresenter;
import today.news.task.LfTask;
import today.news.task.TaskHelper;

/*
* 集成MVP 及 网络请求 快捷方式*/
public abstract class BasePresenter<T extends IMvpView> extends BaseMvpPresenter<T> {
    public BasePresenter(T view) {
        super(view);
    }

    public  void submitTask(LfTask lfTask){
        TaskHelper.submitTask(lfTask,lfTask);

    }

}
