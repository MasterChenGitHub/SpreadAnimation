package com.master.spreadanimation;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewGroupCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final SpreadImageView spreadImageView = (SpreadImageView) findViewById(R.id.siv);
        final View containerPopupWindow = null;
        containerPopupWindow.setRotationX(-90);
        containerPopupWindow.setAlpha(0.2F);
        containerPopupWindow.setPivotX(0.0F);

        findViewById(R.id.iv_hello).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spreadImageView.setVisibility(View.VISIBLE);
                spreadImageView.startAnimation();
            }
        });
    }
}
