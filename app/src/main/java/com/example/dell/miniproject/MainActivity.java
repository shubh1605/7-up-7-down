package com.example.dell.miniproject;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.os.PowerManager;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Timer;
import java.util.TimerTask;

import static android.R.attr.countDown;
import static android.R.attr.name;

public class MainActivity extends AppCompatActivity {
    //String balance ;
     private static final String PREFS_NAME="MyPrepsFile";
     HomeWatcher mHomeWatcher;
    long timeLeftInMilliSeconds=86400000;
    ToggleButton tb;
    int bal,bala=10000;
    int coins;
    CountDownTimer countDownTimer;
     boolean mTimerRunning;
    long mEndTime;
    TextView text1;
    TextView time;
    int h,m,sec;
    int fc,fc1=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        time=(TextView) findViewById(R.id.timeid);
startTimer();


        SharedPreferences settings=getSharedPreferences(PREFS_NAME,0);
        bala=settings.getInt(PREFS_NAME,bala);

        tb = (ToggleButton) findViewById (R.id.audioid);
        tb.setText(null);
        tb.setTextOn(null);
        tb.setTextOff(null);
        Button start = (Button) findViewById(R.id.startid);
        Button rules = (Button) findViewById(R.id.rulesid);


        text1 = (TextView)findViewById(R.id.balid);
        text1.setText(""+bala);
        start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.this, Main2Activity.class);
                intent1.putExtra("balance",bala);
                startActivityForResult(intent1,1);
               // startActivityForResult(new Intent(getApplicationContext(),Main2Activity.class),1);
            }
        });
        rules.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent3 = new Intent(MainActivity.this, Main3Activity.class);
                startActivity(intent3);
            }
        });

        tb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                   tb.setBackgroundResource(android.R.drawable.ic_lock_silent_mode);
                   if (mServ != null) {
                       mServ.stopMusic();
                   }
                }
                else
                {
                    tb.setBackgroundResource(android.R.drawable.ic_lock_silent_mode_off);
                    if (mServ != null) {
                        mServ.startMusic();
                    }

                }
            }
        });

        doBindService();
        Intent music = new Intent();
        music.setClass(this, Musicservice.class);
        startService(music);

        HomeWatcher mHomeWatcher;

        mHomeWatcher = new HomeWatcher(this);
        mHomeWatcher.setOnHomePressedListener(new HomeWatcher.OnHomePressedListener() {
            @Override
            public void onHomePressed() {
                if (mServ != null) {
                    mServ.pauseMusic();
                }
            }
            @Override
            public void onHomeLongPressed() {
                if (mServ != null) {
                    mServ.pauseMusic();
                }
            }
        });
        mHomeWatcher.startWatch();
    }


    public void startTimer() {
        mEndTime = System.currentTimeMillis() + timeLeftInMilliSeconds;
        countDownTimer = new CountDownTimer(timeLeftInMilliSeconds, 1000) {
            @Override
            public void onTick(long l) {
                timeLeftInMilliSeconds = l;
                updateCountDownText();
            }
            @Override
            public void onFinish() {
                fc = 1000;
                fc1 = fc1 + 1000;
                if (fc1 < 10000) {
                    bala = bala + 1000;
                }
                start();
            }
        }.start();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(( requestCode==1&& resultCode==RESULT_OK)||( requestCode==1&& resultCode==RESULT_CANCELED)){
            bala=data.getIntExtra("balance2",0);
            text1.setText(bala+"");
            SharedPreferences settings=getSharedPreferences(PREFS_NAME,0);
            SharedPreferences.Editor editor=settings.edit();
            editor.putInt(PREFS_NAME,bala);
            editor.commit();
        }
    }

    private boolean mIsBound = false;
    private Musicservice mServ;
    private ServiceConnection Scon =new ServiceConnection(){
        public void onServiceConnected(ComponentName name, IBinder
                binder) {
            mServ = ((Musicservice.ServiceBinder)binder).getService();
        }

        public void onServiceDisconnected(ComponentName name) {
            mServ = null;
        }
    };

    void doBindService(){
        bindService(new Intent(this,Musicservice.class),
                Scon, Context.BIND_AUTO_CREATE);
        mIsBound = true;
    }

    void doUnbindService()
    {
        if(mIsBound)
        {
            unbindService(Scon);
            mIsBound = false;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mServ != null) {
            mServ.resumeMusic();
        }

    }
    @Override
    protected void onPause() {
        super.onPause();

        PowerManager pm = (PowerManager)
                getSystemService(Context.POWER_SERVICE);
        boolean isScreenOn = false;
        if (pm != null) {
            isScreenOn = pm.isScreenOn();
        }

        if (!isScreenOn) {
            if (mServ != null) {
                mServ.pauseMusic();
            }
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        doUnbindService();
        Intent music = new Intent();
        music.setClass(this,Musicservice.class);
        stopService(music);
    }
    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putLong("millisLeft", timeLeftInMilliSeconds);
        editor.putBoolean("timerRunning", true);
        editor.putLong("endTime", mEndTime);
        editor.apply();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
    @Override
    public void onStart() {
        super.onStart();
        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        timeLeftInMilliSeconds = prefs.getLong("millisLeft", 43200000);
        mTimerRunning = prefs.getBoolean("timerRunning", false);
        updateCountDownText();
        if (mTimerRunning){
        mEndTime = prefs.getLong("endTime", 0);
        timeLeftInMilliSeconds = mEndTime - System.currentTimeMillis();
            startTimer();
        }}

public void updateCountDownText(){
    h=(int)timeLeftInMilliSeconds/3600000;
    m=(int) (timeLeftInMilliSeconds-(h*3600000))/60000;
    sec=(int) (timeLeftInMilliSeconds-(h*3600000)-(m*60000))/1000;
    time.setText(h+":"+m+":"+sec);
}
}
