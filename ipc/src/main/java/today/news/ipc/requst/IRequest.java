package today.news.ipc.requst;

public interface IRequest {
    void setParams(String params);

    String getParams();

    String getRequesKey();
}
