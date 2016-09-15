package com.augmentis.ayp.timer3;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;

/**
 * Created by Wilailux on 9/15/2016.
 */
public class TimerHandlerThread extends HandlerThread {

    private static final int MESSAGE_1 = 123;
    protected Handler handler;
    protected Handler handler2;
    private CallBack callBack;
    int i;


    public TimerHandlerThread(String name, Context ctx, Handler h) {
        super(name);
        callBack = (CallBack) ctx;
        handler = h;
    }

    interface CallBack{
        void sentback(String s);
    }

    @Override
    protected void onLooperPrepared() {
        handler2 = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == MESSAGE_1 ) {
                    int num = (int) msg.obj;
                    for (i = num; i >= 0; i--) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                callBack.sentback(String.valueOf(i));
                            }

                        });

                        try {
                            Log.d("Zin","value 1 : " + i);
                            sleep(1000);
//                            Log.d("Zin","value 2 : " + i);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }

            }
        };
    }

    public void addMessage(Integer num) {
       Message msg = handler2.obtainMessage(MESSAGE_1,num);
        msg.sendToTarget();
    }

}
