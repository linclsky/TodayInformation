package today.news.http.okhttp;

import android.view.textclassifier.TextLinks;

import java.util.Iterator;
import java.util.Map;

import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import today.news.http.HttpScheduler;
import today.news.http.IRequest;
import today.news.http.annotation.RequestMethod;
import today.news.http.request.ICall;

/*
* 组合参数的类
*/
public class OkHttpScheduler extends HttpScheduler {

    private OkHttpClient mClient;

    @Override
    public ICall newCall(IRequest request) {
        //获取到参数的集合
        Map<String,Object> params = request.getParams();
        //获取到请求的类型
        int requestMethod = request.getRequestMethod();
        //声明请求的类
        Request.Builder requestuilder = new Request.Builder();

        switch (requestMethod){
            case RequestMethod.GET:
                //拼接Get请求的url host + path
                StringBuilder urlStrBuilder = new StringBuilder(request.getHost().getHost());
                urlStrBuilder.append(request.getPath());
                HttpUrl.Builder urlBuilder = HttpUrl.parse(urlStrBuilder.toString()).newBuilder();
                if (params != null && params.size() > 0){
                    //声明迭代器
                    Iterator<Map.Entry<String,Object>> iterator = params.entrySet().iterator();
                    while (iterator.hasNext()){
                        Map.Entry<String,Object> next = iterator.next();
                        //TODO: 待重构 这里涉及对象如何转成String 字符串

                        urlBuilder.addQueryParameter(next.getKey(), String.valueOf(next.getValue()));
                    }
                }
                requestuilder.get().url(urlBuilder.build());
                break;
                case RequestMethod.POST:
                    break;
        }
        Request okHttpRequest = requestuilder.build();
        Call call = getClient().newCall(okHttpRequest);
        OkHttpCall okHttpCall = new OkHttpCall(request,call);

        return okHttpCall;
    }

    private OkHttpClient getClient() {
        if (mClient == null){
            mClient = new OkHttpClient();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();

        }
        return mClient;
    }
}
