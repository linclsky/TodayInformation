package today.news.http.okhttp;



import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;
import today.news.http.IRequest;
import today.news.http.request.ICall;

public class OkHttpCall implements ICall {
private Call mCall;

    public OkHttpCall(IRequest request,Call call) {
        this.mCall = call;
    }

    @Override
    public Object execute() {

        Response response = null;
        try {
            response = mCall.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
}
