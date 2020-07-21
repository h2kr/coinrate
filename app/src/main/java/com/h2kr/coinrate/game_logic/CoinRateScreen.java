package com.h2kr.coinrate.game_logic;

import android.os.Build;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.widget.Button;
import android.widget.TextView;

import com.h2kr.coinrate.gamer.Gamer;
import com.h2kr.coinrate.layout.GameScreen;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static com.h2kr.coinrate.layout.GameScreen.currentRate;
import static com.h2kr.coinrate.layout.GameScreen.handler;

public class CoinRateScreen extends Thread {

    public CoinRateScreen() {
        random = new Random();
    }

    private Random random;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void run() {
        while (true) {

            if(Gamer.dollars < 100) {
                MainLogic.rateCoin = randomSumOrDiff(10);
            } else {
                MainLogic.rateCoin = randomSumOrDiff(Gamer.dollars / 50);
            }
            handler.sendEmptyMessage(MainLogic.rateCoin);

            try {
                sleep(ThreadLocalRandom.current().nextInt(1350) + 20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }


    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private int randomSumOrDiff(int num) {

        if(random.nextBoolean())
            return (int) (num + ThreadLocalRandom.current().nextLong(Math.abs(num)  * 5));
        else
            return (int) (num - ThreadLocalRandom.current().nextLong(Math.abs(num) * 5));

    }
}
