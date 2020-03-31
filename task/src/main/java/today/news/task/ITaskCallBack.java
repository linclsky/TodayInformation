package today.news.task;

public interface ITaskCallBack {
    void onSuccess(Object o);


    void onException(Throwable throwable);
}
