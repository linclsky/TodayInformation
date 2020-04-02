package today.news.http;

import today.news.http.parser.IParser;
import today.news.http.request.ICall;
import today.news.http.response.IResponse;
import today.news.http.result.IResult;

public abstract class HttpScheduler {

    public abstract ICall newCall(IRequest request);

    public IResult execute(ICall call) {

        //IRespnse和IRseult进行一个转换
        IResponse iResponse = call.execute();


        IRequest request = call.getRequest();

        IParser parser = request.getParser();


        return parser.parseResponse(request,iResponse);
    }
}
