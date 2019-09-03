package com.example.myapplication4;

import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.net.Socket;
//Class that can open, transfert date and close a connection
public class emergencyProtocol extends Socket {
    private String host;
    private int port;
    private Discover d;

    public emergencyProtocol(String ip, int port,Discover d) throws Exception{

        this.host=host;
        this.port=port;
        this.d=d;
    }



    public void openConnection() throws IOException {
        System.setProperty("javax.net.ssl.trustStore", "clienttrust");
        SSLSocketFactory ssf = (SSLSocketFactory) SSLSocketFactory.getDefault();
        Socket s = ssf.createSocket(host, port);

       /* OutputStream outs = s.getOutputStream();
        PrintStream out = new PrintStream(outs);
        InputStream ins = s.getInputStream();
        BufferedReader in = new BufferedReader(new InputStreamReader(ins));

        out.println("Hi,How are u!");
        out.println("");
        String line = null;
        while ((line = in.readLine()) != null) {
            System.out.println(line);
        }

        in.close();
        out.close();*/
    }
    public String sendData(String data) throws Exception{
        BufferedOutputStream bos = new BufferedOutputStream(getOutputStream());
        bos.write(data.getBytes());
        bos.flush();
        BufferedInputStream bis = new BufferedInputStream(getInputStream());
        String content = "";
        int stream;
        byte[] b = new byte[1024];
        while((stream = bis.read(b)) != -1){
            content += new String(b, 0, stream);
        }

        return content;
    }
   public void closeConnection()throws Exception{
        close();
   }
}
