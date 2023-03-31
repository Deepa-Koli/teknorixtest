package com.androideeps.teknorix.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.androideeps.teknorix.R;
import com.androideeps.teknorix.activities.MainActivity;

public class SplashActivity extends AppCompatActivity {
    private ImageView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        tv=(ImageView) findViewById(R.id.tv);
        Animation myanim= AnimationUtils.loadAnimation(this,R.anim.mytransition);
        tv.setAnimation(myanim);
        final Intent i=new Intent(this, MainActivity.class);
        Thread timer=new Thread(){
            public void run(){
                try {
                    sleep(3500);
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    startActivity(i);
                    finish();
                }
            }
        };
        timer.start();
    }
}