package com.example.myapplication;

import java.io.*;
import java.net.*;

public class LocalServer {
    public static void loadServer()throws IOException {
        ServerSocket server=new ServerSocket(4000);
        Socket socket=server.accept();
        System.out.println("Cliente Conectou");

         InputStreamReader inputReader=new InputStreamReader(socket.getInputStream());
         PrintStream saida= new PrintStream(socket.getOutputStream());
         BufferedReader reader=new BufferedReader(inputReader);
         String x;
         while((x=reader.readLine())!=null){
             saida.println("Servidor: "+ x);
         }
    }
}