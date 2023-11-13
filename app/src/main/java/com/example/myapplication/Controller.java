package com.example.myapplication;

import com.example.myapplication.Object.Card;

import android.content.Context;

import java.io.*;
import java.net.*;

public class Controller {

    public static void Choose() {
        try (
                Socket clientSocket = new Socket
                        ("10.0.2.16", 3050);
                DataInputStream inbound = new DataInputStream(clientSocket.getInputStream());
                DataOutputStream outbound = new DataOutputStream(clientSocket.getOutputStream())) {

            outbound.writeInt(3);
            outbound.writeUTF("hello");
            int k = inbound.readInt();
            String s = inbound.readUTF();

            // Process the server's response here (k and s).
            System.out.println("Received: " + k + " " + s);
        } catch (IOException e) {
            System.out.println("Não foi");
            e.printStackTrace(); // Handle the exception appropriately
        }
    }
}

