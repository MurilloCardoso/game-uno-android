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

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    List<Card> deckPlayer = new ArrayList<Card>(Utils.NewCardsDeck());
    Card tablePending;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.idPlayer);
        LinearLayout table = findViewById(R.id.idTable);

        Card card = Utils.buyCards();
        deckPlayer.add(Utils.buyCards());
        TextView textView2 = new TextView(MainActivity.this);
        textView2.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        textView2.setText("" + card.getNumber());
        textView2.setTextSize(20);
        textView2.setGravity(Gravity.CENTER);
        textView2.setRotation(90);
        if (card.getColor()== 0) {
            //verde
            textView2.setBackgroundColor(Color.argb(255, 0, 128, 51));
        }
        if (card.getColor()== 1) {
            ///azul
            textView2.setBackgroundColor(Color.argb(255, 45, 76, 189));
        }
        if (card.getColor()== 2) {
            //vermelho
            textView2.setBackgroundColor(Color.argb(255, 179, 71, 46));
        }
        if (card.getColor()== 3) {
            //amarelo
            textView2.setBackgroundColor(Color.argb(255, 224, 217, 70));
        }
        tablePending = card;
        textView2.setTextColor(Color.argb(255, 255, 255, 255));
        textView2.setPadding(50, 50, 50, 20);// in pixels (left, top, right, bottom)

        table.addView(textView2);

        for (int i = 0; i <7; i++) {
            // Add textview 1
            TextView textView1 = new TextView(this);
            textView1.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            textView1.setText("" + i);

            final int cardIndex = i;

            textView1.setTextSize(20);
            textView1.setGravity(Gravity.CENTER);
            textView1.setRotation(90);
            if (deckPlayer.get(i).getColor()== 0) {
                //verde
                textView1.setBackgroundColor(Color.argb(255, 0, 128, 51));
            }
            if (deckPlayer.get(i).getColor() == 1) {
                ///azul
                textView1.setBackgroundColor(Color.argb(255, 45, 76, 189));
            }
            if (deckPlayer.get(i).getColor() == 2) {
                //vermelho
                textView1.setBackgroundColor(Color.argb(255, 179, 71, 46));
            }
            if (deckPlayer.get(i).getColor()== 3) {
                //amarelo
                textView1.setBackgroundColor(Color.argb(255, 224, 217, 70));
            }

            // hex color 0xAARRGGBB
            textView1.setTextColor(Color.argb(255, 255, 255, 255));
            textView1.setPadding(50, 50, 50, 20);// in pixels (left, top, right, bottom)
            textView1.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    Card card  = deckPlayer.get(cardIndex);
                  
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
                    linearLayout.removeView(textView1);      deckPlayer.remove(card);
                    Toast toast = Toast.makeText(MainActivity.this, "num: "+ (deckPlayer.size()-1), Toast.LENGTH_SHORT);
                    toast.setDuration(Toast.LENGTH_SHORT); // Defina a duração em milissegundos (neste caso, 5 segundos)
                    toast.show();

                }
            });

            linearLayout.addView(textView1);
        }

        LinearLayout cardBuy = findViewById(R.id.idBuy);


        cardBuy.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Card card = Utils.buyCards();
                final int cardIndex = deckPlayer.size();
                card.setId(deckPlayer.size());
                deckPlayer.add(Utils.buyCards());

                TextView textView1 = new TextView(MainActivity.this);
                textView1.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                textView1.setText("" + card.getNumber());

                textView1.setTextSize(20);
                textView1.setGravity(Gravity.CENTER);
                textView1.setRotation(90);

                if (card.getColor()== 0) {
                    //verde
                    textView1.setBackgroundColor(Color.argb(255, 0, 128, 51));
                }
                if (card.getColor()== 1) {
                    ///azul
                    textView1.setBackgroundColor(Color.argb(255, 45, 76, 189));
                }
                if (card.getColor()== 2) {
                    //vermelho
                    textView1.setBackgroundColor(Color.argb(255, 179, 71, 46));
                }
                if (card.getColor()== 3) {
                    //amarelo
                    textView1.setBackgroundColor(Color.argb(255, 224, 217, 70));
                }

                // hex color 0xAARRGGBB
                textView1.setTextColor(Color.argb(255, 255, 255, 255));
                textView1.setPadding(50, 50, 50, 20);// in pixels (left, top, right, bottom)
                textView1.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {



                            textView2.setLayoutParams(textView1.getLayoutParams());
                            textView2.setText(textView1.getText());
                            textView2.setTextSize(textView1.getTextSize());
                            textView2.setGravity(textView1.getGravity());
                            textView2.setRotation(textView1.getRotation());
                            textView2.setTextColor(textView1.getTextColors());
                            textView2.setPadding(textView1.getPaddingLeft(), textView1.getPaddingTop(), textView1.getPaddingRight(), textView1.getPaddingBottom());
                            Drawable backgroundDrawable = textView1.getBackground();
                            ColorDrawable colorDrawable = (ColorDrawable) backgroundDrawable;
                            deckPlayer.remove(cardIndex);
                            // Obtenha a cor de fundo
                            int backgroundColor = colorDrawable.getColor();
                            textView2.setBackgroundColor(backgroundColor);
                            linearLayout.removeView(textView1);


                            if(deckPlayer.size()==0){
                            Toast toast = Toast.makeText(MainActivity.this, "acabou", Toast.LENGTH_SHORT);
                            toast.setDuration(Toast.LENGTH_SHORT); // Defina a duração em milissegundos (neste caso, 5 segundos)
                            toast.show();}

                    }
                });
                linearLayout.addView(textView1);
            }
        });

    }
}