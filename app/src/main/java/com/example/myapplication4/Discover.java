package com.example.myapplication4;

import android.annotation.SuppressLint;
import android.app.*;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.DhcpInfo;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.*;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.View;
import android.widget.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.*;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.*;

import androidx.core.app.NotificationCompat;
import com.example.myapplication4.emergencyProtocol;
import com.example.myapplication4.Device;
import android.text.format.Formatter;
import org.w3c.dom.Text;

import static androidx.core.content.ContextCompat.getSystemService;
/*Overview: Task wich discover the devices connected to the same network and that finally store the info
under a List of Device type class that are under format<ip,port,name>
*/
public class Discover extends IntentService implements AsyncResponse {

    //List of device must be public so activity can acess it in order to print ip
    private List<Device> devices;
    private runScan asyncTask =null;
    private Discover d=this;
    private void notify(String msg){

    }
    public List<Device> getDevices(){
        return devices;
    }

    public int getNumberDevicesDiscovered(){
        return devices.size();
    }

    public Discover(){
        super("Timer Service");

    }
    public Discover getMyClass(){
        return Discover.this;
    }

   /* @Override
    public void processFinish(String output) {
        try {
            getDevicesConnected();
        }
        catch(Exception e){
            notify(e.getMessage());
        }
        //need to restart indefinitly
    }*/
//Methods override from Android Service
    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int flagId) {

        super.onStartCommand(intent,flags,flagId);
       //asyncTask=new runScan(this,intent);
        asyncTask.delegate = this;
      //  asyncTask.intent=intent;
        return START_STICKY;
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        try {
         //   asyncTask.execute();
        }
        catch(Exception e){

        }

    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void processFinish(String output) {

    }


    //end
    private  class runScan extends AsyncTask<Void, Void, Void> {
        private static final String TAG = "NetworkSniffTask";
        public AsyncResponse delegate = null;
        private WeakReference<Context> mContextRef;
        private Intent intent=null;


        public runScan(Context context,Intent intent){
            mContextRef = new WeakReference<>(context);
            this.intent=intent;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            delegate.processFinish("test");

        }
        @Override
        public Void doInBackground(Void... voids) {
            try {
                Context context =mContextRef.get();;
                 if(context==null){

                     return null;
                }
                if (context != null) {
                    ResultReceiver receiver=intent.getParcelableExtra("receiver");


                    Bundle bundle=new Bundle();
                    bundle.putString("message","test scan.");
                    receiver.send(1234,bundle);

                    ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                    NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                    WifiManager wm = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                    WifiInfo connectionInfo = wm.getConnectionInfo();
                    int ipAddress = connectionInfo.getIpAddress();
                    String ipString = Formatter.formatIpAddress(ipAddress);
                    String prefix = ipString.substring(0, ipString.lastIndexOf(".") + 1);
                    for (int i = 0; i < 255; i++) {
                        String testIp = prefix + String.valueOf(i);
                        InetAddress name = InetAddress.getByName(testIp);
                        String hostName = name.getCanonicalHostName();
                        float level=(i*100/255);
                        Toast.makeText(d,String.valueOf(Math.round(level)),Toast.LENGTH_LONG);
                        if (name.isReachable(1000)) {
                            devices.add(new Device(testIp, 8002, hostName));
                        }
                    }

                }
            }
            catch (Exception t) {
               return null;
            }

        return null;
        }
    }
}
