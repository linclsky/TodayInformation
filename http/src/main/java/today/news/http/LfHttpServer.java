package today.news.http;

import java.util.Map;

import today.news.http.result.IResult;

public class LfHttpServer {

    protected <T> IResult<T> execute(IRequest request , Map<String,Object> params){
        return HttpHelper.execute(request,params);
    }

}
