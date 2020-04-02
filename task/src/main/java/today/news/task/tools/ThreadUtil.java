package today.news.task.tools;

import android.os.Handler;
import android.os.Looper;

/*
* 子线程切换到主线程
*/
public class ThreadUtil {
    //主线程的handler
    private final static Handler MAIN = new Handler(Looper.getMainLooper());
    //获取当前设备的cpu数量
    public static int CPU_NUM = Runtime.getRuntime().availableProcessors();

    public static void postMainThread(final Runnable runnable){
        MAIN.post(new Runnable() {
            @Override
            public void run() {

                try {
                    runnable.run();
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });
    }
}
