package com.example.myapplication;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer extends Thread {
    private ServerSocket serverSocket;

    public SocketServer(int port) {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            Socket clientSocket = serverSocket.accept(); // Aguarda até que um cliente se conecte

            // Agora você pode usar o clientSocket para ler/escrever dados
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.println("Conexão estabelecida com o servidor.");

            // Feche as conexões quando terminar
            out.close();
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
