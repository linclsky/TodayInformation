package today.news.http.request;

import today.news.http.IRequest;
import today.news.http.annotation.RequestMethod;

public class Lfrequest implements IRequest {

    protected IHost host;

    @RequestMethod
    protected int requestMethod;
}
