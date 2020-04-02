package today.news.http.parser;

import com.google.gson.Gson;

import java.lang.reflect.Type;

import today.news.http.IRequest;
import today.news.http.response.IResponse;
import today.news.http.result.IResult;
import today.news.http.result.Result;

public class DefaultResultParse implements IParser {
    private static DefaultResultParse mInstance;
    private final Gson mGson;

    private DefaultResultParse() {
        mGson = new Gson();
    }

    public static IParser getInstance() {

        if (mInstance == null){
            mInstance = new DefaultResultParse();
        }
        return mInstance;
    }


    @Override
    public IResult parseResponse(IRequest request, IResponse iResponse) {
        Type type = request.getType();
        String bodyStr = iResponse.getBodyStr();
        Object object = mGson.fromJson(bodyStr, type);


            return Result.success(object);



    }
}
