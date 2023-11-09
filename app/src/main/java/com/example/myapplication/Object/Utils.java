package com.example.myapplication.Object;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.MainActivity;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import java.util.Set;

public class Utils {


    public static List<Card> NewCardsDeck() {
        List<Card> DeckCard = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < 7; i++) {

            Card card = new Card();
            card.setId(i + 1);
            card.setColor(rand.nextInt(4));
            card.setNumber(rand.nextInt(10));
            DeckCard.add(card);
            // Faça algo com a nova carta, se necessário
        }
        return DeckCard;
    }

    public static Card buyCards() {
        Random rand = new Random();
        int newId;

        do {
            newId = rand.nextInt(100); // Ajuste o intervalo conforme necessário
        } while (hasCardWithId(newId));

        Card card = new Card();
        card.setId(newId);
        card.setColor(rand.nextInt(4));
        card.setNumber(rand.nextInt(10));

        return card;
    }

    public static Card GerarCard() {
        Random rand = new Random();
        int newId;
        newId = rand.nextInt(100);

        Card card = new Card();
        card.setId(newId);
        card.setColor(rand.nextInt(4));
        card.setNumber(rand.nextInt(10));

        return card;
    }

    public static TextView ConstructTextView(Context contx, Card card) {
        TextView textView1 = new TextView(contx);
        textView1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        textView1.setText("" + card.getNumber());
        textView1.setId(card.getId());
        textView1.setTextSize(20);
        textView1.setGravity(Gravity.CENTER);
        textView1.setRotation(90);
        if (card.getColor() == 0) {
            //verde
            textView1.setBackgroundColor(Color.argb(255, 0, 128, 51));
        }
        if (card.getColor() == 1) {
            ///azul
            textView1.setBackgroundColor(Color.argb(255, 45, 76, 189));
        }
        if (card.getColor() == 2) {
            //vermelho
            textView1.setBackgroundColor(Color.argb(255, 179, 71, 46));
        }
        if (card.getColor() == 3) {
            //amarelo
            textView1.setBackgroundColor(Color.argb(255, 224, 217, 70));
        }

        // hex color 0xAARRGGBB
        textView1.setTextColor(Color.argb(255, 255, 255, 255));
        textView1.setPadding(50, 50, 50, 20);// in pi


        return textView1;
    }

    private static boolean hasCardWithId(int id) {
        for (Card card : MainActivity.DeckPlayer) {
            if (card.getId() == id) {

                return true; // O ID já existe na lista
            }
        }
        return false; // O ID não existe na lista
    }

    public boolean firtsPlay() {
        Random rd = new Random();
        int choose = rd.nextInt(10);
        if (choose > 5) {
            return true;
        } else {
            return false;
        }

    }
}
