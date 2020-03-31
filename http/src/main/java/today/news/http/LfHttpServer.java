package today.news.http;

import java.util.Map;

public class LfHttpServer {

    protected Object execute(IRequest request ,Map<String,Object> params){
        return HttpHelper.execute(request,params);
    }

}
