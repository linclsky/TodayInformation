package today.news.http;

import today.news.http.request.ICall;

public abstract class HttpScheduler {

    public abstract ICall newCall(IRequest request);

    public Object execute(ICall call) {
        return call.execute();
    }
}
