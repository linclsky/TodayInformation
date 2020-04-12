package today.news.ipc;

import today.news.ipc.requst.IRequest;

public class IpcManager {

    private static IpcManager mInstance;

    public synchronized static IpcManager getInstance(){
        if (mInstance == null){
            mInstance = new IpcManager();
        }
        return mInstance;
    }

    //异步跨进程通信
    public void excuteAsync(String requestKey, IRequest request){

    }
//    public IResult excuteSync(String requestKey, IRequest request){
//        return null;
//    }
}
