package today.news.task;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import today.news.task.tools.ThreadUtil;

import static today.news.task.TaskScheduler.ITaskSchedulerType.SUMIT_TASK;

public class TaskScheduler {
    private final PriorityThreadPoolExecutor executor;

    interface ITaskSchedulerType{
        int SUMIT_TASK = 0;
    }

    private static TaskScheduler instance;
    private final Handler handle;
    private int COREPOOLSIZE = ThreadUtil.CPU_NUM + 1;
    private int MAXIMUMPOOLSIZE = COREPOOLSIZE * 3;
    private int KEEPALIVETIME =  1;


    private TaskScheduler(){

        //用于消息调试的线程
        HandlerThread handlerThread = new HandlerThread("task-thread");
        handlerThread.start();
        this.handle = new Handler(handlerThread.getLooper(), new Handler.Callback() {
            // HandlerThread handlerThread 运行在子线程
            @Override
            public boolean handleMessage(Message msg) {
                switch (msg.what){
                    case ITaskSchedulerType.SUMIT_TASK:
                        doSubminTask((AsyncTaskInstance) msg.obj);
                        break;
                }
                return false;
            }
        });
        //创建一个线程池
        BlockingQueue<Runnable> poolQueue = new LinkedBlockingQueue<>();//无大小限制的队列
        this.executor = new PriorityThreadPoolExecutor(
                COREPOOLSIZE,
                MAXIMUMPOOLSIZE,
                KEEPALIVETIME,
                TimeUnit.MINUTES,
                poolQueue,
                new TaskThreadFactory()
                );
    }

    private void doSubminTask(AsyncTaskInstance taskInstance) {
        executor.submit(taskInstance);
    }

    public static TaskScheduler getInstance(){
        if (instance == null){
            instance = new TaskScheduler();
        }
        return instance;
    }

    public void submit(AsyncTaskInstance instance) {
        handle.sendMessage(handle.obtainMessage(SUMIT_TASK,instance));

    }
}
