package today.news.com.base;

import today.news.http.IRequest;
import today.news.http.annotation.RequestMethod;
import today.news.http.request.Lfrequest;

public class JHRequest extends Lfrequest {

    public static IRequest sendHttp(String path, @RequestMethod int requestMethod){
        JHRequest request = new JHRequest();
        request.host = HostManager.jhHost;
        request.requestMethod = requestMethod;

        return request;
    }
}
