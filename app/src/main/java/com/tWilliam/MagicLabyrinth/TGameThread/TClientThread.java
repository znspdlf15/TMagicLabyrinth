package com.tWilliam.MagicLabyrinth.TGameThread;

import android.util.Log;

import java.io.BufferedReader;
import java.net.Socket;

public class TClientThread extends Thread{
    private Socket sock = null;
    private BufferedReader br = null;

    public TClientThread(Socket sock, BufferedReader br) {
        this.sock = sock;
        this.br = br;
    }
    
    @Override
    public void run() {
        try {
            while ( true ) {
                Thread.sleep(5000);
                Log.i("client", "waiting response from server...");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
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
