package com.example.myapplication;

import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
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
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.idPlayer);
        LinearLayout table = findViewById(R.id.idTable);

        Card card = Utils.buyCards();
        deckPlayer.add(Utils.buyCards());
        TextView textView2 = new TextView(MainActivity.this);
        textView2.setLayoutParams(new LayoutParams(140, 180));
        textView2.setTextSize(25);
        textView2.setTypeface(null, Typeface.BOLD);
        textView2.setText("" + card.getNumber());
        textView2.setGravity(Gravity.CENTER);
//        textView2.setRotation(90);
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
//        textView2.setPadding(50, 50, 50, 50);// in pixels (left, top, right, bottom)

        table.addView(textView2);

        for (int i = 0; i < 7; i++) {
            // Add textview 1
            TextView textView1 = new TextView(this);
            LayoutParams params = new LayoutParams(140,
                    180);
            params.setMargins(20, 0, 10, 0);
            textView1.setLayoutParams(params);
            textView1.setText("" + i);

            final int cardIndex = i;

            textView1.setTextSize(25);
            textView1.setTypeface(null, Typeface.BOLD);
            textView1.setGravity(Gravity.CENTER);

//            textView1.setRotation(90);
            if (deckPlayer.get(i).getColor() == 0) {
                //verde
                textView1.setBackgroundColor(Color.argb(255, 0, 128, 51));
                textView1.setBackgroundResource(R.drawable.greencard);
            }
            if (deckPlayer.get(i).getColor() == 1) {
                ///azul
                textView1.setBackgroundColor(Color.argb(255, 45, 76, 189));
                textView1.setBackgroundResource(R.drawable.bluecard);
            }
            if (deckPlayer.get(i).getColor() == 2) {
                //vermelho
                textView1.setBackgroundColor(Color.argb(255, 179, 71, 46));
                textView1.setBackgroundResource(R.drawable.redcard);
            }
            if (deckPlayer.get(i).getColor() == 3) {
                //amarelo
                textView1.setBackgroundColor(Color.argb(255, 224, 217, 70));
                textView1.setBackgroundResource(R.drawable.yellowcard);
            }

            // hex color 0xAARRGGBB
            textView1.setTextColor(Color.argb(255, 255, 255, 255));
            textView1.setPadding(50, 50, 50, 50);// in pixels (left, top, right, bottom)
            textView1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Card card = deckPlayer.get(cardIndex);

//                    textView2.setLayoutParams(textView1.getLayoutParams());
                    textView2.setText(textView1.getText());
//                    textView2.setTextSize(textView1.getTextSize());
                    textView2.setGravity(textView1.getGravity());
                    textView2.setRotation(textView1.getRotation());
                    textView2.setTextColor(textView1.getTextColors());
                    textView2.setPadding(textView1.getPaddingLeft(), textView1.getPaddingTop(), textView1.getPaddingRight(), textView1.getPaddingBottom());

                    Drawable backgroundDrawable = textView1.getBackground();
                    textView2.setBackground(backgroundDrawable);
//                    ColorDrawable colorDrawable = (ColorDrawable) backgroundDrawable;
//                    int backgroundColor = colorDrawable.getColor();
//                    textView2.setBackgroundColor(backgroundColor);

                    linearLayout.removeView(textView1);
                    deckPlayer.remove(card);
                }
            });

            linearLayout.addView(textView1);
        }

        LinearLayout cardBuy = findViewById(R.id.idBuy);
        cardBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Card card = Utils.buyCards();
                int cardIndex = deckPlayer.size();
                card.setId(deckPlayer.size());
                deckPlayer.add(Utils.buyCards());

                TextView textView1 = new TextView(MainActivity.this);
                LayoutParams params = new LayoutParams(140, 180);
                params.setMargins(10, 0, 10, 0);
                textView1.setLayoutParams(params);
                textView1.setText("" + card.getNumber());
                textView1.setTextSize(25);
                textView1.setTypeface(null, Typeface.BOLD);
                textView1.setGravity(Gravity.CENTER);
//                textView1.setRotation(90);

                if (card.getColor() == 0) {
                    //verde
                    textView1.setBackgroundColor(Color.argb(255, 0, 128, 51));
                    textView1.setBackgroundResource(R.drawable.greencard);
                }
                if (card.getColor() == 1) {
                    ///azul
                    textView1.setBackgroundColor(Color.argb(255, 45, 76, 189));
                    textView1.setBackgroundResource(R.drawable.bluecard);
                }
                if (card.getColor() == 2) {
                    //vermelho
                    textView1.setBackgroundColor(Color.argb(255, 179, 71, 46));
                    textView1.setBackgroundResource(R.drawable.redcard);
                }
                if (card.getColor() == 3) {
                    //amarelo
                    textView1.setBackgroundColor(Color.argb(255, 224, 217, 70));
                    textView1.setBackgroundResource(R.drawable.yellowcard);
                }

                // hex color 0xAARRGGBB
                textView1.setTextColor(Color.argb(255, 255, 255, 255));
                textView1.setPadding(50, 50, 50, 50);// in pixels (left, top, right, bottom)

                textView1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        textView2.setLayoutParams(textView1.getLayoutParams());
                        textView2.setText(textView1.getText());
//                        textView2.setTextSize(textView1.getTextSize());
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
                    }
                });
                linearLayout.addView(textView1);
            }
        });
    }
}