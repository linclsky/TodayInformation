package today.news.http.okhttp;

import java.io.IOException;

import okhttp3.Response;
import today.news.http.response.IResponse;

public class OkHttpResponse  implements IResponse {

    private final Response response;

    public OkHttpResponse(Response response) {

        this.response = response;
    }

    @Override
    public String getBodyStr() {
        try {
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
