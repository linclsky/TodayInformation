package today.news.com.main.shanghai.module;

import today.news.com.base.JHRequest;
import today.news.com.main.shanghai.dto.ShangHaiDetailBean;
import today.news.http.IRequest;
import today.news.http.annotation.RequestMethod;

public interface ShangHaiDetailRequest {

    IRequest xiaoHuaRequest = JHRequest.sendHttp("/joke/content/list.php", RequestMethod.GET, ShangHaiDetailBean.class);
}
