package today.news.http.result;

public interface IResultCallBack<T> {

    void onSuccess(IResult<T> t);

    void onFailed(IResult t);
}
