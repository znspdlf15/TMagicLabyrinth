package com.tWilliam.MagicLabyrinth.TGameThread;

import android.util.Log;

import com.tWilliam.MagicLabyrinth.Activities.PopUp.OnlineSearchActivity;
import com.tWilliam.MagicLabyrinth.TLibrary.TConstant;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class TInitThread extends Thread {
    Socket sock = null;
    BufferedReader br = null;
    PrintWriter pw = null;

    public TInitThread(){

    }

    @Override
    public void run(){
        try {
            sock = new Socket(TConstant.serverIP, TConstant.serverPort);//아아디,포트
            Log.i("client", "successful connect to ip:" + TConstant.serverIP);
            pw = new PrintWriter(new OutputStreamWriter(sock.getOutputStream()));
            br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

            pw.flush();
            TClientThread it = new TClientThread(sock, br);
            it.start();
            String line = null;

        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(pw != null) {
                    pw.close();
                }
                if(br != null) {
                    br.close();
                }
                if(sock != null) {
                    sock.close();
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
