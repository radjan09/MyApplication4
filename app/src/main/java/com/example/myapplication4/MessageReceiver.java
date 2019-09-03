package com.example.myapplication4;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

public class MessageReceiver extends ResultReceiver {
    private Message message;

    public MessageReceiver(Handler handler) {
        super(handler);
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        message.displayMessage(resultCode, resultData);
    }

    public MessageReceiver(Message message) {
        super(new Handler());
        this.message = message;
    }
/*
    protected void onHandleIntent(Intent intent
    ){
        int time=intent.getIntExtra("time",0);
        for(int i=0;i<time;i++){
            try{
                Thread.sleep(1000);
            }
            catch (Exception e){

            }
        }
    }
}*/
}
