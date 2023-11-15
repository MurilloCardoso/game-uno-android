package com.example.myapplication.socket;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import java.io.*;
import java.net.Socket;

public class ServerController {
    private Context context;
    private Socket socket;

    private android.os.Handler handler;

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public void onServer() {
        Thread serverThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    java.net.ServerSocket server = new java.net.ServerSocket(4000);

                    while (true) {
                        Socket socket = server.accept();
                        setSocket(socket);
                        // Use a Handler to post a Runnable to the main thread
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(context, "Cliente conectou", Toast.LENGTH_LONG).show();
                            }
                        });

                        handleClient(socket);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        serverThread.start();
    }

    public void handleClient(Socket clientSocket) {
        try {
            PrintWriter outputWriter = new PrintWriter(clientSocket.getOutputStream(), true);

            // Send a welcome message to the client
            outputWriter.println("Bem-vindo ao servidor!");

            BufferedReader inputReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            while (true) {
                String message = inputReader.readLine();
                if (message != null) {
                    showMessageFromClient(message);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showMessageFromClient(String message) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, "Mensagem do cliente: " + message, Toast.LENGTH_LONG).show();
            }
        });
    }



}
