package today.news.com.main.shanghai.module;

import java.util.HashMap;
import java.util.Map;

import today.news.http.LfHttpServer;
import today.news.http.result.IResult;

public class ShangHaiDetailHttpTask<T> extends LfHttpServer {

    public IResult<T> getXiaoHuaList (String sort, String page, String pagesize){
        Map<String,Object> params = new HashMap<>();
        params.put("sort",sort);
        params.put("page",page);
        params.put("pagesize",pagesize);
        params.put("time","" + System.currentTimeMillis()/1000);
        params.put("key", "bbc57dd5e4f05991aff09eafd2e667e0");
        return super.execute(ShangHaiDetailRequest.xiaoHuaRequest,params);
    }


}
