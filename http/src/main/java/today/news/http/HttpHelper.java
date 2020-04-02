package today.news.http;

import java.util.Map;

import today.news.http.okhttp.OkHttpScheduler;
import today.news.http.request.ICall;
import today.news.http.result.IResult;

public class HttpHelper {

    private volatile static HttpScheduler sHttpScheduler;

    public static HttpScheduler getHttpScheduler(){
        if (sHttpScheduler == null){
            synchronized (HttpHelper.class){
                if (sHttpScheduler == null){
                    sHttpScheduler = new OkHttpScheduler();
                }
            }
        }
        return sHttpScheduler;
    }

    //TODO:待重构

    protected static <T> IResult<T> execute(IRequest request, Map<String,Object> params){
        request.setParams(params);

        ICall call = getHttpScheduler().newCall(request);


        return getHttpScheduler().execute(call);
    }
}
