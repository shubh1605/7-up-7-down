package com.example.dell.miniproject;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {
   // TextView text2 =(TextView) findViewById(R.id.rules1);
   ImageView rule;
    Button next,previous;
    Button Back;
    int page=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Back=(Button)findViewById(R.id.bckid);
        rule=(ImageView)findViewById(R.id.ruleid);
        next = (Button) findViewById(R.id.nextid);
        previous = (Button) findViewById(R.id.previousid);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(Main3Activity.this, MainActivity.class);
                startActivity(intent3);
            }
        });

    }
public void nextp(View view){
    if(page==1){
        rule.setImageResource(R.drawable.rules2);
        previous.setVisibility(View.VISIBLE);
        next.setVisibility(View.VISIBLE);
        page=2;
    }
   else{
        rule.setImageResource(R.drawable.rules3);
        previous.setVisibility(View.VISIBLE);
        next.setVisibility(View.GONE);
        page=3;
    }
}
public void prevp(View view){
    if(page==3){
        rule.setImageResource(R.drawable.rules2);
        previous.setVisibility(View.VISIBLE);
        next.setVisibility(View.VISIBLE);
        page=2;
    }
    else{
        rule.setImageResource(R.drawable.rules10);
        previous.setVisibility(View.GONE);
        next.setVisibility(View.VISIBLE);
        page=1;
    }
}
}
