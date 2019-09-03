package com.example.myapplication4;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;
import com.example.myapplication4.MainActivity;
public class Message{
    private Activity activityMessage=null;
    public Message(Activity activityMessage ){
        this.activityMessage=activityMessage;
    }
    public void displayMessage(int resultCode, Bundle resultData){
        String message=resultData.getString("message");
        Toast.makeText(activityMessage,resultCode+" "+message,Toast.LENGTH_SHORT).show();
    }
}