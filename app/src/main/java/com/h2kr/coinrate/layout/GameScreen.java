package com.h2kr.coinrate.layout;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.h2kr.coinrate.R;
import com.h2kr.coinrate.game_logic.CoinRateScreen;
import com.h2kr.coinrate.game_logic.MainLogic;
import com.h2kr.coinrate.gamer.Gamer;

public class GameScreen extends AppCompatActivity {

    private Thread rateCoin = new Thread(new CoinRateScreen());

    public static Handler handler;

    private Button buy;
    private Button sell;

    private ImageView rateIcon;

    public static TextView currentRate;

    private TextView amountOfDollars;
    private TextView amountOfCoins;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_game_screen);

            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


            buy = findViewById(R.id.buy);
            sell = findViewById(R.id.sell);
            currentRate = findViewById(R.id.rate);
            amountOfCoins = findViewById(R.id.coins);
            amountOfDollars = findViewById(R.id.dollars);
            rateIcon = findViewById(R.id.rate_icon);
            MainLogic.rateCoin = 1000;

            handler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    currentRate.setText(msg.what + "");

                    if(msg.what > 0) {
                        rateIcon.setBackgroundResource(R.drawable.ic_rate);
                        currentRate.setTextColor(Color.GREEN);
                    }
                    if(msg.what < 0) {
                        rateIcon.setBackgroundResource(R.drawable.ic_rate_down);
                        currentRate.setTextColor(Color.RED);
                    }
                }
            };

            buy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new MainLogic().buy();
                    amountOfCoins.setText("" + Gamer.coins);
                    amountOfDollars.setText("" + Gamer.dollars + "$");
                }
            });

            sell.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new MainLogic().sell();
                    amountOfCoins.setText("" + Gamer.coins);
                    amountOfDollars.setText("" + Gamer.dollars + "$");
                }
            });
            rateCoin.start();
        }


        public TextView getCurrentRate() {
            return currentRate;
        }

}
