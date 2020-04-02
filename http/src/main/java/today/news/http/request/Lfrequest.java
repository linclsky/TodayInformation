package today.news.http.request;

import java.lang.reflect.Type;
import java.util.Map;

import today.news.http.parser.IParser;
import today.news.http.IRequest;
import today.news.http.annotation.RequestMethod;

public class Lfrequest implements IRequest {

    protected IHost host;
   protected Map<String, Object> params;
   protected Type mType;
   protected IParser resultParser;
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

    @Override
    public IParser getParser() {
        return resultParser;
    }

    @Override
    public Type getType() {
        return mType;
    }

}
