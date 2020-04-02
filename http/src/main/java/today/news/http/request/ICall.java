package today.news.http.request;

import today.news.http.IRequest;
import today.news.http.response.IResponse;
import today.news.http.result.IResult;

public interface ICall {

    IResponse execute();
    IRequest getRequest();

}
