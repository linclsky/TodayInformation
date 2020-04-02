package today.news.task;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import today.news.task.tools.ThreadUtil;


public class AsyncTaskInstance<Result> extends FutureTask<Result> {
    private final ITaskBackground iTaskBackground;
    private final ITaskCallBack iTaskCallBack;

    public AsyncTaskInstance(final ITaskBackground<Result> iTaskBackground, ITaskCallBack<Result> iTaskCallBack) {
        super(new Callable<Result>() {
            @Override
            public Result call() throws Exception {
                return iTaskBackground.onBackground();
            }
        });
        this.iTaskBackground = iTaskBackground;
        this.iTaskCallBack = iTaskCallBack;
    }
    //子线程运行后会走到这一步
    @Override
    protected void done() {
        if(iTaskCallBack != null){
            onComplete();
        }
    }
    //获取FutureTask执行过程中的异常
    @Override
    protected void setException(final Throwable t) {
        super.setException(t);
        if (iTaskCallBack != null){
            ThreadUtil.postMainThread(new Runnable() {
                @Override
                public void run() {
                    iTaskCallBack.onException(t);
                }
            });
        }
    }

    private void onComplete() {

        try {
            final Object object = get();
            if (object != null){

                ThreadUtil.postMainThread(new Runnable() {
                    @Override
                    public void run() {
                        iTaskCallBack.onComplete(object);
                    }
                });

            }
        }  catch (Exception e) {
            e.printStackTrace();
        }

    }

    //封闭对外的接口
    public static AsyncTaskInstance getInstanse(ITaskBackground iTaskBackground, ITaskCallBack iTaskCallBack){
        return new AsyncTaskInstance(iTaskBackground, iTaskCallBack);
    }
}
