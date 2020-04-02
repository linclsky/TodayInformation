package today.news.task;

public class TaskHelper {

    public static void submitTask(ITaskBackground iTaskBackground,ITaskCallBack iTaskCallBack){

        AsyncTaskInstance instance = AsyncTaskInstance.getInstanse(iTaskBackground,iTaskCallBack);

        //构建线程池管理器
       TaskScheduler.getInstance().submit(instance);

    }
}
