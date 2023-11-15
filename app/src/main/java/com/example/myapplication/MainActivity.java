package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout.LayoutParams;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.Object.Card;
import com.example.myapplication.Object.Utils;
import com.example.myapplication.socket.ClienteController;
import com.example.myapplication.socket.ServerController;

import android.app.AlertDialog;
import android.content.DialogInterface;

import android.view.LayoutInflater;

import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //Sockets
    private ClienteController clienteController;
    private ServerController serverController;
    android.os.Handler handler = new Handler(Looper.getMainLooper());

    public static List<Card> DeckPlayer = new ArrayList<Card>(Utils.NewCardsDeck());

    Card tablePending;

    public static LinearLayout deckPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);

        if (pref.getBoolean("KEY_SERVER", false)) {
            //Server
            serverController = new ServerController();
            serverController.setHandler(handler);
            serverController.setContext(MainActivity.this);
            serverController.onServer();
        } else {
            //Cliente
            new InitClienteControllerTask().execute();
        }


        deckPlayer = (LinearLayout) findViewById(R.id.idPlayer);

        LinearLayout table = findViewById(R.id.idTable);
        LinearLayout deckEnemy = (LinearLayout) findViewById(R.id.idEnemy);
        LinearLayout cardBuy = findViewById(R.id.idBuy);

        Card card = Utils.GerarCard();

        TextView textView2 = new TextView(MainActivity.this);
        textView2.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        textView2.setText("" + card.getNumber());
        textView2.setTextSize(20);
        textView2.setGravity(Gravity.CENTER);
        textView2.setRotation(90);
        if (card.getColor() == 0) {
            //verde
            textView2.setBackgroundColor(Color.argb(255, 0, 128, 51));
        }
        if (card.getColor() == 1) {
            ///azul
            textView2.setBackgroundColor(Color.argb(255, 45, 76, 189));
        }
        if (card.getColor() == 2) {
            //vermelho
            textView2.setBackgroundColor(Color.argb(255, 179, 71, 46));
        }
        if (card.getColor() == 3) {
            //amarelo
            textView2.setBackgroundColor(Color.argb(255, 224, 217, 70));
        }
        tablePending = card;
        textView2.setTextColor(Color.argb(255, 255, 255, 255));
        textView2.setPadding(50, 50, 50, 20);// in pixels (left, top, right, bottom)

        table.addView(textView2);


        for (int i = 0; i < 7; i++) {
            TextView textView1 = new TextView(this);
            textView1.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            textView1.setText("" + DeckPlayer.get(i).getNumber());
            textView1.setTextSize(20);
            textView1.setId(DeckPlayer.get(i).getId());
            textView1.setGravity(Gravity.CENTER);
            textView1.setRotation(90);
            if (DeckPlayer.get(i).getColor() == 0) {
                //verde
                textView1.setBackgroundColor(Color.argb(255, 0, 128, 51));
            }
            if (DeckPlayer.get(i).getColor() == 1) {
                ///azul
                textView1.setBackgroundColor(Color.argb(255, 45, 76, 189));
            }
            if (DeckPlayer.get(i).getColor() == 2) {
                //vermelho
                textView1.setBackgroundColor(Color.argb(255, 179, 71, 46));
            }
            if (DeckPlayer.get(i).getColor() == 3) {
                //amarelo
                textView1.setBackgroundColor(Color.argb(255, 224, 217, 70));
            }

            textView1.setTextColor(Color.argb(255, 255, 255, 255));
            textView1.setPadding(50, 50, 50, 20);// in pixels (left, top, right, bottom)

            textView1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    String mensagem = "Sua mensagem aqui";
//                    if (controle != null && controle.isConnected()) {
//                        controle.sendStringToServer(mensagem,MainActivity.this);
//                    }
                    for (int j = 0; j < DeckPlayer.size(); j++) {
                        Card aux = DeckPlayer.get(j);
                        if (tablePending.getNumber() == aux.getNumber() || tablePending.getColor()==aux.getColor()) {
                            if (aux.getId() == textView1.getId()) {
                                tablePending=aux;
                                textView2.setLayoutParams(textView1.getLayoutParams());
                                textView2.setText(textView1.getText());
                                textView2.setTextSize(textView1.getTextSize());
                                textView2.setGravity(textView1.getGravity());
                                textView2.setRotation(textView1.getRotation());
                                textView2.setTextColor(textView1.getTextColors());
                                textView2.setPadding(textView1.getPaddingLeft(), textView1.getPaddingTop(), textView1.getPaddingRight(), textView1.getPaddingBottom());
                                Drawable backgroundDrawable = textView1.getBackground();
                                ColorDrawable colorDrawable = (ColorDrawable) backgroundDrawable;
                                int backgroundColor = colorDrawable.getColor();
                                textView2.setBackgroundColor(backgroundColor);
                                DeckPlayer.remove(aux);
                                deckPlayer.removeView(textView1);

                                if (DeckPlayer.size() == 0) {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                    LayoutInflater inflater = getLayoutInflater();
                                    View dialogView = inflater.inflate(R.layout.modal_win, null);
                                    builder.setView(dialogView);
                                    builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });
                                    AlertDialog dialog = builder.create();
                                    dialog.show();
                                }
                            }
                        }

                    }

                }
            });
            deckPlayer.addView(textView1);
        }
        for (int i = 0; i < 7; i++) {
            TextView textView1 = new TextView(this);
            textView1.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

            textView1.setTextSize(20);
            textView1.setId(DeckPlayer.get(i).getId());
            textView1.setGravity(Gravity.CENTER);
            textView1.setRotation(90);
            textView1.setBackgroundColor(Color.argb(255, 0, 0, 0));

            textView1.setTextColor(Color.argb(255, 255, 255, 255));
            textView1.setPadding(50, 50, 50, 20);// in pixels (left, top, right, bottom)

            deckEnemy.addView(textView1);
        }
        cardBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage();
//                Card card = Utils.buyCards();
//                DeckPlayer.add(card);
//                TextView textViewBuy = new TextView(MainActivity.this);
//                textViewBuy.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
//                textViewBuy.setText("" + card.getNumber());
//                textViewBuy.setTextSize(20);
//                textViewBuy.setId(card.getId());
//                textViewBuy.setGravity(Gravity.CENTER);
//                textViewBuy.setRotation(90);
//                if (card.getColor() == 0) {
//                    //verde
//                    textViewBuy.setBackgroundColor(Color.argb(255, 0, 128, 51));
//                }
//                if (card.getColor() == 1) {
//                    ///azul
//                    textViewBuy.setBackgroundColor(Color.argb(255, 45, 76, 189));
//                }
//                if (card.getColor() == 2) {
//                    //vermelho
//                    textViewBuy.setBackgroundColor(Color.argb(255, 179, 71, 46));
//                }
//                if (card.getColor() == 3) {
//                    //amarelo
//                    textViewBuy.setBackgroundColor(Color.argb(255, 224, 217, 70));
//                }
//
//                textViewBuy.setTextColor(Color.argb(255, 255, 255, 255));
//                textViewBuy.setPadding(50, 50, 50, 20);// in pixels (left, top, right, bottom)
//
//                textViewBuy.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//
//                        for (int j = 0; j < DeckPlayer.size(); j++) {
//                            Card aux = DeckPlayer.get(j);
//                            if (tablePending.getNumber() == aux.getNumber() || tablePending.getColor()==aux.getColor()) {
//                                if (aux.getId() == textViewBuy.getId()) {
//                                    tablePending=aux;
//                                    textViewBuy.setLayoutParams(textViewBuy.getLayoutParams());
//                                    textView2.setText(textViewBuy.getText());
//                                    textView2.setTextSize(textViewBuy.getTextSize());
//                                    textView2.setGravity(textViewBuy.getGravity());
//                                    textView2.setRotation(textViewBuy.getRotation());
//                                    textView2.setTextColor(textViewBuy.getTextColors());
//                                    textView2.setPadding(textViewBuy.getPaddingLeft(), textViewBuy.getPaddingTop(), textViewBuy.getPaddingRight(),textViewBuy.getPaddingBottom());
//                                    Drawable backgroundDrawable = textViewBuy.getBackground();
//                                    ColorDrawable colorDrawable = (ColorDrawable) backgroundDrawable;
//                                    int backgroundColor = colorDrawable.getColor();
//                                    textView2.setBackgroundColor(backgroundColor);
//                                    DeckPlayer.remove(aux);
//                                    deckPlayer.removeView(textViewBuy);
//
//
//                                    if (DeckPlayer.size() == 0) {
//                                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//                                        LayoutInflater inflater = getLayoutInflater();
//                                        View dialogView = inflater.inflate(R.layout.modal_win, null);
//                                        builder.setView(dialogView);
//                                        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
//                                            @Override
//                                            public void onClick(DialogInterface dialog, int which) {
//                                                dialog.dismiss();
//                                            }
//                                        });
//                                        AlertDialog dialog = builder.create();
//                                        dialog.show();
//                                    }
//                                }
//                            }
//
//                        }
//                    }
//                });
//                deckPlayer.addView(textViewBuy);
          }
        });

    }
    private void sendMessage() {
        String message = "awdawdawdawdm";
        if (!message.isEmpty()) {
            new SendMessageTask().execute(message);
        }
    }

    private class SendMessageTask extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... messages) {
            if (clienteController != null) {
                for (String message : messages) {
                    clienteController.sendStringToServer(message, MainActivity.this);
                }
            }
            return null;
        }
    }
    // AsyncTask para inicializar o ClienteController
    private class InitClienteControllerTask extends AsyncTask<Void, Void, Void> {
        @SuppressLint("WrongThread")
        @Override
        protected Void doInBackground(Void... voids) {
            // Inicializar o ClienteController
            clienteController = new ClienteController();
            clienteController.setContexto(MainActivity.this);
            clienteController.setHandler(new Handler(Looper.getMainLooper()));
            clienteController.execute();
            return null;
        }
    }
}