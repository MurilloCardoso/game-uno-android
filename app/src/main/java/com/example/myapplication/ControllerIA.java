package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.myapplication.Object.Card;

import java.util.ArrayList;
import java.util.List;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ControllerIA {
    public List<Card> IA_Deck = new ArrayList<>();

    public  void Choose(Card pending ,Context cont) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(cont);

        if (pref.getBoolean("KEY_IA",false)) {

            for (int i = 0; i < IA_Deck.size(); i++) {
                if (
                        IA_Deck.get(i).getColor() == pending.getColor() || IA_Deck.get(i).getNumber() == pending.getNumber()
                ) {
                    //remover e joga
                } else {
                    //comparar carta
                }
            }
        }else{
            //player
        }
    }

}
