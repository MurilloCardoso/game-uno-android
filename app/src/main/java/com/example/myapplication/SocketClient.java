package com.example.myapplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class SocketClient extends Thread {
    private String serverIp;
    private int serverPort;

    public SocketClient(String ip, int port) {
        serverIp = ip;
        serverPort = port;
    }

    @Override
    public void run() {
        try {
            Socket socket = new Socket(serverIp, serverPort);

            // Agora você pode usar o socket para ler/escrever dados
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String message = in.readLine();
            System.out.println("Mensagem do servidor: " + message);

            // Feche as conexões quando terminar
            in.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
