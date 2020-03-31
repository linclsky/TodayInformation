package today.news.task.tools;

import android.os.Handler;
import android.os.Looper;

/*
* 子线程切换到主线程
*/
public class ThreadUtil {
    private final static Handler MAIN = new Handler(Looper.getMainLooper());

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
