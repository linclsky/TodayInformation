package today.news.com.main.beijing;

import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;

public class MainProcessService extends Service {
    public static final int SHANGHAI_DETAIL = 0;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case SHANGHAI_DETAIL:
                    Messenger replyTo = msg.replyTo;
                    Message message = new Message();
                    Bundle bundle = new Bundle();
                    bundle.putString("process",ProcessDataTest.getInstance().getProcessDec());
                    message.setData(bundle);
                    try {
                        replyTo.send(message);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
            }
        }
    };

    //service跨进程 通信的原理就是这个Messenger
    private Messenger mMessenger = new Messenger(mHandler);
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }
}
