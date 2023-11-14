package com.example.myapplication;

import android.os.AsyncTask;

import java.io.IOException;
import java.net.Socket;

public class Controller extends AsyncTask<Void, Void, Socket> {
    @Override
    protected Socket doInBackground(Void... voids) {
        try {
            return new Socket("192.168.100.1", 4000);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Socket socket) {
        // Handle the result, for example, show a Toast message or continue with your app logic
        if (socket != null) {
            // Connection successful, continue with your logic
        } else {
            // Connection failed, handle the error
        }
    }
}
