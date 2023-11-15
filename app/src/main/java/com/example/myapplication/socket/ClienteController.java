package com.example.myapplication.socket;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class ClienteController extends AsyncTask<Void, String, Socket> {

    private Socket socket;
    public Context contexto;

    public Context getContexto() {
        return contexto;
    }

    public void setContexto(Context contexte) {
        this.contexto = contexte;
    }

    private Handler handler;

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public interface Callback {
        void onSocketReady(Socket socket);
    }



    private BufferedReader inputReader;


    @Override
    protected Socket doInBackground(Void... voids) {
        try {
            socket = new Socket("192.168.100.94", 4000);
            inputReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            startReceivingMessages(); // Move the call to startReceivingMessages to doInBackground
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
            startReceivingMessages();
        } else {
            // Connection failed, handle the error
        }
    }

    private void startReceivingMessages() {
        try {
            while (socket.isConnected()) {
                String message = inputReader.readLine();
                if (message != null) {
                    publishProgress(message); // Move this line to doInBackground
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void onProgressUpdate(String... values) {
        showMessageFromServer(values[0]); // Update UI on the main thread
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

    private void showMessageFromServer(String message) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(contexto, "Mensagem do servidor: " + message, Toast.LENGTH_LONG).show();
            }
        });
    }
}

