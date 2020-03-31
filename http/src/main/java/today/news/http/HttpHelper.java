package today.news.http;

import java.util.Map;

import today.news.http.okhttp.OkHttpScheduler;
import today.news.http.request.ICall;

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

    protected static  Object execute(IRequest request, Map<String,Object> params){
        request.setParams(params);

        ICall call = getHttpScheduler().newCall(request);
        Object object = getHttpScheduler().execute(call);

        return object;
    }
}
