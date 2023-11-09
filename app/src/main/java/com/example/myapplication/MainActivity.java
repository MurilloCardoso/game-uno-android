package com.example.myapplication;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.View;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    public static List<Card> DeckPlayer = new ArrayList<Card>(Utils.NewCardsDeck());

    Card tablePending;
    public static LinearLayout deckPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

       deckPlayer = (LinearLayout) findViewById(R.id.idPlayer);

        LinearLayout  table = findViewById(R.id.idTable);
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
            textView1.setText("" + DeckPlayer.get(i).getId());
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

                    if(deckPlayer.isClickable()){
                    for (int j = 0; j < DeckPlayer.size(); j++) {
                        Card aux = DeckPlayer.get(j);
                        if (aux.getId() == textView1.getId()) {
                            deckPlayer.setClickable(false);
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

                            if(DeckPlayer.size()==0){
                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                LayoutInflater inflater = getLayoutInflater();
                                View dialogView = inflater.inflate(R.layout.modal_win, null);
                                builder.setView(dialogView);
                                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });     AlertDialog dialog = builder.create();
                                dialog.show();
                            }
                            else{
                                ControllerIA ai=new ControllerIA();
                                ai.Choose(tablePending,MainActivity.this);
                            }
                        }
                    }}

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
                Card card = Utils.buyCards();
                DeckPlayer.add(card);
                TextView textView2 = new TextView(MainActivity.this);
                textView2.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                textView2.setText("" + card.getNumber());
                textView2.setTextSize(20);
                textView2.setId(card.getId());
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

                textView2.setTextColor(Color.argb(255, 255, 255, 255));
                textView2.setPadding(50, 50, 50, 20);// in pixels (left, top, right, bottom)

                textView2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast toast = Toast.makeText(MainActivity.this, "num: ", Toast.LENGTH_SHORT);
                        toast.setDuration(Toast.LENGTH_SHORT); // Defina a duração em milissegundos (neste caso, 5 segundos)
                        toast.show();
                        for (int j = 0; j < DeckPlayer.size(); j++) {
                            Card aux = DeckPlayer.get(j);
                            if (aux.getId() == textView2.getId()) {
                                DeckPlayer.remove(aux);
                                deckPlayer.removeView(textView2);
                            }
                        }
                    }
                });
                deckPlayer.addView(textView2);
            }
        });

    }
}