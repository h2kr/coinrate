package com.h2kr.coinrate.game_logic;

import android.content.Context;

import com.h2kr.coinrate.gamer.Gamer;
import com.h2kr.coinrate.layout.GameScreen;
import com.h2kr.coinrate.tools.Message;

public class MainLogic extends GameScreen{

    public static int rateCoin;

    public void buy() {
        Gamer.dollars = Gamer.dollars - rateCoin;
        Gamer.coins = Gamer.coins + 1;
    }

    public void sell() {
        if (Gamer.coins == 0) {
            //-----//
        } else {
            Gamer.dollars = Gamer.dollars + rateCoin;
            Gamer.coins = Gamer.coins - 1;
        }
    }

    public static void updateRate() {
    }

}
