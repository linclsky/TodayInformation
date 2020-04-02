package today.news.com.base;

import java.lang.reflect.Type;

import today.news.http.IRequest;
import today.news.http.annotation.RequestMethod;
import today.news.http.parser.DefaultResultParse;
import today.news.http.request.Lfrequest;

public class JHRequest extends Lfrequest {

    public static IRequest sendHttp(String path, @RequestMethod int requestMethod, Type type){
        JHRequest request = new JHRequest();
        request.host = HostManager.jhHost;
        request.path = path;
        request.requestMethod = requestMethod;
        request.mType = type;
        request.resultParser = DefaultResultParse.getInstance();//单例模式

        return request;
    }
}
