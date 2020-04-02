package today.news.task;

public interface ITaskCallBack<Result> {
    void onComplete(Result o);


    void onException(Throwable throwable);
}
