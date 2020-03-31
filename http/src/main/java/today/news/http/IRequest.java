package today.news.http;

import java.util.Map;

import today.news.http.request.IHost;

public interface IRequest {
    void setParams(Map<String, Object> params);


    Map<String, Object> getParams();

    int getRequestMethod();

    IHost getHost();

    String getPath();



}
