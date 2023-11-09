package com.example.myapplication.Object;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Utils {
    public static List<Card> NewCardsDeck() {
        List<Card> list = new ArrayList<Card>();
        for (int i = 0; i <7; i++) {
            buyCards().setId(i);
            Random rand = new Random();
            Card card = new Card();
            card.setColor(rand.nextInt(4));
            card.setNumber(rand.nextInt(10));
            list.add(card);
        }

        return list;
    }

    public static Card buyCards() {

        Random rand = new Random();
        Card card = new Card();
        card.setColor(rand.nextInt(4));
        card.setNumber(rand.nextInt(10));

        return card;
    }
}
