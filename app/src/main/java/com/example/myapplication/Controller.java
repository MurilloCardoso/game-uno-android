package com.example.myapplication;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.security.auth.callback.Callback;

public class Controller extends AsyncTask<Void, Void, Socket> {
    private Socket socket;
    private Handler handler = new Handler(Looper.getMainLooper());
    public interface Callback {
        void onSocketReady(Socket socket);
    }



    @Override
    protected Socket doInBackground(Void... voids) {
        try {
            socket = new Socket("192.168.100.94", 4000);
            return socket;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Socket socket) {
        if (socket != null) {
            // Connection successful

        } else {
            // Connection failed, handle the error
        }
    }

    public void sendStringToServer(String messagem,Context cont ) {
        if (socket != null && socket.isConnected()) {
            try {
                PrintStream saida=new PrintStream(socket.getOutputStream());
                saida.println(messagem);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(cont, "Mensagem do servidor: " + messagem, Toast.LENGTH_LONG).show();
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
                // Handle the error
            }
        }
    }

    public boolean isConnected() {
        return socket != null && socket.isConnected();
    }
}

