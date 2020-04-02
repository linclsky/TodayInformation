package today.news.http.okhttp;



import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;
import today.news.http.IRequest;
import today.news.http.request.ICall;
import today.news.http.response.IResponse;
import today.news.http.result.IResult;

public class OkHttpCall implements ICall {


private Call mCall;
private final IRequest mRequest;

    public OkHttpCall(IRequest request,Call call) {
        this.mCall = call;
        this.mRequest = request;
    }

    @Override
    public IResponse execute() {

        Response response = null;
        try {
            response = mCall.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        OkHttpResponse okHttpResponse = new  OkHttpResponse(response);
        return okHttpResponse;
    }

    @Override
    public IRequest getRequest() {
        return mRequest;
    }
}
