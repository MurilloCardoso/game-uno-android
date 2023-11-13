package com.example.myapplication;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout.LayoutParams;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Object.Card;
import com.example.myapplication.Object.Utils;

import android.app.AlertDialog;
import android.content.DialogInterface;

import org.w3c.dom.Text;

import android.view.LayoutInflater;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    public static List<Card> DeckPlayer = new ArrayList<Card>(Utils.NewCardsDeck());

    Card tablePending;
    public LinearLayout deckPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);

//        if (pref.getBoolean("KEY_SERVER", false)) {
//            // Crie um novo Thread para iniciar o servidor
//            Thread serverThread = new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        LocalServer.loadServer();
//                    } catch (IOException e) {
//                        throw new RuntimeException(e);
//                    }
//                }
//            });
//
//            serverThread.start(); // Inicie o servidor em segundo plano
//        } else {
//            Controller.Choose();
//        }

        deckPlayer = (LinearLayout) findViewById(R.id.idPlayer);

        LinearLayout table = findViewById(R.id.idTable);
        LinearLayout deckEnemy = (LinearLayout) findViewById(R.id.idEnemy);
        LinearLayout cardBuy = findViewById(R.id.idBuy);

        Card card = Utils.GerarCard();

        TextView textView2 = new TextView(MainActivity.this);
        textView2.setLayoutParams(new LayoutParams(140, 180));
        textView2.setTextSize(25);
        textView2.setTypeface(null, Typeface.BOLD);
        textView2.setText("" + card.getNumber());
        textView2.setGravity(Gravity.CENTER);

        if (card.getColor() == 0) {
            //verde
            textView2.setBackgroundColor(Color.argb(255, 0, 128, 51));
            textView2.setBackgroundResource(R.drawable.greencard);
        }
        if (card.getColor() == 1) {
            ///azul
            textView2.setBackgroundColor(Color.argb(255, 45, 76, 189));
            textView2.setBackgroundResource(R.drawable.bluecard);
        }
        if (card.getColor() == 2) {
            //vermelho
            textView2.setBackgroundColor(Color.argb(255, 179, 71, 46));
            textView2.setBackgroundResource(R.drawable.redcard);
        }
        if (card.getColor() == 3) {
            //amarelo
            textView2.setBackgroundColor(Color.argb(255, 224, 217, 70));
            textView2.setBackgroundResource(R.drawable.yellowcard);
        }

        tablePending = card;
        textView2.setTextColor(Color.argb(255, 255, 255, 255));
        textView2.setPadding(50, 50, 50, 50);

        table.addView(textView2);

        for (int i = 0; i < 7; i++) {
            TextView textView1 = new TextView(this);
            LayoutParams params = new LayoutParams(140, 180);
            params.setMargins(20, 0, 10, 0);
            textView1.setLayoutParams(params);
            textView1.setTextSize(25);
            textView1.setTypeface(null, Typeface.BOLD);
            textView1.setGravity(Gravity.CENTER);

            textView1.setText("" + DeckPlayer.get(i).getNumber());
            textView1.setId(DeckPlayer.get(i).getId());

            if (DeckPlayer.get(i).getColor() == 0) {
                //verde
                textView1.setBackgroundColor(Color.argb(255, 0, 128, 51));
                textView1.setBackgroundResource(R.drawable.greencard);
            }
            if (DeckPlayer.get(i).getColor() == 1) {
                ///azul
                textView1.setBackgroundColor(Color.argb(255, 45, 76, 189));
                textView1.setBackgroundResource(R.drawable.bluecard);
            }
            if (DeckPlayer.get(i).getColor() == 2) {
                //vermelho
                textView1.setBackgroundColor(Color.argb(255, 179, 71, 46));
                textView1.setBackgroundResource(R.drawable.redcard);
            }
            if (DeckPlayer.get(i).getColor() == 3) {
                //amarelo
                textView1.setBackgroundColor(Color.argb(255, 224, 217, 70));
                textView1.setBackgroundResource(R.drawable.yellowcard);
            }

            textView1.setTextColor(Color.argb(255, 255, 255, 255));
            textView1.setPadding(50, 50, 50, 50);

            textView1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    for (int j = 0; j < DeckPlayer.size(); j++) {
                        Card aux = DeckPlayer.get(j);
                        if (tablePending.getNumber() == aux.getNumber() || tablePending.getColor() == aux.getColor()) {
                            if (aux.getId() == textView1.getId()) {
                                tablePending = aux;
                                textView2.setText(textView1.getText());
                                textView2.setGravity(textView1.getGravity());
                                textView2.setTextColor(textView1.getTextColors());
                                textView2.setPadding(textView1.getPaddingLeft(), textView1.getPaddingTop(), textView1.getPaddingRight(), textView1.getPaddingBottom());

                                Drawable backgroundDrawable = textView1.getBackground();
                                textView2.setBackground(backgroundDrawable);

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
            LayoutParams params = new LayoutParams(140, 180);
            textView1.setLayoutParams(params);

            textView1.setTextSize(25);
            textView1.setId(DeckPlayer.get(i).getId());
            textView1.setGravity(Gravity.CENTER);
            textView1.setBackgroundColor(Color.argb(255, 0, 0, 0));
            textView1.setBackgroundResource(R.drawable.backcard);

            textView1.setTextColor(Color.argb(255, 255, 255, 255));
            textView1.setPadding(50, 50, 50, 20);// in pixels (left, top, right, bottom)

            deckEnemy.addView(textView1);
        }

        cardBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Card card = Utils.buyCards();
                DeckPlayer.add(card);

                TextView textViewBuy = new TextView(MainActivity.this);
                LayoutParams params = new LayoutParams(140, 180);
                params.setMargins(10, 0, 10, 0);
                textViewBuy.setLayoutParams(params);
                textViewBuy.setText("" + card.getNumber());
                textViewBuy.setTextSize(25);
                textViewBuy.setId(card.getId());
                textViewBuy.setTypeface(null, Typeface.BOLD);
                textViewBuy.setGravity(Gravity.CENTER);

                if (card.getColor() == 0) {
                    //verde
                    textViewBuy.setBackgroundColor(Color.argb(255, 0, 128, 51));
                    textViewBuy.setBackgroundResource(R.drawable.greencard);
                }
                if (card.getColor() == 1) {
                    ///azul
                    textViewBuy.setBackgroundColor(Color.argb(255, 45, 76, 189));
                    textViewBuy.setBackgroundResource(R.drawable.bluecard);
                }
                if (card.getColor() == 2) {
                    //vermelho
                    textViewBuy.setBackgroundColor(Color.argb(255, 179, 71, 46));
                    textViewBuy.setBackgroundResource(R.drawable.redcard);
                }
                if (card.getColor() == 3) {
                    //amarelo
                    textViewBuy.setBackgroundColor(Color.argb(255, 224, 217, 70));
                    textViewBuy.setBackgroundResource(R.drawable.yellowcard);
                }

                textViewBuy.setTextColor(Color.argb(255, 255, 255, 255));
                textViewBuy.setPadding(50, 50, 50, 50);// in pixels (left, top, right, bottom)

                textViewBuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        for (int j = 0; j < DeckPlayer.size(); j++) {
                            Card aux = DeckPlayer.get(j);
                            if (tablePending.getNumber() == aux.getNumber() || tablePending.getColor() == aux.getColor()) {
                                if (aux.getId() == textViewBuy.getId()) {
                                    tablePending = aux;
                                    textView2.setText(textViewBuy.getText());
                                    textView2.setGravity(textViewBuy.getGravity());
                                    textView2.setTextColor(textViewBuy.getTextColors());
                                    textView2.setPadding(textViewBuy.getPaddingLeft(), textViewBuy.getPaddingTop(), textViewBuy.getPaddingRight(), textViewBuy.getPaddingBottom());

                                    Drawable backgroundDrawable = textViewBuy.getBackground();
                                    textView2.setBackground(backgroundDrawable);


//                                    Drawable backgroundDrawable = textViewBuy.getBackground();
//                                    ColorDrawable colorDrawable = (ColorDrawable) backgroundDrawable;

                                    DeckPlayer.remove(aux);
                                    deckPlayer.removeView(textViewBuy);

//                                    int backgroundColor = colorDrawable.getColor();
//                                    textView2.setBackgroundColor(backgroundColor);

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
                deckPlayer.addView(textViewBuy);
            }
        });

    }
}