package com.example.dell.miniproject;
import android.content.Context;
import  android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.Handler;
import android.media.MediaPlayer;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import org.w3c.dom.Text;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


import static com.example.dell.miniproject.R.drawable.up7;
import static com.example.dell.miniproject.R.id.numgenid;
import static com.example.dell.miniproject.R.id.text;
import static com.example.dell.miniproject.R.id.up;
import static com.example.dell.miniproject.R.id.up7id;
import static java.util.logging.Logger.global;
public class Main2Activity extends AppCompatActivity {
   private static final String PREFS_NAME="MyPrepsFile";
    Button up7 ,Back;
    Button down7;
    Button num7;
    TextView bet;
    VideoView Video1;
    TextView coin;
    TextView prev;
    ImageView res;
    int[] arr={0,0,0,0,0};
    int i1,i2,i,j;
    int c,c1;
    int cnt,b1=0,conf=0;
    String b2;
    Button ok;
    Button reset;
    String Name="Name key";
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        setTitle("Main2Activity");
        Intent intent=getIntent();

        c1=intent.getIntExtra("balance",0);
        coin=(TextView)findViewById(R.id.coinid);
        coin.setText("COINS: "+c1);
        c=c1;
        /* Button up7 = (Button) findViewById(R.id.up7id);
        Button down7 = (Button) findViewById(R.id.down7id);
        Button num7 = (Button) findViewById(R.id.id7);*/
        ok = (Button) findViewById(R.id.okid);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (b1 <= c){
                    conf = 1;
                Toast.makeText(Main2Activity.this, "Bet Confirmed", Toast.LENGTH_LONG).show();
            }
                else{
                conf=0;
                    Toast.makeText(Main2Activity.this,"Bet limit exceeded" ,Toast.LENGTH_LONG).show();
                }

            }
        });
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);

        ImageButton val10 = (ImageButton) findViewById(R.id.id10);
        ImageButton val25 = (ImageButton) findViewById(R.id.id25);
        ImageButton val100 = (ImageButton) findViewById(R.id.id100);
        ImageButton val500 = (ImageButton) findViewById(R.id.id500);
        ImageButton val1000 = (ImageButton) findViewById(R.id.id1000);

        reset = (Button) findViewById(R.id.resetid);
        //Button back =(Button)findViewById(R.id.backid);
        bet = (TextView) findViewById(R.id.currid);

        int screenHeight = displaymetrics.heightPixels;

        int h= (int)(screenHeight*0.25);


       val10.getLayoutParams().width = h;
        val25.getLayoutParams().width = h;
        val100.getLayoutParams().width = h;
        val1000.getLayoutParams().width = h;
        val500.getLayoutParams().width = h;



        val10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                b1 = b1 + 10;
                if (b1 > c) {
                    Toast.makeText(Main2Activity.this, "Bet limit exceeded ", Toast.LENGTH_SHORT).show();
                    b1 = b1 - 10;
                } else {
                    bet.setText(b1 + "");
                }
            }
        });
        val25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b1 = b1 + 25;
                if (b1 > c) {
                    Toast.makeText(Main2Activity.this, "Bet limit exceeded ", Toast.LENGTH_SHORT).show();
                    b1 = b1 - 25;
                } else {
                    bet.setText(b1 + "");
                }
            }
        });
        val100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b1 = b1 + 100;
                if (b1 > c) {
                    Toast.makeText(Main2Activity.this, "Bet limit exceeded ", Toast.LENGTH_SHORT).show();
                    b1 = b1 - 100;
                } else {
                    bet.setText(b1 + "");
                }
            }
        });
        val500.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b1 = b1 + 500;
                if (b1 > c) {
                    Toast.makeText(Main2Activity.this, "Bet limit exceeded ", Toast.LENGTH_SHORT).show();
                    b1 = b1 - 500;
                } else {
                    bet.setText(b1 + "");
                }
            }
        });
        val1000.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b1 = b1 + 1000;
                if (b1 > c) {
                    Toast.makeText(Main2Activity.this, "Bet limit exceeded ", Toast.LENGTH_SHORT).show();
                    b1 = b1 - 1000;
                } else {
                    bet.setText(b1 + "");
                }
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b1 = 0;
                bet.setText(b1 + "");
            }
        });
       /* back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent2 = new Intent();
                intent2.putExtra("balance2",c);
                setResult(RESULT_OK,intent2);
                finish();
            }
        } );*/
    }

    @Override
    protected void onUserLeaveHint() {
        Intent intent2 = new Intent();
        intent2.putExtra("balance2",c);
        setResult(RESULT_CANCELED,intent2);
        finish();
    }


    public void onBackPressed(){
        Intent intent2 = new Intent();
        intent2.putExtra("balance2",c);
        setResult(RESULT_CANCELED,intent2);

       finish();
    }
public void back(View view){
    Intent intent2 = new Intent(Main2Activity.this,MainActivity.class);
    intent2.putExtra("balance2",c);
    setResult(RESULT_CANCELED,intent2);
    finish();
}




    public void shubh(final View view) {
        if (conf==1)
        {
            final Animation anim1= AnimationUtils.loadAnimation(Main2Activity.this,R.anim.shake);
            final Animation anim2= AnimationUtils.loadAnimation(Main2Activity.this,R.anim.shake);
        MediaPlayer ring= MediaPlayer.create(Main2Activity.this,R.raw.ring);
        ring.start();
         ImageView d1=(ImageView) findViewById(R.id.d1id);
         ImageView d2=(ImageView) findViewById(R.id.d2id);
        d1.startAnimation(anim1);
        d2.startAnimation(anim2);
        Random r = new Random();
        Random r1 = new Random();
        i1 = r.nextInt(6) + 1;
        i2 = r1.nextInt(6) + 1;
        i=i1+i2;
        for (j=0;j<4;j++)
        {
            arr[j]=arr[j+1];
        }
        arr[4]=i;
       /* EditText  bet = (EditText)findViewById(R.id.betid);
        int b1=0;
        cnt=0;
        b1=Integer.valueOf(bet.getText().toString());
        if( b1 > c)
        {
            bet.setError("Bet Limit Exceeded");
            Toast.makeText(Main2Activity.this,"Bet limit exceeded",Toast.LENGTH_LONG).show();
            b1=0;
            i=0;
        }*/
            coin = (TextView) findViewById(R.id.coinid);

         prev=(TextView) findViewById(R.id.recordid);
        prev.setText("Previous Numbers: "+arr[0]+"  "+arr[1]+"  "+arr[2]+"  "+arr[3]+"  "+arr[4]);
        TextView  numgen = (TextView) findViewById(R.id.numgenid);
        numgen.setText(i + " ");

            conf=0;
            switch (i1){
            case 1:
                d1.setImageResource(R.drawable.dice1);
                break;
            case 2:
                d1.setImageResource(R.drawable.dice2);
                break;
            case 3:
                d1.setImageResource(R.drawable.dice3);
                break;
            case 4:
                d1.setImageResource(R.drawable.dice4);
                break;
            case 5:
                d1.setImageResource(R.drawable.dice5);
                break;
            case 6:
                d1.setImageResource(R.drawable.dice6);
                break;
        }
        switch (i2){
            case 1:
                d2.setImageResource(R.drawable.dice1);
                break;
            case 2:
                d2.setImageResource(R.drawable.dice2);
                break;
            case 3:
                d2.setImageResource(R.drawable.dice3);
                break;
            case 4:
                d2.setImageResource(R.drawable.dice4);
                break;
            case 5:
                d2.setImageResource(R.drawable.dice5);
                break;
            case 6:
                d2.setImageResource(R.drawable.dice6);
                break;
        }
            new android.os.Handler().postDelayed(new Runnable() {
                @Override
                public void run() {


            if (i > 7)
            {

                res=(ImageView)findViewById(R.id.result);
                prev.setAlpha(0f);
                res.setImageResource(R.drawable.win);
                up7 = (Button) findViewById(R.id.up7id);
                down7 = (Button) findViewById(R.id.down7id);
                num7 = (Button) findViewById(R.id.id7);
                up7.setAlpha(0f);
                down7.setAlpha(0f);
                num7.setAlpha(0f);
                reset.setAlpha(0f);
                ok.setAlpha(0f);
                res.setAlpha(1f);

                c = c +b1;
                  Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        prev.setAlpha(1f);
                        res.setAlpha(0f);
                        ok.setAlpha(1f);
                        up7.setAlpha(1f);
                        down7.setAlpha(1f);
                        num7.setAlpha(1f);
                        reset.setAlpha(1f);
                    }
                },3000);
            }
            else
            {
                up7 = (Button) findViewById(R.id.up7id);
                down7 = (Button) findViewById(R.id.down7id);
                num7 = (Button) findViewById(R.id.id7);
                prev.setAlpha(0f);
                res=(ImageView)findViewById(R.id.result);
                res.setImageResource(R.drawable.lose);
                up7.setAlpha(0f);
                ok.setAlpha(0f);
                down7.setAlpha(0f);
                num7.setAlpha(0f);
                reset.setAlpha(0f);
                res.setAlpha(1f);
                c = c -b1;
               Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        prev.setAlpha(1f);
                        res.setAlpha(0f);
                        ok.setAlpha(1f);
                        up7.setAlpha(1f);
                        down7.setAlpha(1f);
                        num7.setAlpha(1f);
                        reset.setAlpha(1f);
                    }
                },3000);
            }
                    coin.setText("COINS: "+c);
                }
            }, 4000);
            coin.setText("COINS: "+c);
        }
        else {
            Toast.makeText(Main2Activity.this, "Confirm Your Bet", Toast.LENGTH_LONG).show();
            conf=0;
        }

    }
    public void shubh2(final View view)
    {
        if(conf==1)
        {
        final Animation anim1= AnimationUtils.loadAnimation(Main2Activity.this,R.anim.shake);
        final Animation anim2= AnimationUtils.loadAnimation(Main2Activity.this,R.anim.shake);
        MediaPlayer ring= MediaPlayer.create(Main2Activity.this,R.raw.ring);
        ring.start();
        ImageView d1=(ImageView) findViewById(R.id.d1id);
        ImageView d2=(ImageView) findViewById(R.id.d2id);
        d1.startAnimation(anim1);
        d2.startAnimation(anim2);
        Random r = new Random();
        Random r1 = new Random();
        i1 = r.nextInt(6) + 1;
        i2 = r1.nextInt(6) + 1;
        i=i1+i2;
        conf=0;

        for (j=0;j<4;j++)
        {
            arr[j]=arr[j+1];
        }
        arr[4]=i;

         coin = (TextView) findViewById(R.id.coinid);

       prev=(TextView) findViewById(R.id.recordid);
        prev.setText("Previous Numbers: "+arr[0]+"  "+arr[1]+"  "+arr[2]+"  "+arr[3]+"  "+arr[4]);
        TextView  numgen = (TextView) findViewById(R.id.numgenid);
        numgen.setText(i + " ");

        switch (i1){
            case 1:
                d1.setImageResource(R.drawable.dice1);
                break;
            case 2:
                d1.setImageResource(R.drawable.dice2);
                break;
            case 3:
                d1.setImageResource(R.drawable.dice3);
                break;
            case 4:
                d1.setImageResource(R.drawable.dice4);
                break;
            case 5:
                d1.setImageResource(R.drawable.dice5);
                break;
            case 6:
                d1.setImageResource(R.drawable.dice6);
                break;
        }
        switch (i2){
            case 1:
                d2.setImageResource(R.drawable.dice1);
                break;
            case 2:
                d2.setImageResource(R.drawable.dice2);
                break;
            case 3:
                d2.setImageResource(R.drawable.dice3);
                break;
            case 4:
                d2.setImageResource(R.drawable.dice4);
                break;
            case 5:
                d2.setImageResource(R.drawable.dice5);
                break;
            case 6:
                d2.setImageResource(R.drawable.dice6);
                break;
        }
            new android.os.Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
            if (i < 7)
            {
                res=(ImageView)findViewById(R.id.result);
               res.setImageResource(R.drawable.win);
                 up7 = (Button) findViewById(R.id.up7id);
                 down7 = (Button) findViewById(R.id.down7id);
                 num7 = (Button) findViewById(R.id.id7);
                up7.setAlpha(0f);
                down7.setAlpha(0f);
                num7.setAlpha(0f);
                prev.setAlpha(0f);
                reset.setAlpha(0f);
               ok.setAlpha(0f);
                res.setAlpha(1f);

                c = c +b1;
                 Timer timer = new Timer();
                 timer.schedule(new TimerTask() {
            @Override
            public void run() {
               res.setAlpha(0f);
                up7.setAlpha(1f);
                down7.setAlpha(1f);
                num7.setAlpha(1f);
                ok.setAlpha(1f);
                prev.setAlpha(1f);
                reset.setAlpha(1f);
            }
        },3000);
            }
            else
            {
                up7 = (Button) findViewById(R.id.up7id);
                down7 = (Button) findViewById(R.id.down7id);
                num7 = (Button) findViewById(R.id.id7);
                res=(ImageView)findViewById(R.id.result);
                res.setImageResource(R.drawable.lose);
                prev.setAlpha(0f);
                up7.setAlpha(0f);
                down7.setAlpha(0f);
                num7.setAlpha(0f);
                ok.setAlpha(0f);
                reset.setAlpha(0f);
                res.setAlpha(1f);
                c = c -b1;
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        res.setAlpha(0f);
                        up7.setAlpha(1f);
                        down7.setAlpha(1f);
                        num7.setAlpha(1f);
                        reset.setAlpha(1f);
                        ok.setAlpha(1f);
                        prev.setAlpha(1f);
                    }
                },3000);
            }
                    coin.setText("COINS: "+c);
                }
            }, 4000);

            coin.setText("COINS: "+c);


        }
        else {
        Toast.makeText(Main2Activity.this, "Confirm Your Bet", Toast.LENGTH_LONG).show();
        conf=0;

    }
    }
    public void shubh3(View view) {
        if(conf==1)
        {
        final Animation anim1= AnimationUtils.loadAnimation(Main2Activity.this,R.anim.shake);
        final Animation anim2= AnimationUtils.loadAnimation(Main2Activity.this,R.anim.shake);
        MediaPlayer ring= MediaPlayer.create(Main2Activity.this,R.raw.ring);
        ring.start();
        ImageView d1=(ImageView) findViewById(R.id.d1id);
        ImageView d2=(ImageView) findViewById(R.id.d2id);
        d1.startAnimation(anim1);
        d2.startAnimation(anim2);
        Random r = new Random();
        Random r1 = new Random();
        i1 = r.nextInt(6) + 1;
        i2 = r1.nextInt(6) + 1;
        i=i1+i2;
            conf=0;

        for (j=0;j<4;j++)
        {
            arr[j]=arr[j+1];
        }
        arr[4]=i;
            conf=0;

         coin = (TextView) findViewById(R.id.coinid);
         prev=(TextView) findViewById(R.id.recordid);
        prev.setText("Previous Numbers: "+arr[0]+"  "+arr[1]+"  "+arr[2]+"  "+arr[3]+"  "+arr[4]);
        TextView  numgen = (TextView) findViewById(R.id.numgenid);
        numgen.setText(i + " ");

        switch (i1){
            case 1:
                d1.setImageResource(R.drawable.dice1);
                break;
            case 2:
                d1.setImageResource(R.drawable.dice2);
                break;
            case 3:
                d1.setImageResource(R.drawable.dice3);
                break;
            case 4:
                d1.setImageResource(R.drawable.dice4);
                break;
            case 5:
                d1.setImageResource(R.drawable.dice5);
                break;
            case 6:
                d1.setImageResource(R.drawable.dice6);
                break;
        }
        switch (i2){
            case 1:
                d2.setImageResource(R.drawable.dice1);
                break;
            case 2:
                d2.setImageResource(R.drawable.dice2);
                break;
            case 3:
                d2.setImageResource(R.drawable.dice3);
                break;
            case 4:
                d2.setImageResource(R.drawable.dice4);
                break;
            case 5:
                d2.setImageResource(R.drawable.dice5);
                break;
            case 6:
                d2.setImageResource(R.drawable.dice6);
                break;
        }
            new android.os.Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
            if (i == 7)
            {
                res=(ImageView)findViewById(R.id.result);
                 res.setImageResource(R.drawable.win);
                up7 = (Button) findViewById(R.id.up7id);
                down7 = (Button) findViewById(R.id.down7id);
                num7 = (Button) findViewById(R.id.id7);
                up7.setAlpha(0f);
                down7.setAlpha(0f);
                num7.setAlpha(0f);
                reset.setAlpha(0f);
                prev.setAlpha(0f);
                ok.setAlpha(0f);
                res.setAlpha(1f);
                c = c +(2*b1);
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        res.setAlpha(0f);
                        up7.setAlpha(1f);
                        down7.setAlpha(1f);
                        num7.setAlpha(1f);
                        prev.setAlpha(1f);
                        reset.setAlpha(1f);
                        ok.setAlpha(1f);
                    }
                },3000);
            }
            else
            {
                up7 = (Button) findViewById(R.id.up7id);
                down7 = (Button) findViewById(R.id.down7id);
                num7 = (Button) findViewById(R.id.id7);
                res=(ImageView)findViewById(R.id.result);
                res.setImageResource(R.drawable.lose);
                up7.setAlpha(0f);
                down7.setAlpha(0f);
                num7.setAlpha(0f);
                prev.setAlpha(0f);
                ok.setAlpha(0f);
                reset.setAlpha(0f);
                res.setAlpha(1f);
                c = c -b1;
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        res.setAlpha(0f);
                        up7.setAlpha(1f);
                        down7.setAlpha(1f);
                        num7.setAlpha(1f);
                        prev.setAlpha(1f);
                        reset.setAlpha(1f);
                        ok.setAlpha(1f);
                    }
                },3000);
            }
                    coin.setText("COINS: "+c);
                }
            }, 4000);
            coin.setText("COINS: "+c);
        }
        else {
            Toast.makeText(Main2Activity.this, "Confirm Your Bet", Toast.LENGTH_LONG).show();
            conf=0;
        }
    }    }








