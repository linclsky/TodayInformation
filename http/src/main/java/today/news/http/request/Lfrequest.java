package today.news.http.request;

import java.util.Map;

import today.news.http.IRequest;
import today.news.http.annotation.RequestMethod;

public class Lfrequest implements IRequest {

    protected IHost host;
   protected Map<String, Object> params;
    @RequestMethod
    protected int requestMethod;
    protected String path;

    @Override
    public void setParams(Map<String, Object> params) {
        this.params= params;
    }

    @Override
    public Map<String, Object> getParams() {
        return params;
    }

    @Override
    public int getRequestMethod() {
        return requestMethod;
    }

    @Override
    public IHost getHost() {
        return host;
    }

    @Override
    public String getPath() {
        return path;
    }
}
