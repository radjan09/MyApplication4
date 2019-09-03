package com.example.myapplication4;

import android.annotation.TargetApi;
import android.app.*;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

import java.io.*;
import java.util.Iterator;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.example.myapplication4.Discover;
import android.view.View.OnTouchListener;
import android.view.View.OnClickListener;

public class MainActivity extends Activity {
    private Discover d;
    private MainActivity a;
    private Intent i;
    private List<Device>  devicesConnected=null;
    private static final String CHANNEL_ID ="test" ;
    private TextView t;
    @TargetApi(Build.VERSION_CODES.O)
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void createNotificationChannel(){

            NotificationChannel serviceChannel=new NotificationChannel(
                    CHANNEL_ID,"test",NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager=getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel);

    }
    public void printIp(){

        setContentView(R.layout.iptable);
        TableLayout table = new TableLayout(this);
        Iterator it=devicesConnected.iterator();
        for(int i=0; i<devicesConnected.size(); i++) {
            Device d=(Device)it.next();
            TableRow row = new TableRow(this);

            TextView ip = new TextView(this);
            ip.setText(d.getIp()
            );
            row.addView(ip);
            TextView type = new TextView(this);
            type.setText(d.getName());
            row.addView(type);
            table.addView(row);
        }
        this.setContentView(table);
    }
    public void startService(View v){

        String input=t.getText().toString();
        Intent serviceIntent=new Intent(this,Discover.class);
        serviceIntent.putExtra("inputExtra",input);
        startService(serviceIntent);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onCreate( Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.iptable);
//Unmark
        Message m1=new Message(MainActivity.this);
        MessageReceiver receiver=new MessageReceiver(m1);
        Intent intent=new Intent(this,Discover.class);
        intent.putExtra("time",10);
        intent.putExtra("receiver",receiver);
        startService(intent);


        //Unmarkt=findViewById(R.id.notTest);
        //UnmarkcreateNotificationChannel();
        //UnmarkstartService(t);


        }

}
